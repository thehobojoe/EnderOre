package com.hobojoe.enderore.proxy;

import com.hobojoe.enderore.EnderOre;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;

/**
 * Created by Joseph on 11/18/2016.
 */
public class ClientProxy extends CommonProxy {

    @Override
    public void registerItemRenderer(Item item, int meta, String id) {
        ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(EnderOre.MODID + ":" + id, "inventory"));
    }
}
