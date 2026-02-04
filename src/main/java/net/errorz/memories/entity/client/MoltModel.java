package net.errorz.memories.entity.client;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.entity.custom.MoltingEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class MoltModel extends GeoModel<MoltingEntity> {
    @Override
    public Identifier getModelResource(MoltingEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "geo/entity/molt.geo.json");
    }

    @Override
    public Identifier getTextureResource(MoltingEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/molt.png");
    }

    @Override
    public Identifier getAnimationResource(MoltingEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "animations/entity/molt.animation.json");
    }

    @Override
    public void setCustomAnimations(MoltingEntity animatable, long instanceId, AnimationState<MoltingEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}