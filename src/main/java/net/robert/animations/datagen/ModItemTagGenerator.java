package net.robert.animations.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.robert.animations.Animations;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, Animations.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        this.tag(ModTags.Items.AL_FURNACE_FUEL)
//                .add(ModItems.AL_FURNACE_FUEL_LV1.get())
//                .add(ModItems.AL_FURNACE_FUEL_LV2.get());
//        this.tag(ModTags.Items.PILL_RECIPE)
//                .add(ModItems.INCREASE_SOUL_POWER_PR_LV1.get())
//                .add(ModItems.INCREASE_SOUL_POWER_PR_LV2.get());
//        this.tag(ModTags.Items.PLANTS)
//                .add(ModItems.PLANT_01.get());
    }
}
