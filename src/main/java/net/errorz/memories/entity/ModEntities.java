package net.errorz.memories.entity;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.entity.custom.BasaltBadgerEntity;
import net.errorz.memories.entity.custom.MoltingEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<BasaltBadgerEntity> BASALT_BADGER = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(AntiqueMemories.MOD_ID, "basalt_badger"),
            EntityType.Builder.create(BasaltBadgerEntity::new, SpawnGroup.CREATURE).dimensions(1f, 1f).makeFireImmune().build());

    public static final EntityType<MoltingEntity> MOLT = Registry.register(Registries.ENTITY_TYPE,
            Identifier.of(AntiqueMemories.MOD_ID, "molt"),
            EntityType.Builder.create(MoltingEntity::new, SpawnGroup.CREATURE).dimensions(0.6f, 0.6f).makeFireImmune().build());

    public static void registerModEntities() {
        AntiqueMemories.LOGGER.info("Registering Mod Entities for " + AntiqueMemories.MOD_ID);
    }
}
