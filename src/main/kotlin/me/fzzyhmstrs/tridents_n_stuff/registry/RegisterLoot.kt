package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.registry.LootRegistry.registerModLoot
import me.fzzyhmstrs.tridents_n_stuff.loot.UniqueLoot
import me.fzzyhmstrs.tridents_n_stuff.loot.VanillaLoot

object RegisterLoot {

    fun registerAll() {
        registerModLoot(UniqueLoot)
        registerModLoot(VanillaLoot)
    }

}