package dev.worldgen.patches.mixin;

import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.SnowAndFreezeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

// MCDev throws a hissy fit because of the stub code
@SuppressWarnings("all")
@Mixin(SnowAndFreezeFeature.class)
public class SnowAndFreezeFeatureMixin {
    @ModifyArg(
        method = "place",
        at = @At(
            value="INVOKE",
            target = "Lnet/minecraft/world/level/WorldGenLevel;getHeight(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I"
        )
    )
    private Heightmap.Types snowUnderTrees(Heightmap.Types heightmap) {
        return Heightmap.Types.MOTION_BLOCKING_NO_LEAVES;
    }
}
