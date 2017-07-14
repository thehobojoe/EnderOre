package com.hobojoe.enderore.block


import com.hobojoe.enderore.EnderOre
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemBlock

/**
 * Created by Joseph on 11/18/2016.
 */
open class BlockBase(
        private var material: Material,
        private var name: String) : Block(material) {

    init {
        unlocalizedName = name
        setRegistryName(name)
    }

    fun registerItemModel(itemBlock: ItemBlock) {
        EnderOre.proxy?.registerItemRenderer(itemBlock, 0, name)
    }

    override fun setCreativeTab(tab: CreativeTabs): BlockBase {
        super.setCreativeTab(tab)
        return this
    }
}
