package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.entity.CustomTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.FarshotTridentEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.HarpoonEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.SpearEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.World

object RegisterEntity {

    fun <T: Entity> register(factory: EntityType.EntityFactory<T>, name: String): EntityType<T>{
        return Registry.register(Registries.ENTITY_TYPE, TNS.identity(name),
            FabricEntityTypeBuilder.create(SpawnGroup.MISC,factory)
                .dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
        )

    }

    val SLUMBERING_TRIDENT: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "slumbering_trident_entity")
    
    val STORMSEEKER: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "stormseeker_entity")

    val THE_DESECRATOR: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "the_desecrator_entity")

    val ECHO_OF_THE_DEEP: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "echo_of_the_deep_entity")
    
    val STELLARIS: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "stellaris_entity")

    val SANGUINE_BOND: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "sanguine_bond_entity")

    //////////////////////
    
    val FRENZIED_TRIDENT: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "frenzied_trident_entity")
    val ANCIENT_TRIDENT: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "ancient_trident_entity")
    val OCEANIC_TRIDENT: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "oceanic_trident_entity")
    val HOLY_TRIDENT: EntityType<CustomTridentEntity> = register(
        { entityType: EntityType<CustomTridentEntity>, world: World ->
            CustomTridentEntity(entityType,world)
        }, "holy_trident_entity")
    val FARSHOT_TRIDENT: EntityType<FarshotTridentEntity> = register(
        { entityType: EntityType<FarshotTridentEntity>, world: World ->
            FarshotTridentEntity(entityType,world)
        }, "farshot_trident_entity")

    //////////////////////
    
    val BONE_HARPOON: EntityType<HarpoonEntity> = register(
        { entityType: EntityType<HarpoonEntity>, world: World ->
            HarpoonEntity(entityType,world)
        },"bone_harpoon_entity")
    val PRISMARINE_HARPOON: EntityType<HarpoonEntity> = register(
        { entityType: EntityType<HarpoonEntity>, world: World ->
            HarpoonEntity(entityType,world)
        },"prismarine_harpoon_entity")
    val DIAMOND_HARPOON: EntityType<HarpoonEntity> = register(
        { entityType: EntityType<HarpoonEntity>, world: World ->
            HarpoonEntity(entityType,world)
        },"diamond_harpoon_entity")

    //////////////////////
    
    val WOODEN_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"wooden_spear_entity")

    val STONE_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"stone_spear_entity")

    val IRON_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"iron_spear_entity")

    val GOLDEN_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"golden_spear_entity")

    val DIAMOND_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"diamond_spear_entity")

    val NETHERITE_SPEAR: EntityType<SpearEntity> = register(
        { entityType: EntityType<SpearEntity>, world: World ->
            SpearEntity(entityType,world)
        },"netherite_spear_entity")

    fun registerAll(){}

}
