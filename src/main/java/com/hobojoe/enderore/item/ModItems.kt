package com.hobojoe.enderore.item

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Created by Joseph on 11/18/2016.
 */
object ModItems {
    lateinit var dustEnder: ItemEnderDust

    fun init() {
        dustEnder = register(ItemEnderDust())
    }

    private fun <T : Item> register(item: T): T {
        GameRegistry.register(item)

        if (item is ItemEnderDust) {
            item.registerItemModel()
        }

        return item
    }
}