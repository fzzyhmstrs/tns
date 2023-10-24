package me.fzzyhmstrs.amethyst_imbuement.item.custom

import me.fzzyhmstrs.fzzy_core.item_util.interfaces.Flavorful
import net.minecraft.item.SwordItem
import net.minecraft.item.ToolMaterial

class SpearItem(privat val material: ToolMaterial, attackDamage: Int, attackSpeed: Float, settings: Settings, private val entityType: EntityType<SpearEntity>):
    SwordItem(material, attackDamage, attackSpeed, settings) {

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return material.repairIngredient.test(ingredient) || super.canRepair(stack, ingredient)
    }


    override fun getEnchantability(): Int {
        return material.enchantability
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.SPEAR
    }
      
      fun makeSpearEntity(
        material: ToolMaterial,
        world: World,
        livingEntity: LivingEntity,
        stack: ItemStack
    ): SpearEntity {
        return SpearEntity(entityType, world, livingEntity, stack).also { it.setDamage(material) }
    }    

    override fun onStoppedUsing(stack: ItemStack, world: World, user: LivingEntity, remainingUseTicks: Int) {
        if (user !is PlayerEntity) {
            return
        }
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i < 10) {
            return
        }
        val j = EnchantmentHelper.getRiptide(stack)
        if (j > 0 && !user.isTouchingWaterOrRain) {
            return
        }
        if (!world.isClient) {
            stack.damage(1, user) { p: PlayerEntity ->
                p.sendToolBreakStatus(
                    user.getActiveHand()
                )
            }
            if (j == 0) {
                val glisteringTridentEntity = makeSpearEntity(material,world, user as LivingEntity, stack)
                glisteringTridentEntity.setVelocity(
                    user,
                    user.pitch,
                    user.yaw,
                    0.0f,
                    2.5f + j.toFloat() * 0.5f,
                    1.0f
                )
                if (user.abilities.creativeMode) {
                    glisteringTridentEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY
                }
                world.spawnEntity(glisteringTridentEntity)
                world.playSoundFromEntity(
                    null,
                    glisteringTridentEntity,
                    SoundEvents.ITEM_TRIDENT_THROW,
                    SoundCategory.PLAYERS,
                    1.0f,
                    1.0f
                )
                if (!user.abilities.creativeMode) {
                    user.inventory.removeOne(stack)
                }
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        if (j > 0) {
            val glisteringTridentEntity = user.yaw
            val f = user.pitch
            var g =
                -MathHelper.sin(glisteringTridentEntity * (Math.PI.toFloat() / 180)) * MathHelper.cos(f * (Math.PI.toFloat() / 180))
            var h = -MathHelper.sin(f * (Math.PI.toFloat() / 180))
            var k =
                MathHelper.cos(glisteringTridentEntity * (Math.PI.toFloat() / 180)) * MathHelper.cos(f * (Math.PI.toFloat() / 180))
            //val l = MathHelper.sqrt(g * g + h * h + k * k)
            val m = 3.0f * ((1.0f + j.toFloat()) / 4.0f)

            g *= m
            h *= m
            k *= m
            user.addVelocity(g.toDouble(),h.toDouble(),k.toDouble())
            user.useRiptide(20)

            if (user.isOnGround) {
                //val n = 1.1999999f
                user.move(MovementType.SELF, Vec3d(0.0, 1.1999999284744263, 0.0))
            }
            val n =
                if (j >= 3) SoundEvents.ITEM_TRIDENT_RIPTIDE_3 else if (j == 2) SoundEvents.ITEM_TRIDENT_RIPTIDE_2 else SoundEvents.ITEM_TRIDENT_RIPTIDE_1
            world.playSoundFromEntity(null, user, n, SoundCategory.PLAYERS, 1.0f, 1.0f)
        }
    }
}
