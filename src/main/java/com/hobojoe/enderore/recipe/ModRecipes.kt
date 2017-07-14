package com.hobojoe.enderore.recipe

import com.hobojoe.enderore.item.ModItems
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Created by Joseph on 11/18/2016.
 */
object ModRecipes {

    fun init() {
        GameRegistry.addRecipe(ItemStack(Items.ENDER_PEARL),
                "XX",
                "XX",
                'X', ModItems.dustEnder)
    }
}
