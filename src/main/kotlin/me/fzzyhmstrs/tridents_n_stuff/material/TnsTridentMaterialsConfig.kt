package me.fzzyhmstrs.tridents_n_stuff.material

import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedToolMaterial
import net.fabricmc.yarn.constants.MiningLevels
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient

object TnsTridentMaterialsConfig {

    val STORMSEEKER = ValidatedToolMaterial.Builder()
        .durability(350)
        .miningSpeedMultiplier(8f)
        .attackDamage(9f)
        .miningLevel(MiningLevels.DIAMOND)
        .enchantability(15)
        .repairIngredient(Ingredient.ofItems(Items.CONDUIT))
        .build()
}