package com.hobojoe.enderore.proxy


import com.hobojoe.enderore.block.BlockEnderOre
import com.hobojoe.enderore.block.ModBlocks
import com.hobojoe.enderore.item.ItemEnderDust
import com.hobojoe.enderore.item.ModItems
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
 * Created by Joseph on 11/18/2016.
 */

@Mod.EventBusSubscriber
open class CommonProxy {


    companion object {
        @JvmStatic
        @SubscribeEvent
        fun registerBlocks(event: RegistryEvent.Register<Block>) {
            event.registry.register(BlockEnderOre())
        }

        @JvmStatic
        @SubscribeEvent
        fun registerItems(event: RegistryEvent.Register<Item>) {
            event.registry.register(ItemEnderDust())
            event.registry.register(ItemBlock(ModBlocks.oreEnder).setRegistryName(ModBlocks.oreEnder.registryName))
        }
    }
}
