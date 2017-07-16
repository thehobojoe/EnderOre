package com.hobojoe.enderore

import com.hobojoe.enderore.proxy.CommonProxy
import net.minecraftforge.common.config.Configuration
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import java.io.File


@Mod(modid = EnderOre.MODID, modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter")
object EnderOre {

    const val MODID = "enderore"
    const val name = "Ender Ore"
    lateinit var config: Configuration

    @Mod.Instance(MODID)
    lateinit var instance: EnderOre

    @SidedProxy(serverSide = "com.hobojoe.enderore.proxy.CommonProxy", clientSide = "com.hobojoe.enderore.proxy.ClientProxy")
    lateinit var proxy: CommonProxy


    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        println(name + " is loading!")

        val directory = event.modConfigurationDirectory
        config = Configuration(File(directory.path, "enderore.cfg"))
        Config.readConfig()
    }


    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {}


    @Mod.EventHandler
    fun postInit(event: FMLPostInitializationEvent) {
        if (config.hasChanged())
            config.save()
        if (Config.generatesOre)
            GameRegistry.registerWorldGenerator(WorldGen(), 0)
    }
}
