package net.robert.animations.event;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.robert.animations.entity.client.ModModelLayers;
import net.robert.animations.Animations;
import net.robert.animations.entity.client.RingModel;

@Mod.EventBusSubscriber(modid = Animations.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.RING_LAYER, RingModel::createBodyLayer);
    }
}
