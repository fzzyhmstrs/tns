package me.fzzyhmstrs.tridents_n_stuff.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import me.fzzyhmstrs.tridents_n_stuff.entity.HarpoonEntity
import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.item.RangedWeaponItem
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.util.UseAction
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import java.util.function.Predicate

class HarpoonLauncherItem(settings: Settings) : RangedWeaponItem(settings) {
    private var attributeModifiers: Multimap<EntityAttribute, EntityAttributeModifier>
    
    init {
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(
            EntityAttributes.GENERIC_ATTACK_SPEED,
            EntityAttributeModifier(
                ATTACK_SPEED_MODIFIER_ID,
                "Tool modifier",
                -2.9,
                EntityAttributeModifier.Operation.ADDITION
            )
        )
        this.attributeModifiers = builder.build()
    }

    override fun canRepair(stack: ItemStack, ingredient: ItemStack): Boolean {
        return ingredient.isOf(Items.IRON_INGOT)
    }

    override fun getProjectiles(): Predicate<ItemStack> {
        return Predicate {stack: ItemStack -> stack.item is HarpoonItem }
    }

    override fun getRange(): Int {
        return 15
    }

    override fun getMaxUseTime(stack: ItemStack?): Int {
        return 72000
    }

    override fun getEnchantability(): Int {
        return 1
    }

    override fun getUseAction(stack: ItemStack): UseAction {
        return UseAction.SPEAR
    }

    override fun getAttributeModifiers(slot: EquipmentSlot): Multimap<EntityAttribute, EntityAttributeModifier> {
        return if (slot == EquipmentSlot.MAINHAND) {
            this.attributeModifiers
        } else super.getAttributeModifiers(slot)
    }

    override fun onStoppedUsing(stack: ItemStack, world: World, user: LivingEntity, remainingUseTicks: Int) {
        if (user !is PlayerEntity) {
            return
        }
        if(user.inventory.isEmpty){
          return
        }
        val i = getMaxUseTime(stack) - remainingUseTicks
        if (i < 15) {
            return
        }
        if (!world.isClient) {
            val harpoonStack = checkHarpoonStack(user)
            if (harpoonStack.isEmpty) return
            stack.damage(1, user) { p: PlayerEntity ->
                p.sendToolBreakStatus(
                    user.getActiveHand()
                )
            }
            val harpoonItem = harpoonStack.item as? HarpoonItem ?: return
            val harpoonEntity = harpoonItem.createHarpoon(world, user)
            harpoonEntity.setVelocity(
              user,
              user.pitch,
              user.yaw,
              0.0f,
              3.2f,
              0.9f
            )
            val j = EnchantmentHelper.getLevel(Enchantments.POWER, stack)
            if (j > 0) {
                harpoonEntity.damage = harpoonEntity.damage + j.toDouble() * HarpoonEntity.powerPerLevel + HarpoonEntity.powerFlatAmount
            }
            val k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack)
            if (k > 0) {
                harpoonEntity.punch = k
            }
            if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                harpoonEntity.setOnFireFor(100)
            }

            if (user.abilities.creativeMode) {
              harpoonEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY
            }
            world.spawnEntity(harpoonEntity)
            world.playSoundFromEntity(
              null,
              harpoonEntity,
              SoundEvents.ITEM_TRIDENT_THROW,
              SoundCategory.PLAYERS,
              1.0f,
              0.9f
            )
            if (!user.abilities.creativeMode) {
                harpoonStack.decrement(1)
                if (harpoonStack.isEmpty) {
                    user.inventory.removeOne(harpoonStack)
                }
            }
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this))
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val harpoonStack = checkHarpoonStack(user)
        val itemStack = user.getStackInHand(hand)
        if (itemStack.damage >= itemStack.maxDamage - 1) {
            return TypedActionResult.fail(itemStack)
        }
        if (harpoonStack.isEmpty){
            return TypedActionResult.fail(itemStack)
        }
        user.setCurrentHand(hand)
        return TypedActionResult.consume(itemStack)
    }

    private fun checkHarpoonStack(user: PlayerEntity): ItemStack{
        var harpoonStack = ItemStack.EMPTY
        for (slot in 0 until user.inventory.size()){
            val testStack = user.inventory.getStack(slot)
            if (projectiles.test(testStack)){
                harpoonStack = testStack
                break
            }
        }
        return harpoonStack
    }

    override fun postHit(stack: ItemStack, target: LivingEntity?, attacker: LivingEntity): Boolean {
        stack.damage(1, attacker) { e: LivingEntity ->
            e.sendEquipmentBreakStatus(
                EquipmentSlot.MAINHAND
            )
        }
        return true
    }

    override fun postMine(
        stack: ItemStack,
        world: World?,
        state: BlockState,
        pos: BlockPos?,
        miner: LivingEntity
    ): Boolean {
        if (state.getHardness(world, pos).toDouble() != 0.0) {
            stack.damage(2, miner) { e: LivingEntity ->
                e.sendEquipmentBreakStatus(
                    EquipmentSlot.MAINHAND
                )
            }
        }
        return true
    }

}
