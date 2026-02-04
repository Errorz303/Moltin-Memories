package net.errorz.memories.damage_type;

import net.errorz.memories.AntiqueMemories;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

public class ModDamageTypes {
    public static final RegistryKey<DamageType> MOLT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, AntiqueMemories.id("molt"));
}
