package me.fzzyhmstrs.tridents_n_stuff.entity

import me.fzzyhmstrs.fzzy_core.coding_util.compat.FzzyDamage
import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterEntity
import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterItem
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.network.packet.s2c.play.GameStateChangeS2CPacket
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.world.World

class HarpoonEntity: PersistentProjectileEntity {
    constructor(entityType: EntityType<out HarpoonEntity?>, world: World) : super(entityType, world)
    constructor(world: World, x: Double, y: Double, z: Double): super(RegisterEntity.BONE_HARPOON,x, y, z, world)

    private var stack = ItemStack(RegisterItem.BONE_HARPOON)

    constructor(entityType: EntityType<out HarpoonEntity?>,world: World, owner: LivingEntity, stack: ItemStack): super(entityType,owner, world){
        this.stack = stack
    }

    companion object{
        val baseDamage = 5.0
        val attackRate = 15
        val powerPerLevel = 1.25
        val powerFlatAmount = 1.0
    }

    init {
        damage = baseDamage
    }

    override fun asItemStack(): ItemStack {
        return stack.copy()
    }

    override fun getDragInWater(): Float {
        return 0.99F
    }

    override fun isCritical(): Boolean {
        return false
    }

    override fun isShotFromCrossbow(): Boolean {
        return false
    }

    override fun getPierceLevel(): Byte {
        return 0
    }

    override fun getHitSound(): SoundEvent {
        return SoundEvents.ITEM_TRIDENT_HIT
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        val damageSource: DamageSource
        var entity2: Entity?
        val entity = entityHitResult.entity
        if (owner.also { entity2 = it } == null) {
            damageSource = FzzyDamage.trident(this)
        } else {
            damageSource = FzzyDamage.trident(this,this,entity2)
            if (entity2 is LivingEntity) {
                (entity2 as LivingEntity).onAttacking(entity)
            }
        }
        val bl = entity.type === EntityType.ENDERMAN
        val j = entity.fireTicks
        if (this.isOnFire && !bl) {
            entity.setOnFireFor(5)
        }
        if (entity.damage(damageSource, damage.toFloat())) {
            if (bl) {
                return
            }
            if (entity is LivingEntity) {
                val vec3d = velocity.multiply(1.0, 0.0, 1.0).normalize().multiply(punch.toDouble() * 0.6)
                if (punch > 0 && vec3d.lengthSquared() > 0.0) {
                    entity.addVelocity(vec3d.x, 0.1, vec3d.z)
                }
                if (!world.isClient && entity2 is LivingEntity) {
                    EnchantmentHelper.onUserDamaged(entity, entity2)
                    EnchantmentHelper.onTargetDamaged(entity2 as LivingEntity?, entity)
                }
                onHit(entity)
                if (entity2 != null && entity !== entity2 && entity is PlayerEntity && entity2 is ServerPlayerEntity && !this.isSilent) {
                    (entity2 as ServerPlayerEntity).networkHandler.sendPacket(
                        GameStateChangeS2CPacket(
                            GameStateChangeS2CPacket.PROJECTILE_HIT_PLAYER,
                            GameStateChangeS2CPacket.DEMO_OPEN_SCREEN.toFloat()
                        )
                    )
                }
            }
            playSound(sound, 1.0f, 1.2f / (random.nextFloat() * 0.2f + 0.9f))
        } else {
            entity.fireTicks = j
            velocity = velocity.multiply(-0.1)
            yaw += 180.0f
            prevYaw += 180.0f
            if (!world.isClient && velocity.lengthSquared() < 1.0E-7) {
                if (pickupType == PickupPermission.ALLOWED) {
                    this.dropStack(asItemStack(), 0.1f)
                }
                discard()
            }
        }
    }
}

