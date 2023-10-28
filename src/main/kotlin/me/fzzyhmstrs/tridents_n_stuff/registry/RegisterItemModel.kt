package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.model.CustomFancyTridentEntityModel
import me.fzzyhmstrs.tridents_n_stuff.model.CustomTridentEntityModel
import me.fzzyhmstrs.tridents_n_stuff.model.SpearEntityModel
import me.fzzyhmstrs.tridents_n_stuff.renderer.BuiltinEntityItemEntityRenderer
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.item.Item

object RegisterItemModel {

    private fun registerFancy(name: String, item: Item){
        val modes = ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(TNS.MOD_ID, name,"inventory"))
            .withHeld(ModelIdentifier(TNS.MOD_ID, "${name}_in_hand","inventory"), true)
        ItemModelRegistry.registerItemModelId(item, modes)
        ItemModelRegistry.registerItemEntityModel(item,
            BuiltinEntityItemEntityRenderer(item,TNS.identity("textures/entity/trident/${name}.png")),
            RegisterRenderer.CUSTOM_FANCY_TRIDENT,
            CustomFancyTridentEntityModel::class.java)
    }
    private fun registerCustom(name: String, item: Item){
        val modes = ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(TNS.MOD_ID, name,"inventory"))
            .withHeld(ModelIdentifier(TNS.MOD_ID, "${name}_in_hand","inventory"), true)
        ItemModelRegistry.registerItemModelId(item, modes)
        ItemModelRegistry.registerItemEntityModel(item,
            BuiltinEntityItemEntityRenderer(item,TNS.identity("textures/entity/trident/${name}.png")),
            RegisterRenderer.CUSTOM_TRIDENT,
            CustomTridentEntityModel::class.java)
    }

    private fun registerSpear(name: String, item: Item){
        val modes = ItemModelRegistry.ModelIdentifierPerModes(ModelIdentifier(TNS.MOD_ID, name,"inventory"))
            .withHeld(ModelIdentifier(TNS.MOD_ID, "${name}_in_hand","inventory"), true)
        ItemModelRegistry.registerItemModelId(item, modes)
        ItemModelRegistry.registerItemEntityModel(item,
            BuiltinEntityItemEntityRenderer(item,TNS.identity("textures/entity/spear/${name}.png")),
            RegisterRenderer.SPEAR,
            SpearEntityModel::class.java)
    }

    fun registerAll(){
        registerFancy("stormseeker",RegisterItem.STORMSEEKER)
        registerFancy("the_desecrator",RegisterItem.THE_DESECRATOR)
        registerFancy("echo_of_the_deep",RegisterItem.ECHO_OF_THE_DEEP)
        registerFancy("stellaris",RegisterItem.STELLARIS)
        registerFancy("sanguine_bond",RegisterItem.SANGUINE_BOND)

        registerCustom("frenzied_trident", RegisterItem.FRENZIED_TRIDENT)
        registerCustom("ancient_trident", RegisterItem.ANCIENT_TRIDENT)
        registerCustom("oceanic_trident", RegisterItem.OCEANIC_TRIDENT)
        registerCustom("holy_trident", RegisterItem.HOLY_TRIDENT)
        registerCustom("farshot_trident", RegisterItem.FARSHOT_TRIDENT)

        registerSpear("wooden_spear", RegisterItem.WOODEN_SPEAR)
        registerSpear("stone_spear", RegisterItem.STONE_SPEAR)
        registerSpear("iron_spear", RegisterItem.IRON_SPEAR)
        registerSpear("golden_spear", RegisterItem.GOLDEN_SPEAR)
        registerSpear("diamond_spear", RegisterItem.DIAMOND_SPEAR)
        registerSpear("netherite_spear", RegisterItem.NETHERITE_SPEAR)
    }


}