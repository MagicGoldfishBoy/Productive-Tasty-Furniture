package com.goldfish.goldfishmod03productivetastyfurniture.registry;

import com.goldfish.goldfishmod02tastyfurniture.TastyFurniture;
import com.goldfish.goldfishmod03productivetastyfurniture.ProductiveTastyFurniture;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class mushRegistry {
    
    public static final DeferredRegister<Item> PRODUCTIVE_MUSH = DeferredRegister.create(BuiltInRegistries.ITEM, ProductiveTastyFurniture.MODID);

     //-----------------------------------------------------berries-------------------------------------------------------------------

        public static final DeferredHolder<Item, Item> ELDERBERRY_MUSH = PRODUCTIVE_MUSH.register("elderberry_mush",
                () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                .alwaysEdible().nutrition(1).saturationModifier(0.3f).build())));
}
