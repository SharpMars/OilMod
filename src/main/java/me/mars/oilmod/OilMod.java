package me.mars.oilmod;

import me.mars.oilmod.registry.ModItems;
import net.fabricmc.api.ModInitializer;

public class OilMod implements ModInitializer {

    public static final String MOD_ID = "oilmod";

    @Override
    public void onInitialize() {
        ModItems.registerItems();
    }
}
