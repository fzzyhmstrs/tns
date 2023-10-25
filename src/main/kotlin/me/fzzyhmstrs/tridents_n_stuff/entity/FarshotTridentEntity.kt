package me.fzzyhmstrs.tridents_n_stuff.entity

import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterEntity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.world.World

class FarshotTridentEntity: CustomTridentEntity {

    constructor(entityType: EntityType<out CustomTridentEntity?>?, world: World?) : super(entityType, world)
    constructor(world: World?, owner: LivingEntity?, stack: ItemStack) : super(
        RegisterEntity.FARSHOT_TRIDENT,
        world,
        owner,
        stack
    )

    override fun tick() {
        super.tick()
        val vec3d4 = velocity
        this.setVelocity(vec3d4.x, vec3d4.y + 0.03, vec3d4.z)
    }

    override fun onOwnedHit(owner: LivingEntity, target: LivingEntity){

    }

}