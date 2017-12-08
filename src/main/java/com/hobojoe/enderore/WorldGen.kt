package com.hobojoe.enderore

import com.hobojoe.enderore.block.ModBlocks
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.IChunkProvider
import net.minecraft.world.gen.IChunkGenerator
import net.minecraft.world.gen.feature.WorldGenMinable
import net.minecraftforge.fml.common.IWorldGenerator
import java.util.*

/**
 * Created by Joseph on 11/22/2016.
 */
class WorldGen : IWorldGenerator {

    var ore: WorldGenMinable

    init {
        val oreDefinition = ModBlocks.oreEnder
        this.ore = WorldGenMinable(oreDefinition.defaultState, 4)
    }

    override fun generate(r: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider) {

        val oreDepthMultiplier = Config.clusterAmount.toDouble()
        val scale = Math.round(r.nextGaussian() * Math.sqrt(oreDepthMultiplier) + oreDepthMultiplier).toInt()

        val heightMin = Config.minHeight
        val heightMax = Config.maxHeight

        val pos = if (r.nextBoolean())
                    scale * 2
                else scale / 2 - 1

        for (x in 0..pos) {
            val cx = chunkX * 16 + r.nextInt(22)
            val cy = r.range(heightMin, heightMax)
            val cz = chunkZ * 16 + r.nextInt(22)
            this.ore.generate(world, r, BlockPos(cx, cy, cz))
        }
    }
}
