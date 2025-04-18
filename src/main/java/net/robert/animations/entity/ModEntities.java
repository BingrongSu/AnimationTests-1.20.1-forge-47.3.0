package net.robert.animations.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.robert.animations.Animations;
import net.robert.animations.entity.custom.RingEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES,"animations");

    public static final RegistryObject<EntityType<RingEntity>> RING = ENTITY_TYPES.register("ring",
            () -> EntityType.Builder.of(RingEntity::new, MobCategory.MISC)
                    .sized(3F, 0.1F)
                    .clientTrackingRange(30)
                    .setUpdateInterval(1)
                    .setShouldReceiveVelocityUpdates(true)
                    .build(new ResourceLocation(Animations.MOD_ID, "ring_entity").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

