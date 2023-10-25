package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.amethyst_imbuement.item.custom.SpearItem
import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.FarshotTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.StormseekerTridentEntity
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
        return Registry.register(Registries.ITEM, TNS.identity(name), item)
    }
    // unique tridents
    val STORMSEEKER = register(StormseekerTridentItem(TnsConfig.materials.stormseeker,-2.9,FabricItemSettings().rarity(Rarity.RARE)), "stormseeker")
    val THE_DESECRATOR = register(DesecratorTridentItem(TnsConfig.materials.desecrator,-2.9,FabricItemSettings().rarity(Rarity.RARE)), "the_desecrator")
    val ECHO_OF_THE_DEEP = register(EchoOfTheDeepTridentItem(TnsConfig.materials.echo,-2.9,FabricItemSettings().rarity(Rarity.RARE)), "echo_of_the_deep")
    val STELLARIS = register(StellarisTridentItem(TnsConfig.materials.stellaris,-2.9,FabricItemSettings().rarity(Rarity.RARE)), "stellaris")
    val SANGUINE_BOND = register(SanguineBondTridentItem(TnsConfig.materials.sanguine,-2.9,FabricItemSettings().rarity(Rarity.RARE)), "sanguine_bond")

    //crafted tridents
    val FRENZIED_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.frenzied,-1.6,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.FRENZIED_TRIDENT, w,e,s) }, "frenzied_trident")
    val ANCIENT_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.ancient,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.ANCIENT_TRIDENT, w,e,s) }, "ancient_trident")
    val OCEANIC_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.oceanic,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.OCEANIC_TRIDENT, w,e,s) }, "oceanic_trident")
    val HOLY_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.holy,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> CustomTridentEntity(RegisterEntity.HOLY_TRIDENT, w,e,s)}, "holy_trident")
    val FARSHOT_TRIDENT = register(BasicCustomTridentItem(TnsConfig.materials.farshot,-2.9,FabricItemSettings().rarity(Rarity.UNCOMMON)) {w,e,s -> FarshotTridentEntity(w,e,s) }, "farshot_trident")

    //Harpoon stuff
    val HARPOON_LAUNCHER = register(HarpoonLauncherItem(Item.Settings().maxDamage(325).rarity(Rarity.COMMON)),"harpoon_launcher")
    val BONE_HARPOON = register(HarpoonItem(5.0,Item.Settings()),"bone_harpoon")

    //spear stuff
    val WOODEN_SPEAR = register(SpearItem(ToolMaterials.WOOD, -2, -2.9f, FabricItemSettings(), RegisterEntity.WOODEN_SPEAR), "wooden_spear")
    val STONE_SPEAR = register(SpearItem(ToolMaterials.STONE, -2, -2.9f, FabricItemSettings(), RegisterEntity.STONE_SPEAR), "stone_spear")
    val IRON_SPEAR = register(SpearItem(ToolMaterials.IRON, -2, -2.9f, FabricItemSettings(), RegisterEntity.IRON_SPEAR), "iron_spear")
    val GOLDEN_SPEAR = register(SpearItem(ToolMaterials.GOLD, -2, -2.9f, FabricItemSettings(), RegisterEntity.GOLDEN_SPEAR), "golden_spear")
    val DIAMOND_SPEAR = register(SpearItem(ToolMaterials.DIAMOND, -2, -2.9f, FabricItemSettings(), RegisterEntity.DIAMOND_SPEAR), "diamond_spear")
    val NETHERITE_SPEAR = register(SpearItem(ToolMaterials.NETHERITE, -2, -2.9f, FabricItemSettings(), RegisterEntity.NETHERITE_SPEAR), "netherite_spear")

    
    val TNS_GROUP: ItemGroup by lazy{
        registerItemGroup()
    }

    fun getItems(): List<Item>{
        return regItem
    }

    fun registerItemGroup(): ItemGroup{
        return Registry.register(Registries.ITEM_GROUP, TNS.identity("tns_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.tridents_n_stuff.tns_group"))
            .icon { ItemStack(Items.TRIDENT) }
            .entries { _, entries ->
                entries.addAll(regItem.stream().map { item -> ItemStack(item) }.toList())
                /*entries.addAll(RegisterArmor.regArmor.stream().map { item -> ItemStack(item) }.toList())
                entries.addAll(RegisterTool.regTool.stream().map { item -> ItemStack(item) }.toList())*/
                /*entries.addAll(RegisterBlock.regBlock.values.stream()
                    .map { block -> ItemStack(block.asItem()) }
                    .toList())*/
            }.build())
    }

    fun registerAll(){
        val group = TNS_GROUP
    }
}
