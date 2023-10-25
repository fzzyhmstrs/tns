package me.fzzyhmstrs.tridents_n_stuff.entity

import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class DesecratorTridentEntity: CustomTridentEntity {

    constructor(entityType: EntityType<out CustomTridentEntity?>?, world: World?) : super(entityType, world)
    constructor(world: World?, owner: LivingEntity?, stack: ItemStack) : super(
        RegisterEntity.THE_DESECRATOR,
        world,
        owner,
        stack
    )

    override fun onOwnedHit(owner: LivingEntity, target: LivingEntity){

    }

}