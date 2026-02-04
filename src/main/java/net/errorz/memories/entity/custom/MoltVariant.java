package net.errorz.memories.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum MoltVariant {
    MOLTEN(0),
    SOUL(1);

    private static final MoltVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            MoltVariant::getId)).toArray(MoltVariant[]::new);
    private final int id;

    MoltVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static MoltVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}