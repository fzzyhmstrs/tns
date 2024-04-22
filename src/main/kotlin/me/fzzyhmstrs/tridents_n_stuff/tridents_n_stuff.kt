@file:Suppress("PropertyName")

package me.fzzyhmstrs.tridents_n_stuff

import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import me.fzzyhmstrs.tridents_n_stuff.registry.*
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.api.EnvType
import net.fabricmc.api.Environment
import net.fabricmc.api.ModInitializer
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory


object TNS: ModInitializer {
    const val MOD_ID = "tridents_n_stuff"
    val LOGGER: Logger = LoggerFactory.getLogger("tridents_n_stuff")

    val BLEEDING = Registry.register(Registries.STATUS_EFFECT, Identifier(MOD_ID,"bleeding"), BleedingStatusEffect(StatusEffectCategory.HARMFUL,0x7C002A))
    override fun onInitialize() {
        RegisterItem.registerAll()
        RegisterModifier.registerAll()
        RegisterEntity.registerAll()
        RegisterTag.registerAll()
        RegisterLoot.registerAll()
        TnsConfig.initConfig()
    }

    fun identity(path: String): Identifier{
        return Identifier(MOD_ID,path)
    }
}

@Environment(value = EnvType.CLIENT)
object TNSClient: ClientModInitializer {

    override fun onInitializeClient() {
        RegisterRenderer.registerAll()
        RegisterItemModel.registerAll()
    }
}