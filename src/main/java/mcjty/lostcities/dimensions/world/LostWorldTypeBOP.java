package mcjty.lostcities.dimensions.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class LostWorldTypeBOP extends WorldType {

    public LostWorldTypeBOP() {
        super("lostcities_bop");
    }

    private BiomeProvider biomeProvider = null;

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new LostCityChunkGenerator(world);
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        if (biomeProvider == null) {
            for (WorldType type : WorldType.WORLD_TYPES) {
                if ("BIOMESOP".equals(type.getName())) {
                    WorldType orig = world.getWorldInfo().getTerrainType();
                    world.getWorldInfo().setTerrainType(type);
                    biomeProvider = type.getBiomeProvider(world);
                    world.getWorldInfo().setTerrainType(orig);
                    break;
                }
            }
        }
        return biomeProvider;
    }
}
