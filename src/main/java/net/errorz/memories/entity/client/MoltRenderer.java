package net.errorz.memories.entity.client;

import com.google.common.collect.Maps;
import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.entity.custom.MoltVariant;
import net.errorz.memories.entity.custom.MoltingEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

import java.util.Map;

public class MoltRenderer extends GeoEntityRenderer<MoltingEntity> {
    private static final Map<MoltVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MoltVariant.class), map -> {
                map.put(MoltVariant.MOLTEN, Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/molt.png"));
                map.put(MoltVariant.SOUL, Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/soul_molt.png"));
            });
    public MoltRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new MoltModel());
    }

    @Override
    public Identifier getTextureLocation(MoltingEntity animatable) {
        return LOCATION_BY_VARIANT.get(animatable.getVariant());
    }

    @Override
    public void render(MoltingEntity entity, float entityYaw, float partialTick, MatrixStack poseStack,
                       VertexConsumerProvider bufferSource, int packedLight) {

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}