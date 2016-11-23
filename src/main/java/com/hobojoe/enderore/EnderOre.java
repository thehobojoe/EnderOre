package com.hobojoe.enderore;

import com.hobojoe.enderore.block.ModBlocks;
import com.hobojoe.enderore.item.ModItems;
import com.hobojoe.enderore.proxy.CommonProxy;
import com.hobojoe.enderore.recipe.ModRecipes;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;

@Mod(modid = EnderOre.MODID, name = EnderOre.name, version = EnderOre.VERSION)
public class EnderOre {
    public static final String MODID = "enderore";
    public static final String name = "Ender Ore";
    public static final String VERSION = "1.0";

    public static Configuration config;


    @Mod.Instance(MODID)
    public static EnderOre instance;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println(name + " is loading!");
        ModItems.init();
        ModBlocks.init();

        File directory = event.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "enderore.cfg"));
        Config.readConfig();
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ModRecipes.init();
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if (config.hasChanged()) {
            config.save();
        }
        GameRegistry.registerWorldGenerator(new WorldGen(), 0);
    }

    @SidedProxy(serverSide = "com.hobojoe.enderore.proxy.CommonProxy", clientSide = "com.hobojoe.enderore.proxy.ClientProxy")
    public static CommonProxy proxy;
}
