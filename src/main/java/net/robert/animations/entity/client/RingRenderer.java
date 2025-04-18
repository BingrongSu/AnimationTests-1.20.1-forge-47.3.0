package net.robert.animations.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.robert.animations.Animations;
import net.robert.animations.entity.custom.RingEntity;

import static net.robert.animations.entity.client.ModModelLayers.RING_LAYER;

public class RingRenderer extends EntityRenderer<RingEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Animations.MOD_ID, "textures/entity/ringmodel.png");

    private final RingModel model;

    public RingRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model =  new RingModel<>(context.bakeLayer(RING_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(RingEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(RingEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.translate(0.0D, -1.4D, 0.0D);
        poseStack.scale(3.0F, 1.0F, 3.0F);
        float time = (entity.tickCount + partialTicks) % 360;
        poseStack.mulPose(Axis.YP.rotationDegrees((float) (time*1.5)));
        poseStack.translate(0.0D, entity.getBobbingOffset(), 0.0D);
        boolean rendering = true;
        if ((entity.getOwnerUUID().isPresent())) {
            Player player= entity.level().getPlayerByUUID(entity.getOwnerUUID().get());
            if(player!=null) {
                if (player.isSpectator() || player.isInvisible()) {
                    rendering = false;
                }
            }
        }

        if(rendering) {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(model.renderType(TEXTURE));
            this.model.renderToBuffer(poseStack, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1.0F);
        }
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, bufferSource, packedLight);
    }
}
