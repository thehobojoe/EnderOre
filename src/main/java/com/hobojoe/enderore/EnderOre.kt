package com.hobojoe.enderore

import com.hobojoe.enderore.block.ModBlocks
import com.hobojoe.enderore.item.ModItems
import com.hobojoe.enderore.proxy.CommonProxy
import com.hobojoe.enderore.recipe.ModRecipes
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry

import java.io.File

@Mod(modid = EnderOre.MODID, name = EnderOre.name, version = EnderOre.VERSION)
class EnderOre {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        println(name + " is loading!")
        ModItems.init()
        ModBlocks.init()

        val directory = event.modConfigurationDirectory
        config = Configuration(File(directory.path, "enderore.cfg"))
        Config.readConfig()
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        ModRecipes.init()
    }

    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        if (config.hasChanged())
            config.save()
        if (Config.generatesOre)
            GameRegistry.registerWorldGenerator(WorldGen(), 0)
    }

    companion object {
        const val MODID = "enderore"
        const val name = "Ender Ore"
        const val VERSION = "1.0"

        lateinit var config: Configuration


        @Mod.Instance(MODID)
        var instance: EnderOre? = null

        @SidedProxy(serverSide = "com.hobojoe.enderore.proxy.CommonProxy", clientSide = "com.hobojoe.enderore.proxy.ClientProxy")
        var proxy: CommonProxy? = null
    }
}
