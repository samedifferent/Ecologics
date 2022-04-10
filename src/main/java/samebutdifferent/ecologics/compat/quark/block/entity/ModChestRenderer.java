package samebutdifferent.ecologics.compat.quark.block.entity;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.ChestRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import samebutdifferent.ecologics.compat.quark.block.ChestVariant;
import samebutdifferent.ecologics.compat.quark.block.IChestBlock;

@OnlyIn(Dist.CLIENT)
public class ModChestRenderer extends ChestRenderer<ModChestBlockEntity> {
    public static Block block = null;
    public static ModChestRenderer renderer;
    public boolean xmasTextures;

    public ModChestRenderer(BlockEntityRendererProvider.Context pContext) {
        super(pContext);
        renderer = this;
    }

    @Override
    protected Material getMaterial(ModChestBlockEntity blockEntity, ChestType chestType) {
        if (this.xmasTextures) {
            return switch (chestType) {
                case RIGHT -> Sheets.CHEST_XMAS_LOCATION_RIGHT;
                case LEFT -> Sheets.CHEST_XMAS_LOCATION_LEFT;
                default -> Sheets.CHEST_XMAS_LOCATION;
            };
        } else {
            Block chestBlock = block;
            if (chestBlock == null) {
                chestBlock = blockEntity.getBlockState().getBlock();
            }
            ChestVariant chestVariant = ((IChestBlock) chestBlock).getVariant();

            if (chestVariant == null)
                return null;

            return switch (chestType) {
                case RIGHT -> getRenderMaterial(chestVariant.getRight());
                case LEFT -> getRenderMaterial(chestVariant.getLeft());
                default -> getRenderMaterial(chestVariant.getSingle());
            };
        }
    }

    private Material getRenderMaterial(ResourceLocation resource) {
        return new Material(Sheets.CHEST_SHEET, resource);
    }
}
