package com.hobojoe.enderore.block

import com.hobojoe.enderore.EnderOre
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Created by Joseph on 11/18/2016.
 */
object ModBlocks {

    const val id = EnderOre.MODID

    @GameRegistry.ObjectHolder("$id:ore_ender")
    lateinit var oreEnder: BlockEnderOre

    @SideOnly(Side.CLIENT)
    fun initModels() {
        oreEnder.initModel()
    }
}