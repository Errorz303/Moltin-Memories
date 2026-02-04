package net.errorz.memories.mixin;

import net.errorz.memories.item.brush.MemoryBrushItem;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrushableBlockEntity.class)
public abstract class BrushingMixin
{
    @Shadow
    private long nextBrushTime;

    @Inject(method = "brush", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/entity/BrushableBlockEntity;generateItem(Lnet/minecraft/entity/player/PlayerEntity;)V"))
    private void injectMethod(long worldTime, PlayerEntity player, Direction hitDirection, CallbackInfoReturnable<Boolean> cir)
    {
        if (player.getActiveItem().getItem() instanceof MemoryBrushItem brushItem)
        {
            this.nextBrushTime -= (long) (10L - brushItem.getBrushingSpeed());
        }
    }
}