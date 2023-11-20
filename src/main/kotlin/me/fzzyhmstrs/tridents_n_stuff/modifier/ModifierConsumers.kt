package me.fzzyhmstrs.tridents_n_stuff.modifier

import me.fzzyhmstrs.fzzy_core.coding_util.PerLvlI
import me.fzzyhmstrs.fzzy_core.coding_util.PersistentEffectHelper
import me.fzzyhmstrs.fzzy_core.nbt_util.Nbt
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import net.minecraft.entity.*
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import kotlin.math.max

object ModifierConsumers {

    val STORM_BLESSED_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            val nbt = stack.orCreateNbt
            val stormKills = nbt.getInt("storm_kills") + 1
            if (user is PlayerEntity) {
                val inventory = user.inventory
                for (i in 0 until inventory.size()){
                    val stackTest = inventory.getStack(i)
                    val nbtTest = stackTest.nbt ?: continue
                    if (Nbt.getItemStackId(nbtTest) == Nbt.getItemStackId(nbt)){
                        nbtTest.putInt("storm_kills",stormKills)
                        break
                    }
                }
            } else {
                val stackTest = user.getStackInHand(Hand.MAIN_HAND)
                stackTest.nbt?.putInt("storm_kills",stormKills)
            }
            nbt.putInt("storm_kills",stormKills)
            if (stormKills > 24) {
                if (target != null && target.distanceTo(user) > 5f) {
                    val storm = LightningStormPersistentEffect()
                    storm.lightningStorm(user,target)
                    if (user is PlayerEntity) {
                        val inventory = user.inventory
                        for (i in 0 until inventory.size()){
                            val stackTest = inventory.getStack(i)
                            val nbtTest = stackTest.nbt ?: continue
                            if (Nbt.getItemStackId(nbtTest) == Nbt.getItemStackId(nbt)){
                                nbtTest.putBoolean("crackling", false)
                                nbtTest.putInt("storm_kills", 0)
                                break
                            }
                        }
                    } else {
                        val stackTest = user.getStackInHand(Hand.MAIN_HAND)
                        stackTest.nbt?.putBoolean("crackling", false)
                        stackTest.nbt?.putInt("storm_kills", 0)
                    }
                    nbt.putBoolean("crackling", false)
                    nbt.putInt("storm_kills", 0)
                } else {
                    nbt.putBoolean("crackling", true)
                }
            }

        }

    private class LightningStormPersistentEffect(override val delay: PerLvlI = PerLvlI()): PersistentEffectHelper.PersistentEffect{

        fun lightningStorm(user: LivingEntity,target: LivingEntity){
            val le = LightningEntity(EntityType.LIGHTNING_BOLT,target.world)
            val startPos = Vec3d.ofBottomCenter(target.blockPos)
            le.refreshPositionAfterTeleport(startPos)
            le.channeler = user as? ServerPlayerEntity
            user.world.spawnEntity(le)
            var startYaw = target.yaw
            val directions: MutableSet<Vec3d> = mutableSetOf()
            for (q in 1..6){
                val g: Float = -startYaw * (Math.PI.toFloat() / 180)
                val z = MathHelper.cos(g)
                val x = MathHelper.sin(g)
                val direction = Vec3d(x.toDouble(),0.0,z.toDouble())
                directions.add(direction)
                startYaw += 60
            }
            val data = Data(user.world,user,target,startPos,1,directions)
            PersistentEffectHelper.setPersistentTickerNeed(this, 10, 10, data)
        }

        override fun persistentEffect(data: PersistentEffectHelper.PersistentEffectData) {
            if (data !is Data) return
            for (direction in data.directions){
                val pos = data.startPos.add(direction.multiply(1.5 * data.phase))
                val blockPos = BlockPos.ofFloored(pos)
                val checkPos = blockPos.mutableCopy()
                for (offset in arrayOfChecks){
                    checkPos.move(0,offset,0)
                    if (data.world.getBlockState(checkPos).isReplaceable && data.world.getBlockState(checkPos.down()).isSolidBlock(data.world, checkPos.down())){
                        val le = LightningEntity(EntityType.LIGHTNING_BOLT,data.world)
                        val startPos = pos.add(0.0,offset.toDouble(),0.0)
                        le.refreshPositionAfterTeleport(startPos)
                        le.channeler = data.user as? ServerPlayerEntity
                        data.world.spawnEntity(le)
                    }
                }
            }
            if (data.phase <= 2){
                val newData = Data(data.world,data.user,data.target, data.startPos,data.phase + 1,data.directions)
                PersistentEffectHelper.setPersistentTickerNeed(this, 5, 5, newData)
            }
        }

        private val arrayOfChecks = intArrayOf(0,1,-1,2,-2,3,-3, 4, -4)

        private class Data(val world: World,val user: LivingEntity, val target: LivingEntity,val startPos: Vec3d, val phase: Int, val directions: Set<Vec3d>): PersistentEffectHelper.PersistentEffectData

    }

    val VILE_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            target.removeStatusEffect(StatusEffects.STRENGTH)
            target.addStatusEffect(StatusEffectInstance(StatusEffects.WEAKNESS, 400, 1))
            target.removeStatusEffect(StatusEffects.JUMP_BOOST)
            //target.addStatusEffect(StatusEffectInstance(StatusEffects.JUMP_BOOST, 400, -3))
        }
    val VILE_KILL_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            user.world.playSound(null,target.blockPos,SoundEvents.ENTITY_WITHER_SHOOT,SoundCategory.PLAYERS,0.5f,1f)
            val aece = AreaEffectCloudEntity(user.world,target.x,target.y + 0.25, target.z)
            aece.owner = user
            aece.addEffect(StatusEffectInstance(StatusEffects.WITHER,200,1))
            aece.particleType = ParticleTypes.SMOKE
            aece.duration = 200
            aece.radius = 2f
            aece.radiusGrowth = 0.0025f
            user.world.spawnEntity(aece)
        }

    val ECHO_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { stack: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            val box = target.boundingBox.expand(2.5)
            val entities = user.world.getOtherEntities(user,box) {e -> e is LivingEntity && e !== target}
            val dmg = max(3.0f,user.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE).toFloat()/3.0f)
            for (entity in entities){
                entity.damage(user.damageSources.indirectMagic(user,user),dmg)
            }
            if (user.world is ServerWorld){
                (user.world as ServerWorld).spawnParticles(ParticleTypes.CRIT,target.x,target.getBodyY(0.5), target.z,100,2.5,2.5,2.5,0.05)
            }
        }

    val STELLAR_HIT_CONSUMER: EquipmentModifier.ToolConsumer =
        EquipmentModifier.ToolConsumer { _: ItemStack, user: LivingEntity, target: LivingEntity? ->
            if (target == null) return@ToolConsumer
            val box = target.boundingBox.expand(2.5)
            val entities = user.world.getOtherEntities(user,box) {e -> e is LivingEntity && e !== target}.mapNotNull { it as? LivingEntity }
            for (entity in entities){
                entity.takeKnockback(0.35,entity.x - target.x,entity.z - target.z)
            }
            if (user.world is ServerWorld){
                (user.world as ServerWorld).spawnParticles(ParticleTypes.PORTAL,target.x,target.getBodyY(0.5), target.z,100,2.5,2.5,2.5,0.05)
            }
        }

}