package me.fzzyhmstrs.imbued_gear.modifier

import me.fzzyhmstrs.gear_core.modifier_util.EquipmentModifier
import me.fzzyhmstrs.imbued_gear.config.IgConfig
import me.fzzyhmstrs.imbued_gear.registry.RegisterStatus
import me.fzzyhmstrs.imbued_gear.registry.RegisterTag
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.registry.tag.EntityTypeTags
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import java.util.*
import kotlin.math.max

object ModifierFunctions {

    val SANGUINE_ATTACK_FUNCTION: EquipmentModifier.DamageFunction =
        EquipmentModifier.DamageFunction { _, user, attacker, _, amount ->
            if (attacker == null) return@DamageFunction amount
            val bleed = attacker.getStatusEffect(TNS.BLEEDING)
            val bleedingAmp = bleed?.amplifier ?: -1
            val bleedingMultiplier = (bleedingAmp * 0.5f) + 1.5f
            attacker.addStatusEffect(StatusEffectInstance(TNS.BLEEDING,100,max(3,bleedingAmp + 1)))
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
            if (attacker?.group == EntityGroup.AQUATIC)
                return@DamageFunction amount * 1.5f
            amount
        }
  
}
