package com.hobojoe.enderore.proxy

import com.hobojoe.enderore.EnderOre
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.model.ModelLoader

/**
 * Created by Joseph on 11/18/2016.
 */
class ClientProxy : CommonProxy() {

    override fun registerItemRenderer(item: Item, meta: Int, id: String) {
        ModelLoader.setCustomModelResourceLocation(item, meta, ModelResourceLocation(EnderOre.MODID + ":" + id, "inventory"))
    }
}
