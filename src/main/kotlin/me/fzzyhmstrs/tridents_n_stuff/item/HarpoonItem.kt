package me.fzzyhmstrs.tridents_n_stuff.item

import me.fzzyhmstrs.tridents_n_stuff.entity.HarpoonEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.Item
import net.minecraft.world.World


open class HarpoonItem(private val damage: Double,settings: Settings) : Item(settings) {

    open fun createHarpoon(world: World, shooter: LivingEntity): PersistentProjectileEntity {
        return HarpoonEntity(world, shooter).also { it.damage = damage }
    }
}