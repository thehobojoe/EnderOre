package com.hobojoe.enderore.config

import me.sargunvohra.mcmods.autoconfig1u.ConfigData
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config

@Config(name = "enderore")
class EnderOreConfig: ConfigData {
    var spawnsEnderman: Boolean = true

    var generatesOre = true;
    var clusterAmount = 6
    var oresPerCluster = 4
    var minHeight = 5
    var maxHeight = 20
}
