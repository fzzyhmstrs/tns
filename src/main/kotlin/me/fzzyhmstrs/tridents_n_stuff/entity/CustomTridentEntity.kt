package me.fzzyhmstrs.tridents_n_stuff.entity

import me.fzzyhmstrs.fzzy_core.entity_util.BasicCustomTridentEntity
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
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
