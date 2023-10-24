package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text

object RegisterItem {

    private val regItem: MutableList<Item> = mutableListOf()

    private fun <T: Item> register(item: T, name: String): T{
        regItem.add(item)
        return Registry.register(Registries.ITEM, TNS.identity(name), item)
    }


    val TNS_GROUP: ItemGroup by lazy{
        registerItemGroup()
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