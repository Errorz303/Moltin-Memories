package net.errorz.memories.entity.custom;

import net.errorz.memories.entity.ModEntities;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Items;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import net.minecraft.entity.ai.goal.*;


public class BasaltBadgerEntity extends PassiveEntity implements GeoEntity {
    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);
    private static final TrackedData<Integer> DATA_ID_TYPE_VARIANT =
            DataTracker.registerData(BasaltBadgerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private Goal followChickenAndRabbitGoal;

    public BasaltBadgerEntity(EntityType<? extends PassiveEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, -1.0F);
    }
    static class Navigation extends MobNavigation {
        Navigation(StriderEntity entity, World world) {
            super(entity, world);
        }

        protected PathNodeNavigator createPathNodeNavigator(int range) {
            this.nodeMaker = new LandPathNodeMaker();
            this.nodeMaker.setCanEnterOpenDoors(true);
            return new PathNodeNavigator(this.nodeMaker, range);
        }

        protected boolean canWalkOnPath(PathNodeType pathType) {
            return pathType != PathNodeType.LAVA && pathType != PathNodeType.DAMAGE_FIRE && pathType != PathNodeType.DANGER_FIRE ? super.canWalkOnPath(pathType) : true;
        }

        public boolean isValidPosition(BlockPos pos) {
            return this.world.getBlockState(pos).isOf(Blocks.LAVA) || super.isValidPosition(pos);
        }
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));

        this.followChickenAndRabbitGoal = new ActiveTargetGoal(this, AnimalEntity.class, 10, false, false, (entity) -> entity instanceof ChickenEntity || entity instanceof RabbitEntity);

        this.goalSelector.add(1, new EscapeDangerGoal(this, 2.0));

        this.targetSelector.add(3, new RevengeGoal(this).setGroupRevenge());
        this.goalSelector.add(3, new TemptGoal(this, 1.25, stack -> stack.isOf(Items.MAGMA_CREAM), false));
        this.goalSelector.add(3, new TemptGoal(this, 1.25, stack -> stack.isOf(Items.ICE), true));
        this.goalSelector.add(4, new PounceAtTargetGoal(this, 0.4F));

        this.goalSelector.add(5, new MeleeAttackGoal(this, (double)1.0F, true));

        this.goalSelector.add(5, new WanderAroundFarGoal(this, 1.0));
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 6.0f));
        this.goalSelector.add(7, new LookAroundGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

    }
    private PlayState predicate(AnimationState tAnimationState) {
        if(tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.basalt_badger.run", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        tAnimationState.getController().setAnimation(RawAnimation.begin().then("animation.basalt_badger.idle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }
    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4);
    }

    public boolean canWalkOnFluid(FluidState state) {
        return state.isIn(FluidTags.LAVA);
    }
    public boolean hurtByWater() {
        return true;
    }

    public boolean isOnFire() {
        return false;
    }
    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.BASALT_BADGER.create(world);
    }
    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_FOX_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_FOX_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_FOX_DEATH;
    }
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        if (fallDistance > 20 && !this.isBaby()) {
            this.playSound(SoundEvents.BLOCK_LAVA_POP, 1.0f, (float) (1.0f + this.random.nextGaussian() / 10f));
        }
        return false;
    }
    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(DATA_ID_TYPE_VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.dataTracker.get(DATA_ID_TYPE_VARIANT);
    }

    public BadgerVariant getVariant() {
        return BadgerVariant.byId(this.getTypeVariant() & 255);
    }

    public void setVariant(BadgerVariant variant) {
        this.dataTracker.set(DATA_ID_TYPE_VARIANT, variant.getId() & 255);
    }

    @Override
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        BadgerVariant variant = Util.getRandom(BadgerVariant.values(), this.random);
        setVariant(variant);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }
}
