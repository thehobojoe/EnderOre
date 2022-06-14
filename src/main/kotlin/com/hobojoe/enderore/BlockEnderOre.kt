package com.hobojoe.enderore

import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.EndermanEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.particle.ParticleEffect
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.Difficulty
import net.minecraft.world.World

class BlockEnderOre(settings: Settings?) : Block(settings) {

    override fun onBreak(world: World, pos: BlockPos, state: BlockState?, player: PlayerEntity) {
        super.onBreak(world, pos, state, player)
        val rand = world.random
        val chance = rand.nextInt(100)

        val enchants = EnchantmentHelper.get(player.mainHandStack);
        val hasSilkTouch = enchants.any { it.key === Enchantments.SILK_TOUCH }
        val spawnEndermen = EnderOreMod.CONFIG.spawnsEnderman

        if(
            world.difficulty != Difficulty.PEACEFUL
            && spawnEndermen
            && !hasSilkTouch
            && chance < EnderOreMod.CONFIG.endermanChance
        ) {
            val tries = rand.nextInt(20)
            for (i in 0 until tries) {
                val x = pos.x + rand.nextInt(3) - rand.nextInt(3)
                val y = pos.y + rand.nextInt(3) - rand.nextInt(3)
                val z = pos.z + rand.nextInt(3) - rand.nextInt(3)
                if(canSpawnEnder(world, BlockPos(x, y, z))) {
                    val ender = EndermanEntity(EntityType.ENDERMAN, world)
                    ender.updatePositionAndAngles(
                        x.toDouble(),
                        y.toDouble(),
                        z.toDouble(),
                        rand.nextFloat(),
                        rand.nextFloat())
                    world.spawnEntity(ender)
                    ender.playSound(SoundEvent(Identifier("entity.enderman.teleport")), 1F, 1F)
                    break
                }
            }

        }
    }

    private fun canSpawnEnder(world: World, pos: BlockPos): Boolean {
        return world.isAir(pos) && world.isAir(pos.add(0, 1, 0)) && world.isAir(pos.add(0, 2, 0))
    }
}
