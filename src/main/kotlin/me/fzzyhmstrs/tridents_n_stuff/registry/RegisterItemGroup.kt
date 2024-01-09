package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.item.*
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.item.*
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.text.Text
import net.minecraft.util.Rarity

object RegisterItemGroup {

    fun registerItemGroup(): ItemGroup{
        return Registry.register(Registries.ITEM_GROUP, TNS.identity("tns_group"), FabricItemGroup.builder()
            .displayName(Text.translatable("itemGroup.tridents_n_stuff.tns_group"))
            .icon { ItemStack(Items.TRIDENT) }
            .entries { _, entries ->
                entries.addAll(regItem.stream().map { item -> ItemStack(item) }.toList())
            }.build())
    }

    fun registerAll(){
    }
}
