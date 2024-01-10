package me.fzzyhmstrs.tridents_n_stuff

import me.fzzyhmstrs.fzzy_core.coding_util.compat.FzzyDamage
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
        entity.damage(FzzyDamage.dryOut(entity), 1.5f)
    }
}