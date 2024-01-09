package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.fzzy_core.coding_util.FzzyPort
import me.fzzyhmstrs.fzzy_core.item_util.CustomFlavorItem
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.FarshotTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.item.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Rarity

object RegisterItem {

    private val regItem: MutableList<Item> = mutableListOf()

    private fun <T: Item> register(item: T, name: String): T{
        regItem.add(item)
        return FzzyPort.ITEM.register(TNS.identity(name), item)
    }
    // unique tridents
    val SLUMBERING_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.slumbering,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.SLUMBERING_TRIDENT, w,e,s) }, "slumbering_trident")
    val STORMSEEKER = register(BasicModifierTridentItem(TnsConfig.materials.stormseeker,-2.9, RegisterModifier.STORM_BLESSED.modifierId, FabricItemSettings().rarity(Rarity.RARE)) {w,e,s -> CustomTridentEntity(RegisterEntity.STORMSEEKER, w,e,s) }, "stormseeker")
    val THE_DESECRATOR = register(BasicModifierTridentItem(TnsConfig.materials.desecrator,-2.9, RegisterModifier.VILE.modifierId, FabricItemSettings().rarity(Rarity.RARE)) {w,e,s -> CustomTridentEntity(RegisterEntity.THE_DESECRATOR, w,e,s) }, "the_desecrator")
    val ECHO_OF_THE_DEEP = register(BasicModifierTridentItem(TnsConfig.materials.echo,-2.9, RegisterModifier.ECHOING.modifierId, FabricItemSettings().rarity(Rarity.RARE)) {w,e,s -> CustomTridentEntity(RegisterEntity.ECHO_OF_THE_DEEP, w,e,s) }, "echo_of_the_deep")
    val STELLARIS = register(BasicModifierTridentItem(TnsConfig.materials.stellaris,-2.9, RegisterModifier.STELLAR.modifierId, FabricItemSettings().rarity(Rarity.RARE)) {w,e,s -> CustomTridentEntity(RegisterEntity.STELLARIS, w,e,s) }, "stellaris")
    val SANGUINE_BOND = register(BasicModifierTridentItem(TnsConfig.materials.sanguine,-2.9, RegisterModifier.SANGUINE.modifierId, FabricItemSettings().rarity(Rarity.RARE)) {w,e,s -> CustomTridentEntity(RegisterEntity.SANGUINE_BOND, w,e,s) }, "sanguine_bond")

    //icons
    val ICON_OF_STORMS = register(CustomFlavorItem(FabricItemSettings().rarity(Rarity.UNCOMMON)),"icon_of_storms")
    val ICON_OF_EVIL = register(CustomFlavorItem(FabricItemSettings().rarity(Rarity.UNCOMMON)),"icon_of_evil")
    val ICON_OF_DEPTHS = register(CustomFlavorItem(FabricItemSettings().rarity(Rarity.UNCOMMON)),"icon_of_depths")
    val ICON_OF_STARS = register(CustomFlavorItem(FabricItemSettings().rarity(Rarity.UNCOMMON)),"icon_of_stars")
    val ICON_OF_BLOOD = register(CustomFlavorItem(FabricItemSettings().rarity(Rarity.UNCOMMON)),"icon_of_blood")
    
    //crafted tridents
    val FRENZIED_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.frenzied,-1.6,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.FRENZIED_TRIDENT, w,e,s) }, "frenzied_trident")
    val ANCIENT_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.ancient,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.ANCIENT_TRIDENT, w,e,s) }, "ancient_trident")
    val OCEANIC_TRIDENT = register(BasicModifierTridentItem(TnsConfig.materials.oceanic,-2.9, RegisterModifier.OCEANIC.modifierId, FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.OCEANIC_TRIDENT, w,e,s) }, "oceanic_trident")
    val HOLY_TRIDENT = register(BasicModifierTridentItem(TnsConfig.materials.holy,-2.9, RegisterModifier.HOLY.modifierId, FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.HOLY_TRIDENT, w,e,s)}, "holy_trident")
    val FARSHOT_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.farshot,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> FarshotTridentEntity(w,e,s) }, "farshot_trident")

    //Harpoon stuff
    val HARPOON_LAUNCHER = register(HarpoonLauncherItem(Item.Settings().maxDamage(325).rarity(Rarity.COMMON)),"harpoon_launcher")
    val BONE_HARPOON = register(HarpoonItem(TnsConfig.items.harpoon.boneBaseDamage.get(),RegisterEntity.BONE_HARPOON,Item.Settings()),"bone_harpoon")
    val PRISMARINE_HARPOON = register(HarpoonItem(TnsConfig.items.harpoon.prismarineBaseDamage.get(),RegisterEntity.PRISMARINE_HARPOON,Item.Settings()),"prismarine_harpoon")
    val DIAMOND_HARPOON = register(HarpoonItem(TnsConfig.items.harpoon.diamondBaseDamage.get(),RegisterEntity.DIAMOND_HARPOON,Item.Settings()),"diamond_harpoon")

    //spear stuff
    val WOODEN_SPEAR = register(SpearItem(ToolMaterials.WOOD, 4, -2.9f, FabricItemSettings(), RegisterEntity.WOODEN_SPEAR), "wooden_spear")
    val STONE_SPEAR = register(SpearItem(ToolMaterials.STONE, 4, -2.9f, FabricItemSettings(), RegisterEntity.STONE_SPEAR), "stone_spear")
    val IRON_SPEAR = register(SpearItem(ToolMaterials.IRON, 4, -2.9f, FabricItemSettings(), RegisterEntity.IRON_SPEAR), "iron_spear")
    val GOLDEN_SPEAR = register(SpearItem(ToolMaterials.GOLD, 4, -2.9f, FabricItemSettings(), RegisterEntity.GOLDEN_SPEAR), "golden_spear")
    val DIAMOND_SPEAR = register(SpearItem(ToolMaterials.DIAMOND, 4, -2.9f, FabricItemSettings(), RegisterEntity.DIAMOND_SPEAR), "diamond_spear")
    val NETHERITE_SPEAR = register(SpearItem(ToolMaterials.NETHERITE, 4, -2.9f, FabricItemSettings(), RegisterEntity.NETHERITE_SPEAR), "netherite_spear")

    
    val TNS_GROUP: ItemGroup by lazy{
        RegisterItemGroup.registerItemGroup()
    }

    fun getItems(): List<Item>{
        return regItem
    }

    fun registerAll(){
        val group = TNS_GROUP
    }
}
