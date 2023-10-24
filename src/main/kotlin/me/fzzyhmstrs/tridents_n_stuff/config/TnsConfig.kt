package me.fzzyhmstrs.tridents_n_stuff.config

import me.fzzyhmstrs.fzzy_config.config_util.*
import me.fzzyhmstrs.fzzy_config.interfaces.OldClass
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedFloat
import me.fzzyhmstrs.fzzy_config.validated_field.ValidatedInt
import me.fzzyhmstrs.fzzy_config.validated_field.list.ValidatedStringList
import me.fzzyhmstrs.fzzy_config.validated_field.map.ValidatedStringBoolMap
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.material.TnsTridentMaterialsConfig
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.resource.ResourceManager
import net.minecraft.resource.ResourceType
import net.minecraft.util.Identifier

object TnsConfig:
    SyncedConfigWithReadMe(
        TNS.MOD_ID,
        "README.txt",
        TNS.MOD_ID,
        Header.Builder()
            .box("tns.readme.main_header.title")
            .space()
            .add("tns.readme.main_header.changelog")
            .literal()
            .add("0.1.0+1.20.1: Initial release of Tridents 'n' Stuff.")
            .space()
            .translate()
            .add("tns.readme.main_header.note")
            .space()
            .space()
            .build()), SimpleSynchronousResourceReloadListener
{

    private val materialsHeader = buildSectionHeader("materials")

    class Materials: ConfigClass(materialsHeader), OldClass<Materials>{

        var stormseeker = TnsTridentMaterialsConfig.STORMSEEKER

        override fun generateNewClass(): Materials {
            return this
        }

    }

    private val modifiersHeader = buildSectionHeader("modifiers")

    class Modifiers: ConfigClass(modifiersHeader), OldClass<Modifiers>{

        fun isModifierEnabled(id: Identifier): Boolean{
            return !disabledModifiers.contains(id.toString())
        }

        var disabledModifiers = ValidatedStringList(
            listOf(),
            {str -> Identifier.tryParse(str) != null},
            invalidEntryMessage = "Needs to be a valid modifier identifier string"
        )

        override fun generateNewClass(): Modifiers {
            return this
        }

    }

    var materials = SyncedConfigHelperV1.readOrCreateAndValidate("materials_v0.json", base = TNS.MOD_ID) { Materials() }
    var modifiers = SyncedConfigHelperV1.readOrCreateAndValidate("modifiers_v0.json", base = TNS.MOD_ID) { Modifiers() }

    private fun buildSectionHeader(name:String): Header{
        return Header.Builder().space().underoverscore("tns.readme.header.$name").add("tns.readme.header.$name.desc").space().build()
    }

    override fun initConfig() {
        super.initConfig()
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(this)
    }

    override fun reload(manager: ResourceManager?) {
        materials = SyncedConfigHelperV1.readOrCreateAndValidate("materials_v0.json", base = TNS.MOD_ID) { Materials() }
        modifiers = SyncedConfigHelperV1.readOrCreateAndValidate("modifiers_v0.json", base = TNS.MOD_ID) { Modifiers() }

    }

    override fun getFabricId(): Identifier {
        return TNS.identity("tns_configuration")
    }
}