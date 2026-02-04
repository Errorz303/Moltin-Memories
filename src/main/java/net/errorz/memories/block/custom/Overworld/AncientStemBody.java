package net.errorz.memories.block.custom.Overworld;

import com.mojang.serialization.MapCodec;
import net.errorz.memories.block.MemoryBlocks;
import net.minecraft.block.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class AncientStemBody extends AbstractPlantBlock implements Fertilizable, CaveVines {
    public static final MapCodec<AncientStemBody> CODEC = createCodec(AncientStemBody::new);

    public MapCodec<AncientStemBody> getCodec() {
        return CODEC;
    }

    public AncientStemBody(AbstractBlock.Settings settings) {
        super(settings, Direction.DOWN, SHAPE, false);
    }

    protected AbstractPlantStemBlock getStem() {
        return (AbstractPlantStemBlock) MemoryBlocks.ANCIENT_STEM_HEAD;
    }



    public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
        return true;
    }
}
