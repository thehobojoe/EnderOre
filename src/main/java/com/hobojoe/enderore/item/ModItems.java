package com.hobojoe.enderore.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joseph on 11/18/2016.
 */
public class ModItems {

    public static ItemBase dustEnder;

    public static void init() {
        dustEnder = register(new ItemBase("dustEnder").setCreativeTab(CreativeTabs.MATERIALS));
    }

    private static <T extends Item> T register(T item) {
        GameRegistry.register(item);

        if (item instanceof ItemBase) {
            ((ItemBase)item).registerItemModel();
        }

        return item;
    }
}