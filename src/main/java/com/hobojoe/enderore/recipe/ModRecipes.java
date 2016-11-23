package com.hobojoe.enderore.recipe;

import com.hobojoe.enderore.item.ModItems;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joseph on 11/18/2016.
 */
public class ModRecipes {

    public static void init() {
        GameRegistry.addRecipe(new ItemStack(Items.ENDER_PEARL), "XX",
                "XX",
                'X', ModItems.dustEnder);
        //GameRegistry.addShapedRecipe(new ItemStack(Items.RABBIT_STEW), " R ", "CPM", " B ", 'R', Items.COOKED_RABBIT, 'C', ModItems.corn, 'P', Items.BAKED_POTATO, 'M', Blocks.BROWN_MUSHROOM, 'B', Items.BOWL);
    }
}
