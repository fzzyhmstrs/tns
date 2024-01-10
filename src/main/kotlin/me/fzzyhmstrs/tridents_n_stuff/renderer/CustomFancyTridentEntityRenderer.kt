package me.fzzyhmstrs.tridents_n_stuff.renderer

import me.fzzyhmstrs.fzzy_core.coding_util.compat.FzzyRotation
import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.model.CustomFancyTridentEntityModel
import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterRenderer
import net.minecraft.client.render.OverlayTexture
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRenderer
import net.minecraft.client.render.entity.EntityRendererFactory
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.Identifier
import net.minecraft.util.math.MathHelper

@Suppress("PrivatePropertyName")
class CustomFancyTridentEntityRenderer<T: CustomTridentEntity>(private val texture: Identifier, context: EntityRendererFactory.Context) : EntityRenderer<T>(context) {
    var model = CustomFancyTridentEntityModel(context.getPart(RegisterRenderer.CUSTOM_FANCY_TRIDENT))

    override fun render(
        tridentEntity: T,
        f: Float,
        g: Float,
        matrixStack: MatrixStack,
        vertexConsumerProvider: VertexConsumerProvider,
        i: Int
    ) {
        matrixStack.push()
        matrixStack.multiply(
            FzzyRotation.POSITIVE_Y.degrees(
                MathHelper.lerp(
                    g,
                    tridentEntity.prevYaw,
                    tridentEntity.yaw
                ) - 90.0f
            )
        )
        matrixStack.multiply(
            FzzyRotation.POSITIVE_Z.degrees(
                MathHelper.lerp(
                    g,
                    tridentEntity.prevPitch,
                    tridentEntity.pitch
                ) + 90.0f
            )
        )
        val vertexConsumer = ItemRenderer.getDirectItemGlintConsumer(
            vertexConsumerProvider,
            model.getLayer(getTexture(tridentEntity)),
            false,
            tridentEntity.isEnchanted
        )
        model.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0f, 1.0f, 1.0f, 1.0f)
        matrixStack.pop()
        super.render(tridentEntity, f, g, matrixStack, vertexConsumerProvider, i)
    }

    override fun getTexture(entity: T): Identifier {
        return texture
    }
}