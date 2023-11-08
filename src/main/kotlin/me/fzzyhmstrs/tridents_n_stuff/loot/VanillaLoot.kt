package me.fzzyhmstrs.tridents_n_stuff.loot

import me.fzzyhmstrs.fzzy_core.item_util.AbstractModLoot
import me.fzzyhmstrs.tridents_n_stuff.config.TnsConfig
import me.fzzyhmstrs.tridents_n_stuff.registry.RegisterItem
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.LootTables
import net.minecraft.loot.condition.RandomChanceLootCondition
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier

object VanillaLoot: AbstractModLoot() {
    override val targetNameSpace: String = "battletowers"

    override fun lootBuilder(id: Identifier, table: LootTable.Builder): Boolean{
        if(LootTables.WOODLAND_MANSION_CHEST.equals(id) || LootTables.PILLAGER_OUTPOST_CHEST.equals(id) || LootTables.SIMPLE_DUNGEON_CHEST.equals(id) || LootTables.VILLAGE_FLETCHER_CHEST.equals(id) || LootTables.DESERT_PYRAMID_CHEST.equals(id) || LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)){
            val poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(RandomChanceLootCondition.builder(TnsConfig.items.basicHarpoonChance.get()))
                .with(ItemEntry.builder(RegisterItem.BONE_HARPOON).weight(5).apply(SetCountLootFunction.builder(
                    UniformLootNumberProvider.create(2f,6f))))
                .with(ItemEntry.builder(RegisterItem.PRISMARINE_HARPOON).weight(1).apply(SetCountLootFunction.builder(
                    UniformLootNumberProvider.create(1f,4f))))
            table.pool(poolBuilder)
            return true
        } else if(LootTables.UNDERWATER_RUIN_SMALL_CHEST.equals(id) || LootTables.UNDERWATER_RUIN_BIG_CHEST.equals(id) || LootTables.SHIPWRECK_SUPPLY_CHEST.equals(id)){
            val poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(RandomChanceLootCondition.builder(TnsConfig.items.oceanHarpoonChance.get()))
                .with(ItemEntry.builder(RegisterItem.PRISMARINE_HARPOON).weight(1).apply(SetCountLootFunction.builder(
                    UniformLootNumberProvider.create(2f,8f))))
            table.pool(poolBuilder)
            return true
        } else if (LootTables.STRONGHOLD_CROSSING_CHEST.equals(id) || LootTables.STRONGHOLD_CORRIDOR_CHEST.equals(id) || LootTables.NETHER_BRIDGE_CHEST.equals(id)) {
            val poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0F))
                .conditionally(RandomChanceLootCondition.builder(TnsConfig.items.strongHarpoonChance.get()))
                .with(ItemEntry.builder(RegisterItem.PRISMARINE_HARPOON).weight(5).apply(SetCountLootFunction.builder(
                    UniformLootNumberProvider.create(2f,6f))))
                .with(ItemEntry.builder(RegisterItem.DIAMOND_HARPOON).weight(1).apply(SetCountLootFunction.builder(
                    UniformLootNumberProvider.create(1f,4f))))
            table.pool(poolBuilder)
            return true
        }

        return false
    }
}
