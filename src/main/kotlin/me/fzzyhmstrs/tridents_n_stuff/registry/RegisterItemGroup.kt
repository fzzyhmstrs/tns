package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text

object RegisterItemGroup {

    fun registerItemGroup(): ItemGroup{
        return Registry.register(Registries.ITEM_GROUP, TNS.identity("tns_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.tridents_n_stuff.tns_group"))
            .icon { ItemStack(Items.TRIDENT) }
            .entries { _, entries ->
                entries.addAll(RegisterItem.getItems().stream().map { item -> ItemStack(item) }.toList())
            }.build())
    }

    fun registerAll(){
    }
}
