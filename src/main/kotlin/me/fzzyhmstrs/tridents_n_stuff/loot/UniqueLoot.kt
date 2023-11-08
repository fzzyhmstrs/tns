package me.fzzyhmstrs.tridents_n_stuff.loot

import me.fzzyhmstrs.fzzy_core.item_util.AbstractModLoot
import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterTag
import net.minecraft.entity.EntityType
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.TagEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.util.Identifier

object UniqueLoot: AbstractModLoot() {
    override val targetNameSpace: String = "minecraft"

    override fun lootBuilder(id: Identifier, table: LootTable.Builder): Boolean{
        if(id == EntityType.WITHER.lootTableId){
            val poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(RandomChanceLootCondition.builder(TnsConfig.items.witherUniqueChance.get()))
                .with(TagEntry.expandBuilder(RegisterTag.UNIQUE_TRIDENTS))
            table.pool(poolBuilder)
            return true
        } else if (id.path.contains("chests") || id.path.contains("chest")) {
            val poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(RandomChanceLootCondition.builder(TnsConfig.items.chestUniqueChance.get()))
                .with(TagEntry.expandBuilder(RegisterTag.UNIQUE_TRIDENTS))
            table.pool(poolBuilder)
            return true
        }

        return false
    }
}