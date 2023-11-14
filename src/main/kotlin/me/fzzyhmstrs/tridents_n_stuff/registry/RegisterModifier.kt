package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.modifier_util.AbstractModifier
import me.fzzyhmstrs.fzzy_core.registry.ModifierRegistry
import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.modifier.ConfigEquipmentModifier
import me.fzzyhmstrs.tridents_n_stuff.modifier.ModifierConsumers
import me.fzzyhmstrs.tridents_n_stuff.modifier.ModifierFunctions
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Formatting
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

    val STORM_BLESSED = buildModifier(TNS.identity("storm_blessed"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.STORM_BLESSED_KILL_CONSUMER)
        .withCustomFormatting(Formatting.BLUE)
        .also { regMod.add(it) }

    val VILE = buildModifier(TNS.identity("vile"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.VILE_HIT_CONSUMER)
        .withKilledOther(ModifierConsumers.VILE_KILL_CONSUMER)
        .withCustomFormatting(Formatting.DARK_RED)
        .also { regMod.add(it) }

    val ECHOING = buildModifier(TNS.identity("echoing"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.ECHO_HIT_CONSUMER)
        .withCustomFormatting(Formatting.AQUA)
        .also { regMod.add(it) }

    val SANGUINE = buildModifier(TNS.identity("sanguine"), persistent = true, availableForSelection = false)
        .withOnAttack(ModifierFunctions.SANGUINE_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.RED, Formatting.BOLD)
        .also { regMod.add(it) }

    val HOLY = buildModifier(TNS.identity("holy"), persistent = true, availableForSelection = false)
        .withOnAttack(ModifierFunctions.HOLY_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.YELLOW, Formatting.BOLD)
        .also { regMod.add(it) }

    val OCEANIC = buildModifier(TNS.identity("oceanic"), persistent = true, availableForSelection = false)
        .withOnAttack(ModifierFunctions.OCEANIC_ATTACK_FUNCTION)
        .withCustomFormatting(Formatting.AQUA, Formatting.BOLD)
        .also { regMod.add(it) }

    val STELLAR = buildModifier(TNS.identity("stellar"), persistent = true, availableForSelection = false)
        .withPostHit(ModifierConsumers.STELLAR_HIT_CONSUMER)
        .withCustomFormatting(Formatting.DARK_PURPLE, Formatting.BOLD)
        .also { regMod.add(it) }

    fun registerAll(){
        for (mod in regMod){
            ModifierRegistry.register(mod)
        }
    }



}
