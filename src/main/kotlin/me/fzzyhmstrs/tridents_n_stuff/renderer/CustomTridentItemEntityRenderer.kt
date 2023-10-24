package me.fzzyhmstrs.tridents_n_stuff.renderer

import me.fzzyhmstrs.fzzy_core.registry.ItemModelRegistry
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.Identifier

class CustomTridentItemEntityRenderer(private val item: Item, private val texture: Identifier): BuiltinItemRendererRegistry.DynamicItemRenderer {

    private val modelLoader by lazy {
        ItemModelRegistry.getEntityModelLoader(item)
    }

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertexConsumers: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {

        val renderModel = modelLoader.getModel()
        matrices.push()
        matrices.scale(1.0f, -1.0f, -1.0f)
        val block = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumers, renderModel.getLayer(
                texture
            ), false, stack.hasGlint()
        )
        renderModel.render(matrices, block, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
        matrices.pop()
    }
}