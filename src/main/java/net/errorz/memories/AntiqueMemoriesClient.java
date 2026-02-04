package net.errorz.memories;

import net.errorz.memories.block.MemoryBlocks;
import net.errorz.memories.entity.ModEntities;
import net.errorz.memories.entity.client.BasaltBadgerEntityRenderer;
import net.errorz.memories.entity.client.MoltRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class AntiqueMemoriesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.ANCIENT_SALVIA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.ANTIQUE_ROSES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.SOUL_GENUS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.SOUL_BEET, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.ANCIENT_STEM_BODY, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(MemoryBlocks.ANCIENT_STEM_HEAD, RenderLayer.getCutout());

        EntityRendererRegistry.register(ModEntities.BASALT_BADGER, BasaltBadgerEntityRenderer::new);
        EntityRendererRegistry.register(ModEntities.MOLT, MoltRenderer::new);
    }
}