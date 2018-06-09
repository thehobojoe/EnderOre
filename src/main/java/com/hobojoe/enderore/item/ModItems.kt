package com.hobojoe.enderore.item

import com.hobojoe.enderore.EnderOre
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.Side
import net.minecraftforge.fml.relauncher.SideOnly

/**
 * Created by Joseph on 11/18/2016.
 */
object ModItems {

    const val id = EnderOre.MODID

    @GameRegistry.ObjectHolder("$id:dust_ender")
    lateinit var dustEnder: ItemEnderDust

    @SideOnly(Side.CLIENT)
    fun initModels() {
        dustEnder.initModel()
        dustEnder.initOreDict()
    }
}