package me.fzzyhmstrs.tridents_n_stuff.entity

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LightningEntity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.data.DataTracker
import net.minecraft.entity.data.TrackedDataHandlerRegistry
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.PersistentProjectileEntity
import net.minecraft.entity.projectile.TridentEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ToolMaterial
import net.minecraft.nbt.NbtCompound
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.hit.EntityHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

open class SpearEntity : PersistentProjectileEntity {
    private var tridentStack = ItemStack.EMPTY.copy()
    private var dealtDamage = false
    private var damage = 8f

    constructor(entityType: EntityType<out SpearEntity?>?, world: World?) : super(entityType, world)
    constructor(entityType: EntityType<out SpearEntity?>?,world: World?, owner: LivingEntity?, stack: ItemStack) : super(
        entityType,
        owner,
        world
    ) {
        tridentStack = stack.copy()
        dataTracker.set(ENCHANTED, stack.hasGlint())
    }

    fun setDamage(material:ToolMaterial) {
        this.damage = material.attackDamage
    }

    override fun initDataTracker() {
        super.initDataTracker()
        dataTracker.startTracking(ENCHANTED, false)
    }

    override fun tick() {
        if (inGroundTime > 4) {
            dealtDamage = true
        }
        super.tick()
    }

    private val isOwnerAlive: Boolean
        get() {
            val entity = owner
            return if (entity == null || !entity.isAlive) {
                false
            } else entity !is ServerPlayerEntity || !entity.isSpectator()
        }

    override fun asItemStack(): ItemStack {
        return tridentStack.copy()
    }

    val isEnchanted: Boolean
        get() = dataTracker.get(ENCHANTED)

    override fun getEntityCollision(currentPosition: Vec3d, nextPosition: Vec3d): EntityHitResult? {
        return if (dealtDamage) {
            null
        } else super.getEntityCollision(currentPosition, nextPosition)
    }

    override fun onEntityHit(entityHitResult: EntityHitResult) {
        val blockPos: BlockPos?
        var livingEntity: Entity? = null
        val entity = entityHitResult.entity
        var f = this.damage
        if (entity is LivingEntity) {
            livingEntity = entity
            f += EnchantmentHelper.getAttackDamage(tridentStack, livingEntity.group)
        }
        if (owner != null){
            livingEntity = owner as Entity
        }
        val damageSource = this.damageSources.trident(this, if (owner == null) this else livingEntity)
        dealtDamage = true
        var soundEvent = SoundEvents.ITEM_TRIDENT_HIT
        if (entity.damage(damageSource, f)) {
            if (entity.type === EntityType.ENDERMAN) {
                return
            }
            if (entity is LivingEntity) {
                if (livingEntity is LivingEntity) {
                    EnchantmentHelper.onUserDamaged(entity, livingEntity)
                    EnchantmentHelper.onTargetDamaged(livingEntity, entity)
                }
                onHit(entity)
            }
        }
        velocity = velocity.multiply(-0.01, -0.1, -0.01)
        playSound(soundEvent, 1.0f, 1.0f)
    }

    override fun tryPickup(player: PlayerEntity): Boolean {
        return super.tryPickup(player) || this.isNoClip && isOwner(player) && player.inventory.insertStack(
            asItemStack()
        )
    }

    override fun getHitSound(): SoundEvent {
        return SoundEvents.ITEM_TRIDENT_HIT_GROUND
    }

    override fun onPlayerCollision(player: PlayerEntity) {
        if (isOwner(player) || owner == null) {
            super.onPlayerCollision(player)
        }
    }

    override fun readCustomDataFromNbt(nbt: NbtCompound) {
        super.readCustomDataFromNbt(nbt)
        if (nbt.contains("Spear", 10)) {
            tridentStack = ItemStack.fromNbt(nbt.getCompound("Trident"))
        }
        dealtDamage = nbt.getBoolean("DealtDamage")
    }

    override fun writeCustomDataToNbt(nbt: NbtCompound) {
        super.writeCustomDataToNbt(nbt)
        nbt.put("Spear", tridentStack.writeNbt(NbtCompound()))
        nbt.putBoolean("DealtDamage", dealtDamage)
    }

    public override fun age() {
        val i = dataTracker.get(LOYALTY)
        if (pickupType != PickupPermission.ALLOWED || i <= 0) {
            super.age()
        }
    }

    override fun getDragInWater(): Float {
        return 0.95f
    }

    override fun shouldRender(cameraX: Double, cameraY: Double, cameraZ: Double): Boolean {
        return true
    }

    companion object {
        private val ENCHANTED =
            DataTracker.registerData(SpearEntity::class.java, TrackedDataHandlerRegistry.BOOLEAN)
    }
}
