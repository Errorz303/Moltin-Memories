package net.errorz.memories.block.custom.Magmolts;

import net.errorz.memories.block.MemoryBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class Molt extends Block {

    public Molt(Settings settings) {
        super(settings);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return state.getBlock() == MemoryBlocks.MOLTEN_MOLT;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.getBlock() == MemoryBlocks.MOLTEN_MOLT && random.nextInt(50) == 0) {
            world.setBlockState(pos, MemoryBlocks.COOLED_MOLT.getDefaultState());
        }
    }
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        entity.slowMovement(state, new Vec3d((double)0.9F, (double)1.5F, (double)0.9F));
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity) entity;
            if (!livingEntity.isInvulnerableTo(world.getDamageSources().hotFloor())) {
                entity.damage(world.getDamageSources().hotFloor(), 1.0F);
            }
        }
    }
    protected boolean isSideInvisible(BlockState state, BlockState stateFrom, Direction direction) {
        return stateFrom.isOf(this) ? true : super.isSideInvisible(state, stateFrom, direction);
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.getBlock() == MemoryBlocks.COOLED_MOLT) {
            return;
        }
        if (random.nextInt(5) != 0) {
            return;
        }
        Direction direction = Direction.random(random);
        if (direction == Direction.UP) {
            return;
        }
        BlockPos offset = pos.offset(direction);
        if (state.isOpaque() && world.getBlockState(offset).isSideSolidFullSquare(world, offset, direction.getOpposite())) {
            return;
        }
        double dX = direction.getOffsetX() == 0 ? random.nextDouble() : 0.5 + direction.getOffsetX() * 0.6;
        double dY = direction.getOffsetY() == 0 ? random.nextDouble() : 0.5 + direction.getOffsetY() * 0.6;
        double dZ = direction.getOffsetZ() == 0 ? random.nextDouble() : 0.5 + direction.getOffsetZ() * 0.6;
        world.addParticle(ParticleTypes.DRIPPING_LAVA, pos.getX() + dX, pos.getY() + dY, pos.getZ() + dZ, 0.0, 0.0, 0.0);
    }
}