package com.hobojoe.enderore.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Created by Joseph on 11/18/2016.
 */
object ModItems {
    lateinit var dustEnder: ItemBase

    fun init() {
        dustEnder = register(ItemBase("dust_ender").setCreativeTab(CreativeTabs.MATERIALS))
    }

    private fun <T : Item> register(item: T): T {
        GameRegistry.register(item)

        if (item is ItemBase) {
            item.registerItemModel()
        }

        return item
    }
}