package samebutdifferent.ecologics.platform;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class ConfigPlatformHelper {
    @ExpectPlatform
    public static double coconutCrabSpawnChance() {
        throw new AssertionError();
    }

}
