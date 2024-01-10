package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.tridents_n_stuff.TNS

object RegisterTag {

    val UNIQUE_TRIDENTS = FzzyPort.ITEM.tagOf(TNS.identity("unique_tridents"))
    val ICONS = FzzyPort.ITEM.tagOf(TNS.identity("icons"))

    fun registerAll(){}

}
