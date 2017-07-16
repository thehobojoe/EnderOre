package com.hobojoe.enderore

import net.minecraftforge.common.config.Configuration

/**
 * Created by Joseph on 11/22/2016.
 */
object Config {
    private val CATEGORY_GENERAL = "general"

    var spawnsEnderman = true
    var generatesOre = true
    var dropsPearls = false
    var oresPerCluster = 4
    var clusterAmount = 12
    var minHeight = 5
    var maxHeight = 20


    fun readConfig() {
        val cfg = EnderOre.config
        try {
            cfg.load()
            initGeneralConfig(cfg)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (cfg.hasChanged()) {
                cfg.save()
            }
        }
    }

    private fun initGeneralConfig(cfg: Configuration) {
        spawnsEnderman = cfg.getBoolean("spawnsEndermen", CATEGORY_GENERAL, spawnsEnderman, "Set to false to disable endermen spawning when mining ender ore")
        generatesOre =   cfg.getBoolean("generatesOre",   CATEGORY_GENERAL, generatesOre, "Set to false to disable ore generation")
        dropsPearls =    cfg.getBoolean("dropsPearls",    CATEGORY_GENERAL, dropsPearls, "Set to true to make ender ore drop pearls instead of dust")
        oresPerCluster = cfg.getInt("oresPerCluster",     CATEGORY_GENERAL, oresPerCluster, 1, 30, "Number of ores per cluster")
        clusterAmount =  cfg.getInt("clusterAmount",      CATEGORY_GENERAL, clusterAmount, 1, 30, "Number of clusters per chunk")
        minHeight =      cfg.getInt("minHeight",          CATEGORY_GENERAL, minHeight, 1, 64, "Minimum height for world generation")
        maxHeight =      cfg.getInt("maxHeight",          CATEGORY_GENERAL, maxHeight, 8, 256, "Maximum height for world generation")
    }
}
