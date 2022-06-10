package samebutdifferent.ecologics;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = Ecologics.MOD_ID)
public class ModConfiguration implements ConfigData {
    // Beach
    public double COCONUT_CRAB_SPAWN_CHANCE = 0.2D;
    public boolean GENERATE_COCONUT_TREES = true;
    public boolean GENERATE_SEASHELLS = true;

    // Desert
    public boolean SPAWN_CAMELS = true;
    public boolean GENERATE_PRICKLY_PEARS = true;
    public double PRICKLY_PEAR_GROWTH_CHANCE = 1.0;
    public boolean GENERATE_DESERT_RUINS = true;

    // Snowy
    public boolean SPAWN_PENGUINS = true;
    public boolean GENERATE_THIN_ICE_PATCHES = true;

    // Plains
    public boolean SPAWN_SQUIRRELS = true;
    public boolean GENERATE_WALNUT_TREES = true;

    // Lush caves
    public boolean REPLACE_AZALEA_TREE = true;
    public boolean GENERATE_SURFACE_MOSS = true;
}