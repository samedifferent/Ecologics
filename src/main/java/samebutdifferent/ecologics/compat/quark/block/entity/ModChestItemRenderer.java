package samebutdifferent.ecologics.compat.quark.block.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModChestItemRenderer extends BlockEntityWithoutLevelRenderer {

    public ModChestItemRenderer(BlockEntityRenderDispatcher pBlockEntityRenderDispatcher, EntityModelSet pEntityModelSet) {
        super(pBlockEntityRenderDispatcher, pEntityModelSet);
    }

    @Override
    public void renderByItem(ItemStack pStack, ItemTransforms.TransformType pTransformType, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, int pPackedOverlay) {
        ModChestRenderer.block = ((BlockItem) pStack.getItem()).getBlock();
        ModChestRenderer.renderer.render((ModChestBlockEntity) ((EntityBlock) ModChestRenderer.block).newBlockEntity(BlockPos.ZERO, ModChestRenderer.block.defaultBlockState()), 0, pPoseStack, pBuffer, pPackedLight, pPackedOverlay);
        ModChestRenderer.block = null;
    }
}
