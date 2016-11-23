package com.hobojoe.enderore;

import net.minecraftforge.common.config.Configuration;

/**
 * Created by Joseph on 11/22/2016.
 */
public class Config {
    private static final String CATEGORY_GENERAL = "general";

    public static boolean spawnsEnderman = true;
    public static boolean generatesOre = true;
    public static int oresPerCluster = 4;
    public static int clusterAmount = 15;
    public static int minHeight = 5;
    public static int maxHeight = 25;


    public static void readConfig() {
        Configuration cfg = EnderOre.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        spawnsEnderman = cfg.getBoolean("spawnsEndermen", CATEGORY_GENERAL, spawnsEnderman, "Set to false to disable endermen spawning when mining ender ore");
        generatesOre = cfg.getBoolean("generatesOre", CATEGORY_GENERAL, generatesOre, "Set to false to disable ore generation");
        oresPerCluster = cfg.getInt("oresPerCluster", Configuration.CATEGORY_GENERAL, oresPerCluster, 1, 30, "Number of ores per cluster");
        clusterAmount = cfg.getInt("clusterAmount", Configuration.CATEGORY_GENERAL, clusterAmount, 1, 30, "Number of clusters per chunk");
        minHeight = cfg.getInt("minHeight", Configuration.CATEGORY_GENERAL, minHeight, 1, 64, "Minimum height for world generation");
        maxHeight = cfg.getInt("maxHeight", Configuration.CATEGORY_GENERAL, maxHeight, 8, 256 ,"Maximum height for world generation");
    }
}
