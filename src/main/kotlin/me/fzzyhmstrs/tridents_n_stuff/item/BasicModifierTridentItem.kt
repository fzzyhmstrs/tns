package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.fzzy_core.modifier_util.ModifierHelperType
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifierHelper
import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import net.minecraft.item.ToolMaterial
import net.minecraft.util.Identifier

class BasicModifierTridentItem(material: ToolMaterial, attackSpeed: Double = -2.9, private val modifier: Identifier, settings: Settings, entityBuilder: EntityBuilder<CustomTridentEntity>) :
    BasicCustomTridentItem(material, attackSpeed, settings, entityBuilder) {

    override fun defaultModifiers(type: ModifierHelperType<*>?): MutableList<Identifier> {
        if (type == EquipmentModifierHelper.getType()) return mutableListOf(modifier)
        return super.defaultModifiers(type)
    }
}
