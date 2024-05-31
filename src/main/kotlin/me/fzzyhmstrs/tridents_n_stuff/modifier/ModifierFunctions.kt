package me.fzzyhmstrs.tridents_n_stuff.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.effect.StatusEffectInstance
import kotlin.math.min

object ModifierFunctions {

    val SANGUINE_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, attacker, _, amount ->
            if (attacker == null) return@DamageFunction amount
            val bleed = attacker.getStatusEffect(TNS.BLEEDING)
            val bleedingAmp = bleed?.amplifier ?: -1
            val bleedingMultiplier = (bleedingAmp * 0.5f) + 1.5f
            attacker.addStatusEffect(StatusEffectInstance(TNS.BLEEDING,100,min(3,bleedingAmp + 1)))
            user.heal(2f * bleedingMultiplier)
            amount * bleedingMultiplier
        }

    val HOLY_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, attacker, _, amount ->
            if (attacker?.group == EntityGroup.UNDEAD)
                return@DamageFunction amount * 1.5f
            amount
        }

    val OCEANIC_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, _, attacker, _, amount ->
            if (attacker?.group == EntityGroup.AQUATIC || attacker?.type == EntityType.DROWNED)
                return@DamageFunction amount * 1.5f
            amount
        }

}