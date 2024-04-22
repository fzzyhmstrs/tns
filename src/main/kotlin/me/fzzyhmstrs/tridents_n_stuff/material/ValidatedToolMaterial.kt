package me.fzzyhmstrs.tridents_n_stuff.material

import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.util.Walkable
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIngredient
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.Item
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

open class ValidatedToolMaterial protected constructor(
    durabilityDefault: ValidatedInt,
    miningSpeedDefault: ValidatedFloat,
    attackDamageDefault: ValidatedFloat,
    miningLevelDefault: ValidatedInt,
    enchantabilityDefault: ValidatedInt,
    repairIngredientDefault: ValidatedIngredient)
    :
    ToolMaterial, Walkable
{
    var durability = durabilityDefault
    var miningSpeedMultiplier = miningSpeedDefault
    var attackDamage = attackDamageDefault
    var miningLevel = miningLevelDefault
    var enchantability = enchantabilityDefault
    var repairIngredient = repairIngredientDefault

    override fun getDurability(): Int {
        return durability.get()
    }

    override fun getMiningSpeedMultiplier(): Float {
        return miningSpeedMultiplier.get()
    }

    override fun getAttackDamage(): Float {
        return attackDamage.get()
    }

    override fun getMiningLevel(): Int {
        return miningLevel.get()
    }

    override fun getEnchantability(): Int {
        return enchantability.get()
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.toIngredient()
    }

    override fun toString(): String {
        return ConfigApi.serializeConfig(this, mutableListOf())
    }

    class Builder: AbstractBuilder<ValidatedToolMaterial, Builder>() {
        override fun builderClass(): Builder{
            return this
        }
        override fun build(): ValidatedToolMaterial {
            return ValidatedToolMaterial(d, mSM, aD, mL, e, rI)
        }
    }

    abstract class AbstractBuilder<T: ValidatedToolMaterial, U: AbstractBuilder<T,U>>(){
        protected var d = ValidatedInt(1,1,0)
        protected var mSM = ValidatedFloat(1f,1f,0f)
        protected var aD = ValidatedFloat(1f,1f,0f)
        protected var mL = ValidatedInt(1,4,0)
        protected var e = ValidatedInt(1,50,0)
        protected var rI = ValidatedIngredient(setOf())

        abstract fun builderClass(): U

        fun durability(default: Int, max: Int = Short.MAX_VALUE.toInt()): U{
            d = ValidatedInt(default,max,0)
            return builderClass()
        }
        fun miningSpeedMultiplier(default: Float, max: Float = 20f): U{
            mSM = ValidatedFloat(default, max, 1f)
            return builderClass()
        }
        fun attackDamage(default: Float, max: Float = 50f): U{
            aD = ValidatedFloat(default,max,0f)
            return builderClass()
        }
        fun miningLevel(default: Int, max: Int = MiningLevels.NETHERITE): U{
            mL = ValidatedInt(default,max,0)
            return builderClass()
        }
        fun enchantability(default: Int, max: Int = 50): U{
            e = ValidatedInt(default,max,1)
            return builderClass()
        }
        fun repairIngredient(ingredient: Identifier): U{
            rI = ValidatedIngredient(ingredient)
            return builderClass()
        }
        fun repairIngredient(ingredient: Set<Identifier>): U{
            rI = ValidatedIngredient(ingredient)
            return builderClass()
        }
        fun repairIngredient(ingredient: TagKey<Item>): U{
            rI = ValidatedIngredient(ingredient)
            return builderClass()
        }
        abstract fun build(): T
    }
}