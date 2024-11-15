package com.goldfish.goldfishmod03productivetastyfurniture;

import  com.goldfish.goldfishmod03productivetastyfurniture.registry.mushRegistry;

import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;

import com.goldfish.goldfishmod02tastyfurniture.datagen.GM1BlockLootTableProvider;
import com.goldfish.goldfishmod02tastyfurniture.datagen.GM1LootTableProvider;
import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(ProductiveTastyFurniture.MODID)
public class ProductiveTastyFurniture
{
    public static final String MODID = "goldfishmod03productivetastyfurniture";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister.Items PRODUCTIVE_MUSH = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PRODUCTIVE_TASTY_FURNITURE_TAB = CREATIVE_MODE_TABS.register("productive_tasty_furniture_tab", () -> CreativeModeTab.builder()
    .title(Component.translatable("itemGroup.productivetastyfurniture"))
    .withTabsBefore(CreativeModeTabs.COMBAT)
    .icon(() -> mushRegistry.ELDERBERRY_MUSH.get().getDefaultInstance())
    .displayItems((parameters, output) -> {
        output.acceptAll(mushRegistry.PRODUCTIVE_MUSH.getEntries().stream().map(sup -> {
            return sup.get().getDefaultInstance();
        }).toList());
    }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> gettab(){
      return PRODUCTIVE_TASTY_FURNITURE_TAB;
}

    public ProductiveTastyFurniture(IEventBus modEventBus, ModContainer modContainer)
    {
        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register the Deferred Register to the mod event bus so blocks get registered
        BLOCKS.register(modEventBus);
        // Register the Deferred Register to the mod event bus so items get registered
        ITEMS.register(modEventBus);

        mushRegistry.PRODUCTIVE_MUSH.register(modEventBus);
        // Register the Deferred Register to the mod event bus so tabs get registered
        CREATIVE_MODE_TABS.register(modEventBus);

        

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);

        // Register our mod's ModConfigSpec so that FML can create and load the config file for us
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");

        if (Config.logDirtBlock)
            LOGGER.info("DIRT BLOCK >> {}", BuiltInRegistries.BLOCK.getKey(Blocks.DIRT));

        LOGGER.info(Config.magicNumberIntroduction + Config.magicNumber);

        Config.items.forEach((item) -> LOGGER.info("ITEM >> {}", item.toString()));
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }

// public class datagathering {
//      @SubscribeEvent
//      public static void onGatherData(GatherDataEvent event) {

//         try {
//         DataGenerator generator = event.getGenerator();
//         PackOutput output = generator.getPackOutput();
//         ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
//         CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
//         generator.addProvider(event.includeClient(), new GM1LootTableProvider(output, lookupProvider));
//         new SubProviderEntry(
//             GM1BlockLootTableProvider::new,
//             LootContextParamSets.EMPTY
//         );
//         LOGGER.info("hello from robot heck");
//         } catch (RuntimeException e) {
//             LOGGER.error("failed to generate blockstates");
//         }
        
//         }
//        }
}
