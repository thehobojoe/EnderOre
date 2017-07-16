package com.hobojoe.enderore.item

import com.hobojoe.enderore.EnderOre
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

/**
 * Created by Joseph on 11/18/2016.
 */
class ItemEnderDust : Item() {

    private var name = "dust_ender"

    init {
        unlocalizedName = "${EnderOre.MODID}.$name"
        setRegistryName(name)
        setCreativeTab(CreativeTabs.MISC)
    }

    fun registerItemModel() {
        EnderOre.proxy.registerItemRenderer(this, 0, name)
    }
}