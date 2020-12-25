package com.hobojoe.enderore.config

import me.sargunvohra.mcmods.autoconfig1u.ConfigData
import me.sargunvohra.mcmods.autoconfig1u.annotation.Config

@Config(name = "enderore")
class EnderOreConfig: ConfigData {
    var spawnsEnderman: Boolean = true
}
