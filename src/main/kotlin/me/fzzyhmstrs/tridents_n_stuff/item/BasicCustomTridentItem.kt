package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import net.minecraft.data.client.BlockStateVariantMap.TriFunction
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

class BasicCustomTridentItem(material: ToolMaterial, attackSpeed: Double = -2.9, settings: Settings, private val entityBuilder: EntityBuilder<CustomTridentEntity>) :
    CustomTridentItem<CustomTridentEntity>(material, attackSpeed, settings) {
    override fun makeTridentEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): CustomTridentEntity {
        return entityBuilder.build(world, livingEntity, stack).also { it.setDamage(material) }
    }

    @FunctionalInterface
    fun interface EntityBuilder<T: CustomTridentEntity>{
        fun build(world: World,livingEntity: LivingEntity,stack: ItemStack): T
    }

}