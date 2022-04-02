package samebutdifferent.ecologics.client.renderer.block;

import com.google.common.collect.ImmutableMap;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.block.entity.SignBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.text.OrderedText;
import net.minecraft.util.DyeColor;
import net.minecraft.util.SignType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Environment(EnvType.CLIENT)
public class ModSignBlockEntityRenderer<T extends SignBlockEntity> implements BlockEntityRenderer<T> {
    private static final int RENDER_DISTANCE = MathHelper.square(16);
    private final Map<SignType, SignBlockEntityRenderer.SignModel> typeToModel;
    private final TextRenderer textRenderer;

    public ModSignBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
        this.typeToModel = SignType.stream().collect(ImmutableMap.toImmutableMap((signType) -> signType, (signType) -> new SignBlockEntityRenderer.SignModel(ctx.getLayerModelPart(EntityModelLayers.createSign(signType)))));
        this.textRenderer = ctx.getTextRenderer();
    }

    public void render(SignBlockEntity signBlockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j) {
        BlockState blockState = signBlockEntity.getCachedState();
        matrixStack.push();
        SignType signType = getSignType(blockState.getBlock());
        SignBlockEntityRenderer.SignModel signModel = this.typeToModel.get(signType);
        float h;
        if (blockState.getBlock() instanceof SignBlock) {
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            h = -((float)(blockState.get(SignBlock.ROTATION) * 360) / 16.0F);
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(h));
            signModel.stick.visible = true;
        } else {
            matrixStack.translate(0.5D, 0.5D, 0.5D);
            h = -blockState.get(WallSignBlock.FACING).asRotation();
            matrixStack.multiply(Vec3f.POSITIVE_Y.getDegreesQuaternion(h));
            matrixStack.translate(0.0D, -0.3125D, -0.4375D);
            signModel.stick.visible = false;
        }

        matrixStack.push();
        matrixStack.scale(0.6666667F, -0.6666667F, -0.6666667F);
        SpriteIdentifier spriteIdentifier = TexturedRenderLayers.getSignTextureId(signType);
        Objects.requireNonNull(signModel);
        VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumerProvider, signModel::getLayer);
        signModel.root.render(matrixStack, vertexConsumer, i, j);
        matrixStack.pop();
        matrixStack.translate(0.0D, 0.3333333432674408D, 0.046666666865348816D);
        matrixStack.scale(0.010416667F, -0.010416667F, 0.010416667F);
        int l = getColor(signBlockEntity);
        OrderedText[] orderedTexts = signBlockEntity.updateSign(MinecraftClient.getInstance().shouldFilterText(), (text) -> {
            List<OrderedText> list = this.textRenderer.wrapLines(text, 90);
            return list.isEmpty() ? OrderedText.EMPTY : list.get(0);
        });
        int n;
        boolean bl;
        int o;
        if (signBlockEntity.isGlowingText()) {
            n = signBlockEntity.getTextColor().getSignColor();
            bl = shouldRender(signBlockEntity, n);
            o = 15728880;
        } else {
            n = l;
            bl = false;
            o = i;
        }

        for(int p = 0; p < 4; ++p) {
            OrderedText orderedText = orderedTexts[p];
            float q = (float)(-this.textRenderer.getWidth(orderedText) / 2);
            if (bl) {
                this.textRenderer.drawWithOutline(orderedText, q, (float)(p * 10 - 20), n, l, matrixStack.peek().getPositionMatrix(), vertexConsumerProvider, o);
            } else {
                this.textRenderer.draw(orderedText, q, (float)(p * 10 - 20), n, false, matrixStack.peek().getPositionMatrix(), vertexConsumerProvider, false, 0, o);
            }
        }

        matrixStack.pop();
    }

    private static boolean shouldRender(SignBlockEntity sign, int signColor) {
        if (signColor == DyeColor.BLACK.getSignColor()) {
            return true;
        } else {
            MinecraftClient minecraftClient = MinecraftClient.getInstance();
            ClientPlayerEntity clientPlayerEntity = minecraftClient.player;
            if (clientPlayerEntity != null && minecraftClient.options.getPerspective().isFirstPerson() && clientPlayerEntity.isUsingSpyglass()) {
                return true;
            } else {
                Entity entity = minecraftClient.getCameraEntity();
                return entity != null && entity.squaredDistanceTo(Vec3d.ofCenter(sign.getPos())) < (double)RENDER_DISTANCE;
            }
        }
    }

    private static int getColor(SignBlockEntity sign) {
        int i = sign.getTextColor().getSignColor();
        int j = (int)((double) NativeImage.getRed(i) * 0.4D);
        int k = (int)((double)NativeImage.getGreen(i) * 0.4D);
        int l = (int)((double)NativeImage.getBlue(i) * 0.4D);
        return i == DyeColor.BLACK.getSignColor() && sign.isGlowingText() ? -988212 : NativeImage.packColor(0, l, k, j);
    }

    public static SignType getSignType(Block block) {
        SignType signType;
        if (block instanceof AbstractSignBlock) {
            signType = ((AbstractSignBlock)block).getSignType();
        } else {
            signType = SignType.OAK;
        }

        return signType;
    }
}
