package com.hobojoe.enderore.config

import draylar.omegaconfig.api.Comment
import draylar.omegaconfig.api.Config

class EnderOreConfig: Config {
    override fun getName(): String {
        return "enderore"
    }

    @Comment(value = "If mining ender ore should spawn endermen")
    var spawnsEnderman: Boolean = true

    @Comment(value = "Chance that an enderman will spawn")
    var endermanChance = 20

    @Comment(value = "Should ore generate")
    var generatesOre = true;

    @Comment(value = "Ore clusters per chunk")
    var clusterAmount = 6

    @Comment(value = "Ores per cluster")
    var oresPerCluster = 4

    @Comment(value = "Minimum height to generate ore")
    var minHeight = 5

    @Comment(value = "Maximum height to generate ore")
    var maxHeight = 20
}
