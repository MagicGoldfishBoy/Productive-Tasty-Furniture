package com.goldfish.goldfishmod03productivetastyfurniture.datagen;

import java.util.concurrent.CompletableFuture;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.core.HolderLookup.Provider;

 public class GM3RecipeDatagen extends RecipeProvider {
    
    private PackOutput output;
    private CompletableFuture<Provider> lookupProvider;

    public GM3RecipeDatagen(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }
    
}
