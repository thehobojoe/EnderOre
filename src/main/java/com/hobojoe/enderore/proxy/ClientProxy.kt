package com.hobojoe.enderore.proxy


import com.hobojoe.enderore.block.ModBlocks
import com.hobojoe.enderore.item.ModItems
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
 * Created by Joseph on 11/18/2016.
 */

@Mod.EventBusSubscriber
class ClientProxy : CommonProxy() {

    companion object {
        @JvmStatic
        @SubscribeEvent
        fun registerModels(event: ModelRegistryEvent) {
            ModBlocks.initModels()
            ModItems.initModels()
        }
    }
}
