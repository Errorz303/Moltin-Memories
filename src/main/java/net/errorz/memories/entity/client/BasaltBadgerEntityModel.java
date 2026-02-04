package net.errorz.memories.entity.client;

import net.errorz.memories.AntiqueMemories;
import net.errorz.memories.entity.custom.BasaltBadgerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class BasaltBadgerEntityModel extends GeoModel<BasaltBadgerEntity> {
    @Override
    public Identifier getModelResource(BasaltBadgerEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "geo/entity/basalt_badger.geo.json");
    }

    @Override
    public Identifier getTextureResource(BasaltBadgerEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "textures/entity/basalt_badger.png");
    }

    @Override
    public Identifier getAnimationResource(BasaltBadgerEntity animatable) {
        return Identifier.of(AntiqueMemories.MOD_ID, "animations/entity/basalt_badger.animation.json");
    }

    @Override
    public void setCustomAnimations(BasaltBadgerEntity animatable, long instanceId, AnimationState<BasaltBadgerEntity> animationState) {
        GeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }
}