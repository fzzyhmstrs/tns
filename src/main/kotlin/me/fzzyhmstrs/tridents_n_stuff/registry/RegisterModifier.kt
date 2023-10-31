package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.tridents_n_stuff.modifier.ConfigEquipmentModifier
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object RegisterModifier {

    private val regMod: MutableList<AbstractModifier<*>> = mutableListOf()

    private val CHEAP_TOLL = ConstantLootNumberProvider.create(3f) // 96% probability
    // private val NORMAL_TOLL = ConstantLootNumberProvider.create(5f) //75% probability
    private val EXPENSIVE_TOLL = UniformLootNumberProvider.create(6f,7f) //50% chance of enough toll
    private val VERY_EXPENSIVE_TOLL = UniformLootNumberProvider.create(7f,9f) //25% chance of toll

    private fun buildModifier(
        modifierId: Identifier,
        target: EquipmentModifier.EquipmentModifierTarget = EquipmentModifier.EquipmentModifierTarget.NONE,
        weight: Int = 10,
        rarity: EquipmentModifier.Rarity = EquipmentModifier.Rarity.COMMON,
        persistent: Boolean = false,
        availableForSelection: Boolean = true
    ): EquipmentModifier {
        return ConfigEquipmentModifier(modifierId,target,weight,rarity,persistent, availableForSelection)
    }

    fun registerAll(){

    }

    val SANGUINE = buildModifier(TNS.identity("sanguine"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.SANGUINE_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.RED, Formatting.BOLD)
        .also { regMod.add(it) }

    val HOLY = buildModifier(TNS.identity("holy"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.HOLY_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.YELLOW, Formatting.BOLD)
        .also { regMod.add(it) }

    val OCEANIC = buildModifier(TNS.identity("oceanic"), persistent = true, randomSelectable = false)
        .withOnAttack(ModifierFunctions.OCEANIC_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.AQUA, Formatting.BOLD)
        .also { regMod.add(it) }

}
