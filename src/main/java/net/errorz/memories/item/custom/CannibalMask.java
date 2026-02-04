package net.errorz.memories.item.custom;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Pair;
import java.util.Optional;

public class CannibalMask extends TrinketItem {
    public CannibalMask(Settings settings) {
        super(settings);
    }

    public static boolean isWearingMask(LivingEntity livingEntity) {
        return getWornMask(livingEntity) != ItemStack.EMPTY;
    }

    public static ItemStack getWornMask(LivingEntity livingEntity) {
        Optional<TrinketComponent> component = TrinketsApi.getTrinketComponent(livingEntity);
        if (component.isPresent()) {
            for (Pair<SlotReference, ItemStack> pair : component.get().getAllEquipped()) {
                if (pair.getRight().getItem() instanceof CannibalMask) {
                    return pair.getRight();
                }
            }
        }
        return ItemStack.EMPTY;
    }
}