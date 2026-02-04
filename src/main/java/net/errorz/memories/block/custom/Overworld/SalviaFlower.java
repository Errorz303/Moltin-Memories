package net.errorz.memories.block.custom.Overworld;

import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class SalviaFlower extends SnifferFlowers {
    public SalviaFlower(AbstractBlock.Settings settings) {
        super(settings);
    }

    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return super.canPlantOnTop(floor, world, pos) || floor.isOf(Blocks.SAND) || floor.isOf(Blocks.DIRT) || floor.isOf(Blocks.COARSE_DIRT) || floor.isOf(Blocks.GRAVEL) || floor.isOf(Blocks.FARMLAND) || floor.isOf(Blocks.DEEPSLATE);
    }

    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        VoxelShape voxelShape = this.getOutlineShape(state, world, pos, ShapeContext.absent());
        Vec3d vec3d = voxelShape.getBoundingBox().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;

        for(int i = 0; i < 3; ++i) {
            if (random.nextBoolean()) {
                world.addParticle(ParticleTypes.GLOW, d + random.nextDouble() / (double)5.0F, (double)pos.getY() + ((double)0.5F - random.nextDouble()), e + random.nextDouble() / (double)5.0F, (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }

    }
}