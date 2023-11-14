package me.fzzyhmstrs.tridents_n_stuff.modifier

import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifierHelper
import me.fzzyhmstrs.gear_core.modifier_util.ConfigEquipmentModifier
import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import net.minecraft.util.Identifier

class ConfigEquipmentModifier(
    modifierId: Identifier = AbstractModifierHelper.BLANK,
    target: EquipmentModifierTarget = EquipmentModifierTarget.NONE,
    weight: Int = 10,
    rarity: Rarity = Rarity.COMMON,
    persistent: Boolean = false,
    availableForSelection: Boolean = true)
: ConfigEquipmentModifier(
    modifierId,
    target,
    weight,
    rarity,
    persistent,
    availableForSelection)
{
    override fun isEnabled(): Boolean{
        return TnsConfig.modifiers.isModifierEnabled(modifierId)
    }
}
