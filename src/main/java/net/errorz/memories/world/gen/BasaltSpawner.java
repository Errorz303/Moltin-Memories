package net.errorz.memories.world.gen;

import net.errorz.memories.entity.ModEntities;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.world.Heightmap;
import net.minecraft.world.biome.BiomeKeys;

public class BasaltSpawner {
    public static void addSpawns() {
        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey( BiomeKeys.BASALT_DELTAS),
                SpawnGroup.MONSTER, ModEntities.BASALT_BADGER, 1, 1, 1);
        SpawnRestriction.register(ModEntities.BASALT_BADGER, SpawnLocationTypes.ON_GROUND,
                Heightmap.Type.WORLD_SURFACE, TameableEntity::canMobSpawn);

    }

}