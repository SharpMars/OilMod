package me.mars.oilmod.registry;

import me.mars.oilmod.OilMod;
import me.mars.oilmod.OilPotion;
import me.mars.oilmod.effect.OiledEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static final StatusEffect OILED = new OiledEffect();

    public static final Item OIL_POTION = new OilPotion(new Item.Settings().group(ItemGroup.MISC).maxCount(1), 3600, false);
    public static final Item OIL_POTION_REAL = new OilPotion(new Item.Settings().group(ItemGroup.MISC).maxCount(1), 3600, false);
    public static final Item OIL_BUCKET = new OilPotion(new Item.Settings().group(ItemGroup.MISC).maxCount(1), 7200, true);


    public static void registerItems()
    {
        Registry.register(Registry.STATUS_EFFECT, new Identifier(OilMod.MOD_ID, "oiled"), OILED);
        Registry.register(Registry.ITEM, new Identifier(OilMod.MOD_ID, "oil_potion"), OIL_POTION);
        Registry.register(Registry.ITEM, new Identifier(OilMod.MOD_ID, "oil_bottle"), OIL_POTION_REAL);
        Registry.register(Registry.ITEM, new Identifier(OilMod.MOD_ID, "oil_bucket"), OIL_BUCKET);
    }
}
