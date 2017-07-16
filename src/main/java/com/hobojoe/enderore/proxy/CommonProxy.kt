package com.hobojoe.enderore.proxy

import net.minecraft.item.Item

/**
 * Created by Joseph on 11/18/2016.
 */
open class CommonProxy {
    open fun registerItemRenderer(item: Item, meta: Int, id: String) {}
}
