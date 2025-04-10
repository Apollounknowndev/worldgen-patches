package dev.worldgen.patches.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Set;

import static dev.worldgen.patches.WorldgenPatches.SNOW_LAYER;

// MCDev throws a hissy fit because of the stub code
@SuppressWarnings("all")
@Mixin(TreeFeature.class)
public class TreeFeatureMixin {

    @Inject(
        method = "place",
        at = @At("RETURN")
    )
    private void postProcessLeaves(FeaturePlaceContext<TreeConfiguration> context, CallbackInfoReturnable<Boolean> cir, @Local(ordinal = 2) Set<BlockPos> leaves) {
        WorldGenLevel level = context.level();

        for (BlockPos leafPos : leaves) {
            // TODO: Fix leaf decay handling
            //BlockState leaf = level.getBlockState(leafPos);
            //if (leaf.getBlock() instanceof LeavesBlock && leaf.getValue(BlockStateProperties.DISTANCE) == 7) {
            //    level.setBlock(leafPos, Blocks.AIR.defaultBlockState(), 19);
            //}

            BlockPos snowPos = leafPos.above();
            if (level.isEmptyBlock(snowPos) && SNOW_LAYER.canSurvive(level, snowPos) && level.getBiome(snowPos).value().shouldSnow(level, snowPos)) {
                level.setBlock(snowPos, SNOW_LAYER, 19);
            }
        }
    }
}
