package com.hobojoe.enderore.block;

import com.hobojoe.enderore.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by Joseph on 11/18/2016.
 */
public class ModBlocks {

    public static BlockEnderOre oreEnder;

    public static void init() {
        oreEnder = register(new BlockEnderOre("oreEnder", ModItems.dustEnder, 1, 2));
    }

    private static <T extends Block> T register(T block, ItemBlock itemBlock) {
        GameRegistry.register(block);
        GameRegistry.register(itemBlock);

        if (block instanceof BlockBase) {
            ((BlockBase)block).registerItemModel(itemBlock);
        }

        return block;
    }

    private static <T extends Block> T register(T block) {
        ItemBlock itemBlock = new ItemBlock(block);
        itemBlock.setRegistryName(block.getRegistryName());
        return register(block, itemBlock);
    }

}