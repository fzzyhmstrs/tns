package me.fzzyhmstrs.tridents_n_stuff

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory

class BleedingStatusEffect(statusEffectCategory: StatusEffectCategory, i: Int): StatusEffect(statusEffectCategory, i) {
    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        val i = 50 shr amplifier
        return if (i > 0) {
            duration % i == 0
        } else false
    }

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        entity.damage(entity.damageSources.dryOut(), 1.5f)
    }
}