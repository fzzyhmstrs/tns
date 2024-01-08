package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.item_util.BasicCustomTridentItem
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.entity.*
import me.fzzyhmstrs.tridents_n_stuff.item.HarpoonLauncherItem
import me.fzzyhmstrs.tridents_n_stuff.item.SpearItem
import me.fzzyhmstrs.tridents_n_stuff.model.CustomFancyTridentEntityModel
import me.fzzyhmstrs.tridents_n_stuff.model.CustomTridentEntityModel
import me.fzzyhmstrs.tridents_n_stuff.model.SpearEntityModel
import me.fzzyhmstrs.tridents_n_stuff.renderer.CustomFancyTridentEntityRenderer
import me.fzzyhmstrs.tridents_n_stuff.renderer.CustomTridentEntityRenderer
import me.fzzyhmstrs.tridents_n_stuff.renderer.HarpoonEntityRenderer
import me.fzzyhmstrs.tridents_n_stuff.renderer.SpearEntityRenderer
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry
import net.minecraft.client.item.ModelPredicateProviderRegistry
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.entity.model.EntityModelLayer
import net.minecraft.client.world.ClientWorld
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

object RegisterRenderer {

    val CUSTOM_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_trident"),"custom_trident_model")
    val CUSTOM_FANCY_TRIDENT: EntityModelLayer = EntityModelLayer(TNS.identity("custom_fancy_trident"),"custom_fancy_trident_model")
    val SPEAR: EntityModelLayer = EntityModelLayer(TNS.identity("spear"),"spear_model")


    fun registerAll(){

        EntityRendererRegistry.register(
            RegisterEntity.SLUMBERING_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/slumbering_trident.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.STORMSEEKER
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/stormseeker.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.THE_DESECRATOR
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/the_desecrator.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.ECHO_OF_THE_DEEP
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/echo_of_the_deep.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.STELLARIS
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/stellaris.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.SANGUINE_BOND
        ){ context: EntityRendererFactory.Context -> CustomFancyTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/sanguine_bond.png"),context) }

        //////////

        EntityRendererRegistry.register(
            RegisterEntity.FRENZIED_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/frenzied_trident.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.ANCIENT_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/ancient_trident.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.OCEANIC_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/oceanic_trident.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.HOLY_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomTridentEntityRenderer<CustomTridentEntity>(TNS.identity("textures/trident/holy_trident.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.FARSHOT_TRIDENT
        ){ context: EntityRendererFactory.Context -> CustomTridentEntityRenderer<FarshotTridentEntity>(TNS.identity("textures/trident/farshot_trident.png"),context) }

        ///////////

        EntityRendererRegistry.register(
            RegisterEntity.WOODEN_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/wooden_spear.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.STONE_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/stone_spear.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.IRON_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/iron_spear.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.GOLDEN_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/golden_spear.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.DIAMOND_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/diamond_spear.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.NETHERITE_SPEAR
        ){ context: EntityRendererFactory.Context -> SpearEntityRenderer<SpearEntity>(TNS.identity("textures/spear/netherite_spear.png"),context) }

        ///////////

        EntityRendererRegistry.register(
            RegisterEntity.BONE_HARPOON
        ){ context: EntityRendererFactory.Context -> HarpoonEntityRenderer(TNS.identity("textures/harpoon/bone_harpoon.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.PRISMARINE_HARPOON
        ){ context: EntityRendererFactory.Context -> HarpoonEntityRenderer(TNS.identity("textures/harpoon/prismarine_harpoon.png"),context) }

        EntityRendererRegistry.register(
            RegisterEntity.DIAMOND_HARPOON
        ){ context: EntityRendererFactory.Context -> HarpoonEntityRenderer(TNS.identity("textures/harpoon/diamond_harpoon.png"),context) }

        /////////////

        EntityModelLayerRegistry.registerModelLayer(CUSTOM_TRIDENT,CustomTridentEntityModel::getTexturedModelData)
        EntityModelLayerRegistry.registerModelLayer(CUSTOM_FANCY_TRIDENT,CustomFancyTridentEntityModel::getTexturedModelData)
        EntityModelLayerRegistry.registerModelLayer(SPEAR,SpearEntityModel::getTexturedModelData)

        /////////////

        for (item in RegisterItem.getItems()){
            if (!(item is BasicCustomTridentItem<*> || item is HarpoonLauncherItem || item is SpearItem)) continue
            ModelPredicateProviderRegistry.register(
                item, Identifier("throwing")
            ) { stack: ItemStack, _: ClientWorld?, entity: LivingEntity?, _: Int -> if (entity != null && entity.isUsingItem && entity.activeItem == stack) 1.0f else 0.0f }
        }
        ModelPredicateProviderRegistry.register(
            RegisterItem.STORMSEEKER, Identifier("crackling")
        ) { stack: ItemStack, _: ClientWorld?, _: LivingEntity?, _: Int ->
            val nbt = stack.nbt
            if (nbt == null){
                0.0f
            } else {
                if (nbt.getBoolean("crackling")){
                    1f
                } else {
                    0f
                }
            }
        }
    }

}
