@file:Suppress("PropertyName")

package me.fzzyhmstrs.tridents_n_stuff

import com.llamalad7.mixinextras.MixinExtrasBootstrap
import me.fzzyhmstrs.tridents_n_stuff.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.random.Random


object TNS: ModInitializer {
    const val MOD_ID = "tridents_n_stuff"
    val LOGGER: Logger = LoggerFactory.getLogger("tridents_n_stuff")
    override fun onInitialize() {
        RegisterItem.registerAll()
        RegisterModifier.registerAll()
        RegisterEntity.registerAll()
    }

    fun random(): Random{
        return Random(System.currentTimeMillis())
    }

    fun identity(path: String): Identifier{
        return Identifier(MOD_ID,path)
    }
}

@Environment(value = EnvType.CLIENT)
object TNSClient: ClientModInitializer{

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
        RegisterItemModel.registerAll()
    }

    fun random(): Random{
        return Random(System.currentTimeMillis())
    }
}

object TNSPreLaunch: PreLaunchEntrypoint{

    override fun onPreLaunch() {
        MixinExtrasBootstrap.init()
    }

}