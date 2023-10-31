package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.tridents_n_stuff.entity.DesecratorTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.StormseekerTridentEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.world.World

class BasicModifierTridentItem(material: ToolMaterial, attackSpeed: Double = -2.9, private val modifier: Identifier, settings: Settings, entityBuilder: EntityBuilder<CustomTridentEntity>) :
    BasicCustomTridentItem(material, attackSpeed, settings, entityBuilder), Modifiable {

    override fun defaultModifiers(type: ModifierHelperType<*>?): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) return mutableListOf(modifier)
        return super.defaultModifiers(type)
    }
}
