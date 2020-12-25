package com.hobojoe.enderore

import com.hobojoe.enderore.config.EnderOreConfig
import me.sargunvohra.mcmods.autoconfig1u.AutoConfig
import me.sargunvohra.mcmods.autoconfig1u.ConfigHolder
import me.sargunvohra.mcmods.autoconfig1u.serializer.Toml4jConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

class EnderOreMod : ModInitializer {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    val DUST_ENDER = Item(FabricItemSettings().group(ItemGroup.MISC))
    val ORE_ENDER = BlockEnderOre(FabricBlockSettings.of(Material.METAL).hardness(3F))

    override fun onInitialize() {
        println("Ender Ore Initialized")

        AutoConfig.register(EnderOreConfig::class.java, ::Toml4jConfigSerializer)
        config_ = AutoConfig.getConfigHolder(EnderOreConfig::class.java)

        Registry.register(Registry.ITEM, Identifier("enderore", "dust_ender"), DUST_ENDER)
        Registry.register(Registry.BLOCK, Identifier("enderore", "ore_ender"), ORE_ENDER)
        Registry.register(Registry.ITEM, Identifier("enderore", "ore_ender"), BlockItem(ORE_ENDER, Item.Settings().group(ItemGroup.MISC)))
    }

    companion object {
        const val MODID = "enderore"

        private lateinit var config_: ConfigHolder<EnderOreConfig>
        val config: EnderOreConfig
            get() = config_.config
    }
}

