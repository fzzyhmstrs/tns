package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.util.Identifier

object RegisterRenderer {

    val CUSTOM_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_trident"),"custom_trident_model")
    val CUSTOM_FANCY_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_fancy_trident"),"custom_fancy_trident_model")


    fun registerAll(){}

}