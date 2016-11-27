package com.hobojoe.enderore.block;

import com.hobojoe.enderore.Config;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by Joseph on 11/18/2016.
 */
public class BlockEnderOre extends BlockBase {

    private Item drop;
    private int least_quantity;
    private int most_quantity;

    protected BlockEnderOre(String name, Item drop, int least_quantity, int most_quantity) {
        super(Material.ROCK, name);

        this.drop = drop;
        this.least_quantity = least_quantity;
        this.most_quantity = most_quantity;

        setHardness(3f);
        setResistance(5f);
        this.setHarvestLevel("pickaxe", 2);
        this.setCreativeTab(CreativeTabs.MATERIALS);
    }


    @Override
    public Item getItemDropped(IBlockState state, Random random, int fortune) {
        return this.drop;
    }

    @Override
    public int quantityDropped(IBlockState state, int fortune, Random random) {
        if (this.least_quantity >= this.most_quantity)
            return this.least_quantity;
        return this.least_quantity + random.nextInt(this.most_quantity - this.least_quantity + fortune + 1);
    }

    @Override
    public void dropBlockAsItemWithChance( final World w, final BlockPos pos, final IBlockState state, final float chance, final int fortune )
    {
        super.dropBlockAsItemWithChance( w, pos, state, chance, fortune );

        if( this.getItemDropped( state, w.rand, fortune ) != Item.getItemFromBlock(this)) {
            final int xp = MathHelper.getRandomIntegerInRange( w.rand, 2, 5 );

            this.dropXpOnBlockBreak( w, pos, xp );
        }
    }

    @Override
    public int quantityDroppedWithBonus( final int fortune, final Random rand ) {
        if( fortune > 0 && Item.getItemFromBlock( this ) != this.getItemDropped( null, rand, fortune ) ) {
            int j = rand.nextInt( fortune + 2 ) - 1;

            if( j < 0 ) {
                j = 0;
            }

            return this.quantityDropped( rand ) * ( j + 1 );
        } else {
            return this.quantityDropped( rand );
        }
    }

    @Override
    public void harvestBlock(World world, EntityPlayer entityplayer, BlockPos pos, IBlockState state, @Nullable TileEntity te, @Nullable ItemStack stack) {
        super.harvestBlock(world, entityplayer, pos, state, te, stack);
        int rand = world.rand.nextInt(100);
        if(rand < 20) {
            if (!world.isRemote && world.getDifficulty() != EnumDifficulty.PEACEFUL && Config.spawnsEnderman) {
                int tries = world.rand.nextInt(20);
                for (int i = 0; i < tries; i++) {
                    int spawnX = pos.getX() + world.rand.nextInt(3) - world.rand.nextInt(3);
                    int spawnY = pos.getY() + world.rand.nextInt(3) - world.rand.nextInt(3);
                    int spawnZ = pos.getZ() + world.rand.nextInt(3) - world.rand.nextInt(3);
                    if (canSpawnEnder(world, pos)) {
                        EntityEnderman ender = new EntityEnderman(world);
                        ender.setLocationAndAngles((double) spawnX + world.rand.nextDouble(), (double) spawnY + world.rand.nextDouble(), (double) spawnZ + world.rand.nextDouble(), world.rand.nextFloat(), world.rand.nextFloat());
                        world.spawnEntityInWorld(ender);
                        ender.spawnExplosionParticle();
                        ender.playSound(new SoundEvent(new ResourceLocation("entity.endermen.teleport")), 1.0F, 1.0F);
                        break;
                    }
                }
            }
        }
    }

    private boolean canSpawnEnder(World world, BlockPos pos) {
        if (world.isAirBlock(pos)) {
            if (world.isAirBlock(pos.add(0, 1, 0))) {
                if (world.isAirBlock(pos.add(0, 2, 0))) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public BlockEnderOre setCreativeTab(CreativeTabs tab) {
        super.setCreativeTab(tab);
        return this;
    }

}