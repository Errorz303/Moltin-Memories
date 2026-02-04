package net.errorz.memories.entity.client;

import com.google.common.collect.Maps;
import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.entity.custom.BadgerVariant;
import net.errorz.memories.entity.custom.BasaltBadgerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class BasaltBadgerEntityRenderer extends GeoEntityRenderer<BasaltBadgerEntity> {
    private static final Map<BadgerVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(BadgerVariant.class), map -> {
                map.put(BadgerVariant.MOLTEN, Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/basalt_badger.png"));
                map.put(BadgerVariant.SOUL, Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/soul_badger.png"));
            });
    public BasaltBadgerEntityRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new BasaltBadgerEntityModel());
    }

    @Override
    public Identifier getTextureLocation(BasaltBadgerEntity animatable) {
        return LOCATION_BY_VARIANT.get(animatable.getVariant());
    }

    @Override
    public void render(BasaltBadgerEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f, 0.4f, 0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}