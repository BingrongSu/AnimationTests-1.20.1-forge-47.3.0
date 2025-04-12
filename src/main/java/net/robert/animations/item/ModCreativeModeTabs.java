package net.robert.animations.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.robert.animations.Animations;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Animations.MOD_ID);

    public static final RegistryObject<CreativeModeTab> SOUL_LAND_TAB = CREATIVE_MODE_TABS.register("soulland_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TEST_01.get()))
                    .title(Component.translatable("creativetab.Animations_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {

                        output.accept(ModItems.TEST_01.get());

                    }))
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
