package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.tridents_n_stuff.entity.DesecratorTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.StormseekerTridentEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

class DesecratorTridentItem(material: ToolMaterial, attackSpeed: Double = -2.9, settings: Settings) :
    CustomTridentItem<DesecratorTridentEntity>(material, attackSpeed, settings) {
    override fun makeTridentEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): DesecratorTridentEntity {
        return DesecratorTridentEntity(world,livingEntity,stack).also { it.setDamage(material) }
    }
}