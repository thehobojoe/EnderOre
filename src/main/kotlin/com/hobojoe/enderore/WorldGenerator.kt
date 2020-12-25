package com.hobojoe.enderore

import com.hobojoe.enderore.config.EnderOreConfig
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.impl.biome.modification.BiomeSelectionContextImpl
import net.minecraft.block.Blocks
import net.minecraft.client.render.SkyProperties
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.decorator.Decorator
import net.minecraft.world.gen.decorator.RangeDecoratorConfig
import net.minecraft.world.gen.feature.ConfiguredFeature
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.OreFeatureConfig

class WorldGenerator {

    private val GENERATE_ORE = Feature.ORE
            .configure(OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    EnderOreMod.ORE_ENDER.defaultState,
                    EnderOreMod.config.oresPerCluster))
            .decorate(Decorator.RANGE.configure(RangeDecoratorConfig(
                    0, // bottom offset
                    EnderOreMod.config.minHeight,
                    EnderOreMod.config.maxHeight, )))
            .spreadHorizontally()
            .repeat(EnderOreMod.config.clusterAmount)

    fun init() {
        val oreOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, Identifier("enderore", "ender_ore_overworld"))
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreOverworld.value, GENERATE_ORE)
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreOverworld)
    }
}
