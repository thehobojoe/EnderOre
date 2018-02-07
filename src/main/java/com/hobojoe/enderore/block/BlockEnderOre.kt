package com.hobojoe.enderore.block

import com.hobojoe.enderore.Config
import com.hobojoe.enderore.EnderOre
import com.hobojoe.enderore.item.ItemEnderDust
import com.hobojoe.enderore.item.ModItems
import com.hobojoe.enderore.range
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.monster.EntityEnderman
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.init.Enchantments
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ResourceLocation
import net.minecraft.util.SoundEvent
import net.minecraft.util.math.BlockPos
import net.minecraft.world.EnumDifficulty
import net.minecraft.world.World
import java.util.*

/**
 * Created by Joseph on 11/18/2016.
 */
class BlockEnderOre : BlockBase(Material.ROCK) {

    private val name = "ore_ender"
    private val leastDrop = 1
    private val mostDrop = 2

    init {
        unlocalizedName = "${EnderOre.MODID}.$name"
        setRegistryName(name)
        setHardness(3f)
        setResistance(5f)
        setHarvestLevel("pickaxe", 2)
        setCreativeTab(CreativeTabs.MISC)
    }


    override fun getItemDropped(state: IBlockState?, random: Random?, fortune: Int): Item? {
        if(Config.dropsPearls)
            return Items.ENDER_PEARL
        return ModItems.dustEnder
    }

    override fun quantityDropped(state: IBlockState?, fortune: Int, random: Random): Int {
        if(Config.dropsPearls) {
            return random.range(1, 1 + fortune)
        }
        return random.range(leastDrop, mostDrop + fortune)
    }

    override fun dropBlockAsItemWithChance(w: World, pos: BlockPos, state: IBlockState, chance: Float, fortune: Int) {
        super.dropBlockAsItemWithChance(w, pos, state, chance, fortune)

        if (this.getItemDropped(state, w.rand, fortune) != Item.getItemFromBlock(this)) {
            val bonus = fortune * w.rand.range(0, 2)
            val xp = w.rand.range(2, 5 + bonus)
            this.dropXpOnBlockBreak(w, pos, xp)
        }
    }

    override fun quantityDroppedWithBonus(fortune: Int, rand: Random): Int {
        if (fortune > 0 && Item.getItemFromBlock(this) != this.getItemDropped(null, rand, fortune)) {
            var j = rand.nextInt(fortune + 2) - 1

            if (j < 0)
                j = 1

            return this.quantityDropped(rand) * j
        } else {
            return this.quantityDropped(rand)
        }
    }

    override fun harvestBlock(world: World, entityplayer: EntityPlayer, pos: BlockPos, state: IBlockState, te: TileEntity?, stack: ItemStack?) {
        super.harvestBlock(world, entityplayer, pos, state, te, stack)
        val rand = world.rand.nextInt(100)
        if (rand < 20) {

            if (!world.isRemote
                    && world.difficulty != EnumDifficulty.PEACEFUL
                    && EnchantmentHelper.getEnchantmentLevel(Enchantments.SILK_TOUCH, stack) <= 0
                    && Config.spawnsEnderman) {
                val tries = world.rand.nextInt(20)
                for (i in 0 until tries) {
                    val spawnX = pos.x + world.rand.nextInt(3) - world.rand.nextInt(3)
                    val spawnY = pos.y + world.rand.nextInt(3) - world.rand.nextInt(3)
                    val spawnZ = pos.z + world.rand.nextInt(3) - world.rand.nextInt(3)
                    if (canSpawnEnder(world, BlockPos(spawnX, spawnY, spawnZ))) {
                        val ender = EntityEnderman(world)
                        ender.setLocationAndAngles(spawnX.toDouble() + world.rand.nextDouble(),
                                spawnY.toDouble() + 0,
                                spawnZ.toDouble() + world.rand.nextDouble(),
                                world.rand.nextFloat(),
                                world.rand.nextFloat())
                        world.spawnEntity(ender)
                        ender.spawnExplosionParticle()
                        ender.playSound(SoundEvent(ResourceLocation("entity.endermen.teleport")), 1.0f, 1.0f)
                        break
                    }
                }
            }
        }
    }

    private fun canSpawnEnder(world: World, pos: BlockPos): Boolean {
        if (world.isAirBlock(pos)) {
            if (world.isAirBlock(pos.add(0, 1, 0))) {
                if (world.isAirBlock(pos.add(0, 2, 0))) {
                    return true
                }
            }
        }
        return false
    }

    override fun setCreativeTab(tab: CreativeTabs): BlockEnderOre {
        super.setCreativeTab(tab)
        return this
    }

}