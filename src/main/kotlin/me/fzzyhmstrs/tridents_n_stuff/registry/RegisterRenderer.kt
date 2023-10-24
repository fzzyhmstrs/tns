package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object RegisterRenderer {

    val CUSTOM_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_trident"),"custom_trident_model")
    val CUSTOM_FANCY_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_fancy_trident"),"custom_fancy_trident_model")

     EntityRendererRegistry.register(
            RegisterEntity.STORMSEEKER
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<StormseekerTridentEntity>(context) }


    fun registerAll(){
        for (item in RegisterItem.getItems()){
            if (!(item is CustomTridentItem || item is HarpoonItem || item is SpearItem)) continue
            ModelPredicateProviderRegistry.register(
                item, Identifier("throwing")
            ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int -> if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f }
        }
    }

}
