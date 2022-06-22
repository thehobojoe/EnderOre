package com.hobojoe.enderore

import com.hobojoe.enderore.config.EnderOreConfig
import draylar.omegaconfig.OmegaConfig
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.item.v1.FabricItemSettings
import net.minecraft.block.Material
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroup
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

object EnderOreMod : ModInitializer {
    // This code runs as soon as Minecraft is in a mod-load-ready state.
    // However, some things (like resources) may still be uninitialized.
    // Proceed with mild caution.

    private val SHARED_SETTINGS = FabricBlockSettings.of(Material.STONE).strength(3F).requiresTool()

    const val MODID = "enderore"

    val DUST_ENDER = Item(FabricItemSettings().group(ItemGroup.MISC))
    val ORE_ENDER = BlockEnderOre(SHARED_SETTINGS)
    val DEEPSLATE_ORE_ENDER = BlockEnderOre(SHARED_SETTINGS.sounds(BlockSoundGroup.DEEPSLATE))
    val CONFIG: EnderOreConfig = OmegaConfig.register(EnderOreConfig::class.java)

    override fun onInitialize() {
        println("Ender Ore Initialized")

        Registry.register(Registry.ITEM, Identifier(MODID, "dust_ender"), DUST_ENDER)

        Registry.register(Registry.BLOCK, Identifier(MODID, "ore_ender"), ORE_ENDER)
        Registry.register(Registry.ITEM, Identifier(MODID, "ore_ender"), BlockItem(ORE_ENDER, Item.Settings().group(ItemGroup.MATERIALS)))

        Registry.register(Registry.BLOCK, Identifier(MODID, "deepslate_ore_ender"), DEEPSLATE_ORE_ENDER)
        Registry.register(Registry.ITEM, Identifier(MODID, "deepslate_ore_ender"), BlockItem(DEEPSLATE_ORE_ENDER, Item.Settings().group(ItemGroup.MATERIALS)))

        WorldGenerator.registerOregen();
    }
}

