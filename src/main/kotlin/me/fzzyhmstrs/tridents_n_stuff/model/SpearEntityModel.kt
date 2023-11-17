package me.fzzyhmstrs.tridents_n_stuff.model

import net.minecraft.client.model.*
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.entity.model.TridentEntityModel
import net.minecraft.client.util.math.MatrixStack

class SpearEntityModel(private val root: ModelPart) : TridentEntityModel(root) {


    companion object {

        fun getTexturedModelData(): TexturedModelData {
            val modelData = ModelData()
            val modelPartData = modelData.root
            val modelPartData2 = modelPartData.addChild(
                "pole",
                ModelPartBuilder.create().uv(0, 6).cuboid(-0.5f, 2.0f, -0.5f, 1.0f, 25.0f, 1.0f),
                ModelTransform.NONE
            )
            modelPartData2.addChild(
                "base_left",
                ModelPartBuilder.create().uv(4, 0).cuboid(-2.5f, 4.0f, -0.5f, 2.0f, 1.0f, 1.0f),
                ModelTransform.NONE
            )
            modelPartData2.addChild(
                "base_right",
                ModelPartBuilder.create().uv(10, 0).cuboid(0.5f, 4.0f, -0.5f, 2.0f, 1.0f, 1.0f),
                ModelTransform.NONE
            )
            modelPartData2.addChild(
                "left_spike",
                ModelPartBuilder.create().uv(4, 2).cuboid(-1.5f, -1.0f, -0.5f, 1.0f, 3.0f, 1.0f),
                ModelTransform.NONE
            )
            modelPartData2.addChild(
                "middle_spike",
                ModelPartBuilder.create().uv(0, 0).cuboid(-0.5f, -3.0f, -0.5f, 1.0f, 5.0f, 1.0f),
                ModelTransform.NONE
            )
            modelPartData2.addChild(
                "right_spike",
                ModelPartBuilder.create().uv(4, 2).mirrored().cuboid(0.5f, -1.0f, -0.5f, 1.0f, 3.0f, 1.0f),
                ModelTransform.NONE
            )
            return TexturedModelData.of(modelData, 32, 32)
        }
    }

    override fun render(
        matrices: MatrixStack,
        vertices: VertexConsumer,
        light: Int,
        overlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        root.render(matrices, vertices, light, overlay, red, green, blue, alpha)
    }


}
