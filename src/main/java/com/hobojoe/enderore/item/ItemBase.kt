package com.hobojoe.enderore.item

import com.hobojoe.enderore.EnderOre
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item

/**
 * Created by Joseph on 11/18/2016.
 */
class ItemBase(private var name: String) : Item() {

    init {
        unlocalizedName = name
        setRegistryName(name)
    }

    fun registerItemModel() {
        EnderOre.proxy?.registerItemRenderer(this, 0, name)
    }

    override fun setCreativeTab(tab: CreativeTabs): ItemBase {
        super.setCreativeTab(tab)
        return this
    }
}