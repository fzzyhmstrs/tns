package me.fzzyhmstrs.tridents_n_stuff.registry

import me.fzzyhmstrs.tridents_n_stuff.TNS
import me.fzzyhmstrs.tridents_n_stuff.entity.HarpoonEntity
import me.fzzyhmstrs.tridents_n_stuff.entity.StormseekerTridentEntity
import net.fabricmc.fabric.api.`object`.builder.v1.entity.FabricEntityTypeBuilder
import net.minecraft.entity.EntityDimensions
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.world.World

object RegisterEntity {

    val GLISTERING_TRIDENT_ENTITY: EntityType<StormseekerTridentEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        TNS.identity( "stormseeker_trident_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<StormseekerTridentEntity>, world: World ->
            StormseekerTridentEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    val HARPOON_ENTITY: EntityType<HarpoonEntity> = Registry.register(
        Registries.ENTITY_TYPE,
        TNS.identity( "basic_harpoon_entity"),
        FabricEntityTypeBuilder.create(
            SpawnGroup.MISC
        ) { entityType: EntityType<HarpoonEntity>, world: World ->
            HarpoonEntity(
                entityType,
                world
            )
        }.dimensions(EntityDimensions.fixed(0.5f, 0.5f)).trackRangeChunks(4).trackedUpdateRate(20).build()
    )

    fun registerAll(){}

}