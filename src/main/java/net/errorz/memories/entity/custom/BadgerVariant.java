package net.errorz.memories.entity.custom;

import java.util.Arrays;
import java.util.Comparator;

public enum BadgerVariant {
    MOLTEN(0),
    SOUL(1);

    private static final BadgerVariant[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(
            BadgerVariant::getId)).toArray(BadgerVariant[]::new);
    private final int id;

    BadgerVariant(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public static BadgerVariant byId(int id) {
        return BY_ID[id % BY_ID.length];
    }
}