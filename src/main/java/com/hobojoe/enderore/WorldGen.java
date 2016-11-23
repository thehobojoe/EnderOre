package com.hobojoe.enderore;

import com.hobojoe.enderore.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * Created by Joseph on 11/22/2016.
 */
public class WorldGen implements IWorldGenerator {

    private final WorldGenMinable ore;

    public WorldGen() {
        final Block oreDefinition = ModBlocks.oreEnder;
        this.ore = new WorldGenMinable(oreDefinition.getDefaultState(), 4);
    }

    @Override
    public void generate(Random r, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if(this.ore == null) return;

        final double oreDepthMultiplier = Config.clusterAmount;
        final int scale = (int) Math.round( r.nextGaussian() * Math.sqrt( oreDepthMultiplier ) + oreDepthMultiplier );

        final int heightMin = Config.minHeight;
        final int heightMax = Config.maxHeight;

        for( int x = 0; x < ( r.nextBoolean() ? scale * 2 : scale ) / 2; ++x ) {
            if(Config.generatesOre) {
                final int cx = chunkX * 16 + r.nextInt( 22 );
                final int cy = MathHelper.getRandomIntegerInRange( r, heightMin, heightMax );
                final int cz = chunkZ * 16 + r.nextInt( 22 );
                this.ore.generate( world, r, new BlockPos( cx, cy, cz ) );
            }
        }
    }
}
