package dev.worldgen.patches.mixin;

import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.minecraft.world.level.levelgen.Heightmap.Types.WORLD_SURFACE_WG;

@Mixin(SurfaceRules.Context.SteepMaterialCondition.class)
public class SteepMaterialConditionMixin extends SurfaceRules.LazyXZCondition {
    protected SteepMaterialConditionMixin(SurfaceRules.Context context) {
        super(context);
    }

    /**
     * @author Apollo
     * @reason Rewrite logic
     */
    @Overwrite
    public boolean compute() {
        int x = this.context.blockX & 15;
        int z = this.context.blockZ & 15;
        int north = Math.max(z - 1, 0);
        int south = Math.min(z + 1, 15);
        ChunkAccess chunk = this.context.chunk;
        int heightN = chunk.getHeight(WORLD_SURFACE_WG, x, north);
        int heightS = chunk.getHeight(WORLD_SURFACE_WG, x, south);
        if (Math.abs(heightS - heightN) >= 4) {
            return true;
        } else {
            int west = Math.max(x - 1, 0);
            int east = Math.min(x + 1, 15);
            int heightW = chunk.getHeight(WORLD_SURFACE_WG, west, z);
            int heightE = chunk.getHeight(WORLD_SURFACE_WG, east, z);
            return Math.abs(heightW - heightE) >= 4;
        }
    }
}
