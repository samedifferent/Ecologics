package samebutdifferent.ecologics.util;

import com.google.gson.JsonElement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.Optional;

// Thanks TelepathicGrunt for letting me use this
public class CodecUtils {
    private static final CodecCache<PlacedFeature> placedFeatureCodecCache = CodecCache.of(PlacedFeature.DIRECT_CODEC);

    public static boolean serializeAndCompareFeature(PlacedFeature placedFeature1, PlacedFeature placedFeature2) {
        if (placedFeature1 == placedFeature2) return true;

        Optional<JsonElement> optionalJsonElement1 = encode(placedFeature1);
        if (optionalJsonElement1.isEmpty()) return false;

        Optional<JsonElement> optionalJsonElement2 = encode(placedFeature2);
        if (optionalJsonElement2.isEmpty()) return false;

        JsonElement featureJson1 = optionalJsonElement1.get();
        JsonElement featureJson2 = optionalJsonElement2.get();
        return featureJson1.equals(featureJson2);
    }

    public static String getCacheStats() {
        return placedFeatureCodecCache.getStats();
    }

    public static void clearCache() {
        placedFeatureCodecCache.clear();
    }

    public static Optional<JsonElement> encode(PlacedFeature feature) {
        return placedFeatureCodecCache.get(feature);
    }
}
