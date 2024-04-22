package me.fzzyhmstrs.tridents_n_stuff.config

import me.fzzyhmstrs.fzzy_config.annotations.ConvertFrom
import me.fzzyhmstrs.fzzy_config.api.ConfigApi
import me.fzzyhmstrs.fzzy_config.config.Config
import me.fzzyhmstrs.fzzy_config.config.ConfigSection
import me.fzzyhmstrs.fzzy_config.validation.Shorthand.validatedIds
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedList
import me.fzzyhmstrs.fzzy_config.validation.minecraft.ValidatedIdentifier
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedDouble
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedFloat
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.material.TnsTridentMaterialsConfig
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.entity.EntityType
import net.minecraft.loot.LootTables
import net.minecraft.registry.Registries
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object TnsConfig {

    @ConvertFrom("items_v0.json",TNS.MOD_ID)
    class Items: Config(TNS.identity("items_config")) {

        var harpoon = Harpoon()
        class Harpoon: ConfigSection(){
            var boneBaseDamage = ValidatedDouble(5.0,50.0,0.0)
            var prismarineBaseDamage = ValidatedDouble(6.0,50.0,0.0)
            var diamondBaseDamage = ValidatedDouble(7.0,50.0,0.0)
            var powerAdderPerLevel = ValidatedDouble(1.25,12.5,0.0)
            var powerBaseAdder = ValidatedDouble(1.0,10.0,0.0)
        }

        var oceanBossLootTables = ValidatedList(listOf(EntityType.ELDER_GUARDIAN.lootTableId), Registries.ENTITY_TYPE.validatedIds())

        var oceanChestLootTables = ValidatedList(
            listOf(
                LootTables.SHIPWRECK_SUPPLY_CHEST,
                LootTables.SHIPWRECK_MAP_CHEST,
                LootTables.SHIPWRECK_TREASURE_CHEST,
                LootTables.UNDERWATER_RUIN_SMALL_CHEST,
                LootTables.UNDERWATER_RUIN_BIG_CHEST,
                LootTables.RUINED_PORTAL_CHEST,
                LootTables.BURIED_TREASURE_CHEST,
                Identifier("mostructures","pirate_ship_cargo_misc")
            ), ValidatedIdentifier())

        var witherUniqueChance = ValidatedFloat(0.01f,1f,0f)
        var oceanBossUniqueChance = ValidatedFloat(0.005f,1f,0f)
        var chestUniqueChance = ValidatedFloat(0.0005f,1f,0f)
        var basicHarpoonChance = ValidatedFloat(0.1f,1f,0f)
        var oceanHarpoonChance = ValidatedFloat(0.1f,1f,0f)
        var strongHarpoonChance = ValidatedFloat(0.075f,1f,0f)
    }

    @ConvertFrom("materials_v0.json",TNS.MOD_ID)
    class Materials: Config(TNS.identity("materials_config")){

        var slumbering = TnsTridentMaterialsConfig.SLUMBERING
        var stormseeker = TnsTridentMaterialsConfig.STORMSEEKER
        var desecrator = TnsTridentMaterialsConfig.DESECRATOR
        var echo = TnsTridentMaterialsConfig.ECHO
        var stellaris = TnsTridentMaterialsConfig.STELLARIS
        var sanguine = TnsTridentMaterialsConfig.SANGUINE

        var frenzied = TnsTridentMaterialsConfig.FRENZIED
        var ancient = TnsTridentMaterialsConfig.ANCIENT
        var oceanic = TnsTridentMaterialsConfig.OCEANIC
        var holy = TnsTridentMaterialsConfig.HOLY
        var farshot = TnsTridentMaterialsConfig.FARSHOT
    }

    @ConvertFrom("modifiers_v0.json",TNS.MOD_ID)
    class Modifiers: Config(TNS.identity("modifiers_config")){

        fun isModifierEnabled(id: Identifier): Boolean{
            return !disabledModifiers.contains(id.toString())
        }

        var disabledModifiers = ValidatedList.ofString()
    }

    var items: Items = ConfigApi.registerAndLoadConfig({ Items() })
    var materials: Materials = ConfigApi.registerAndLoadConfig({ Materials() })
    var modifiers: Modifiers = ConfigApi.registerAndLoadConfig({ Modifiers() })

    fun initConfig() {
    }
}