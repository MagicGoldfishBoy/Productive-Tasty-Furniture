package com.goldfish.goldfishmod03productivetastyfurniture.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class GM3ItemModelDatagen extends ItemModelProvider {

        public GM3ItemModelDatagen(PackOutput output, String modid, ExistingFileHelper existingFileHelper) {
        super(output, "goldfishmod03productivetastyfurniture", existingFileHelper);
    }

    @Override
    protected void registerModels() {
        buildMushItemModels();
    }

    protected void buildMushItemModels() {
        withExistingParent("elderberry_mush", mcLoc("item/generated")).texture("layer0", "goldfishmod03productivefurniture:item/elderberry_mush");
    }
    
}
