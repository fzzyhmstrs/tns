package me.fzzyhmstrs.tridents_n_stuff.entity

import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

open class CustomTridentEntity : BasicCustomTridentEntity {

    constructor(entityType: EntityType<out CustomTridentEntity>, world: World) : super(entityType, world)
    constructor(entityType: EntityType<out CustomTridentEntity>,world: World, owner: LivingEntity, stack: ItemStack) : super(entityType, world, owner, stack)

    override fun onOwnedHit(owner: LivingEntity, target: LivingEntity, source: DamageSource, amount: Float, stack: ItemStack): Float{
        var newAmount = amount
        for (equipMod in EquipmentModifierHelper.getRelevantModifiers(owner,stack)){
            newAmount = equipMod.onAttack(stack,owner,target,source, newAmount)
            equipMod.postHit(stack, owner, target)
        }
        return newAmount
    }

    override fun onOwnedKill(owner: LivingEntity, target: LivingEntity, stack: ItemStack) {
        for (equipMod in EquipmentModifierHelper.getRelevantModifiers(owner,stack)){
            equipMod.killedOther(stack, owner, target)
        }
    }

}
