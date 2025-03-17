package dev.worldgen.patches;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldgenPatches {
    public static final String MOD_ID = "worldgen_patches";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final BlockState SNOW_LAYER = Blocks.SNOW.defaultBlockState();
}
