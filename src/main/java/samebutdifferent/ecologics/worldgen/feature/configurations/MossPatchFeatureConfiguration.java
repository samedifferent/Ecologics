package samebutdifferent.ecologics.worldgen.feature.configurations;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.util.math.Direction;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.FeatureConfig;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record MossPatchFeatureConfiguration(int tries, int xzSpread, int ySpread, boolean onFloor, boolean onCeiling, boolean onWalls, List<Block> canPlaceOn) implements FeatureConfig {
    public static final Codec<MossPatchFeatureConfiguration> CODEC = RecordCodecBuilder.create((instance) -> instance.group(Codecs.POSITIVE_INT.fieldOf("tries").orElse(128).forGetter(MossPatchFeatureConfiguration::tries), Codecs.NONNEGATIVE_INT.fieldOf("xz_spread").orElse(7).forGetter(MossPatchFeatureConfiguration::xzSpread), Codecs.NONNEGATIVE_INT.fieldOf("y_spread").orElse(3).forGetter(MossPatchFeatureConfiguration::ySpread), Codec.BOOL.fieldOf("can_place_on_floor").orElse(false).forGetter(MossPatchFeatureConfiguration::onFloor), Codec.BOOL.fieldOf("can_place_on_ceiling").orElse(false).forGetter(MossPatchFeatureConfiguration::onCeiling), Codec.BOOL.fieldOf("can_place_on_wall").orElse(false).forGetter(MossPatchFeatureConfiguration::onWalls), Registry.BLOCK.getCodec().listOf().fieldOf("can_be_placed_on").forGetter(MossPatchFeatureConfiguration::canPlaceOn)).apply(instance, MossPatchFeatureConfiguration::new));
    public static List<Direction> directions = Lists.newArrayList();

    public MossPatchFeatureConfiguration(int tries, int xzSpread, int ySpread, boolean onFloor, boolean onCeiling, boolean onWalls, List<Block> canPlaceOn) {
        this.tries = tries;
        this.xzSpread = xzSpread;
        this.ySpread = ySpread;
        this.onFloor = onFloor;
        this.onCeiling = onCeiling;
        this.onWalls = onWalls;
        this.canPlaceOn = canPlaceOn;
        List<Direction> list = Lists.newArrayList();
        if (onCeiling) {
            list.add(Direction.UP);
        }

        if (onFloor) {
            list.add(Direction.DOWN);
        }

        if (onWalls) {
            Direction.Type var10000 = Direction.Type.HORIZONTAL;
            Objects.requireNonNull(list);
            var10000.forEach(list::add);
        }

        directions = Collections.unmodifiableList(list);
    }

    public int tries() {
        return this.tries;
    }

    public int xzSpread() {
        return this.xzSpread;
    }

    public int ySpread() {
        return this.ySpread;
    }
}

