package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.tridents_n_stuff.entity.StormseekerTridentEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

class StormseekerTridentItem(material: ToolMaterial, attackSpeed: Double = -2.9, settings: Settings) :
    CustomTridentItem<StormseekerTridentEntity>(material, attackSpeed, settings) {
    override fun makeTridentEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): StormseekerTridentEntity {
        return StormseekerTridentEntity(world,livingEntity,stack).also { it.setDamage(material) }
    }
}