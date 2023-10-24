@file:Suppress("PropertyName")

package me.fzzyhmstrs.template_kotlin

import com.llamalad7.mixinextras.MixinExtrasBootstrap
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random


object TemplateKotlin: ModInitializer {
    const val MOD_ID = "template_kotlin"
    val LOGGER: Logger = LoggerFactory.getLogger("template_kotlin")
    override fun onInitialize() {
    }

    fun random(): Random{
        return Random(System.currentTimeMillis())
    }

    fun identity(path: String): Identifier{
        return Identifier(MOD_ID,path)
    }
}

@Environment(value = EnvType.CLIENT)
object TemplateKotlinClient: ClientModInitializer{

    override fun onInitializeClient() {
    }

    fun random(): Random{
        return Random(System.currentTimeMillis())
    }
}

object TemplateKotlinPreLaunch: PreLaunchEntrypoint{

    override fun onPreLaunch() {
        MixinExtrasBootstrap.init()
    }

}