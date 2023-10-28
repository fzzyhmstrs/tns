package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey

object RegisterTag {

    val UNIQUE_TRIDENTS = TagKey.of(RegistryKeys.ITEM, TNS.identity("unique_tridents"))

    fun registerAll(){}

}