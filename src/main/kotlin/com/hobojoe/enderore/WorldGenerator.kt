package com.hobojoe.enderore

import com.hobojoe.enderore.EnderOreMod.DEEPSLATE_ORE_ENDER
import com.hobojoe.enderore.EnderOreMod.MODID
import com.hobojoe.enderore.EnderOreMod.ORE_ENDER
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.util.Identifier
import net.minecraft.util.registry.BuiltinRegistries
import net.minecraft.util.registry.Registry
import net.minecraft.util.registry.RegistryEntry
import net.minecraft.util.registry.RegistryKey
import net.minecraft.world.gen.GenerationStep
import net.minecraft.world.gen.YOffset
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier
import java.util.*

object WorldGenerator {


    var ENDER_ORE_CONFIGURED_FEATURE: ConfiguredFeature<*, *> = ConfiguredFeature(
        Feature.ORE, OreFeatureConfig(
            OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
            ORE_ENDER.defaultState,
            EnderOreMod.CONFIG.oresPerCluster
        )
    )

    var ENDER_ORE_PLACED_FEATURE = PlacedFeature(
        RegistryEntry.of(ENDER_ORE_CONFIGURED_FEATURE),
        Arrays.asList(
            CountPlacementModifier.of(EnderOreMod.CONFIG.clusterAmount),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(EnderOreMod.CONFIG.maxHeight))
        )
    )

    var ENDER_ORE_DEEPSLATE_CONFIGURED_FEATURE: ConfiguredFeature<*, *> = ConfiguredFeature(
        Feature.ORE, OreFeatureConfig(
            OreConfiguredFeatures.DEEPSLATE_ORE_REPLACEABLES,
            DEEPSLATE_ORE_ENDER.defaultState,
            EnderOreMod.CONFIG.oresPerCluster
        )
    )

    var ENDER_ORE_DEEPSLATE_PLACED_FEATURE = PlacedFeature(
        RegistryEntry.of(ENDER_ORE_DEEPSLATE_CONFIGURED_FEATURE),
        Arrays.asList(
            CountPlacementModifier.of(EnderOreMod.CONFIG.clusterAmount),
            SquarePlacementModifier.of(),
            HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(EnderOreMod.CONFIG.maxHeight))
        )
    )

    fun registerOregen() {
        if(EnderOreMod.CONFIG.generatesOre) {
            Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                Identifier(MODID, "ender_ore"), ENDER_ORE_CONFIGURED_FEATURE
            )
            Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                Identifier(MODID, "ender_ore"), ENDER_ORE_PLACED_FEATURE
            )
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(
                    Registry.PLACED_FEATURE_KEY,
                    Identifier(MODID, "ender_ore")
                )
            )
            Registry.register(
                BuiltinRegistries.CONFIGURED_FEATURE,
                Identifier(MODID, "deepslate_ender_ore"), ENDER_ORE_DEEPSLATE_CONFIGURED_FEATURE
            )
            Registry.register(
                BuiltinRegistries.PLACED_FEATURE,
                Identifier(MODID, "deepslate_ender_ore"), ENDER_ORE_DEEPSLATE_PLACED_FEATURE
            )
            BiomeModifications.addFeature(
                BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
                RegistryKey.of(
                    Registry.PLACED_FEATURE_KEY,
                    Identifier(MODID, "deepslate_ender_ore")
                )
            )
        }
    }
}
