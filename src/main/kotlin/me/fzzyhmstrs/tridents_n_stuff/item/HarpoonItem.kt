package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.fzzy_core.coding_util.AcText
import me.fzzyhmstrs.tridents_n_stuff.entity.HarpoonEntity
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World


open class HarpoonItem(private val damage: Double,private val entityType: EntityType<out HarpoonEntity>,settings: Settings) : Item(settings) {

    open fun createHarpoon(world: World, shooter: LivingEntity): PersistentProjectileEntity {
        return HarpoonEntity(entityType, world, shooter, ItemStack(this)).also { it.damage = damage }
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext?
    ) {
        super.appendTooltip(stack, world, tooltip, context)
        tooltip.add(AcText.translatable("item.tridents_n_stuff.harpoon.damage",damage).formatted(Formatting.GREEN))
    }

}