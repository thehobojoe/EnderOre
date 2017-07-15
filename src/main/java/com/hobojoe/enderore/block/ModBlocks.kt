package com.hobojoe.enderore.block

import com.hobojoe.enderore.item.ModItems
import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

/**
 * Created by Joseph on 11/18/2016.
 */
object ModBlocks {

    lateinit var oreEnder: BlockEnderOre

    fun init() {
        oreEnder = register(BlockEnderOre("ore_ender"))
    }

    private fun <T : Block> register(block: T, itemBlock: ItemBlock): T {
        GameRegistry.register(block)
        GameRegistry.register(itemBlock)

        if (block is BlockBase) {
            block.registerItemModel(itemBlock)
        }

        return block
    }

    private fun <T : Block> register(block: T): T {
        val itemBlock = ItemBlock(block)
        itemBlock.registryName = block.registryName
        return register(block, itemBlock)
    }
}