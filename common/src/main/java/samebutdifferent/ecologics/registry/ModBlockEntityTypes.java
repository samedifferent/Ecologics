package samebutdifferent.ecologics.registry;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.HangingSignBlockEntity;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import samebutdifferent.ecologics.block.entity.ModHangingSignBlockEntity;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;
import samebutdifferent.ecologics.block.entity.PotBlockEntity;
import samebutdifferent.ecologics.platform.CommonPlatformHelper;

import java.util.function.Supplier;

public class ModBlockEntityTypes {
    public static void init() {}

    public static final Supplier<BlockEntityType<SignBlockEntity>> SIGN = CommonPlatformHelper.registerBlockEntityType("sign", () -> CommonPlatformHelper.createBlockEntityType(ModSignBlockEntity::new,
            ModBlocks.COCONUT_SIGN.get(), ModBlocks.COCONUT_WALL_SIGN.get(),
            ModBlocks.WALNUT_SIGN.get(), ModBlocks.WALNUT_WALL_SIGN.get()
    ));
    public static final Supplier<BlockEntityType<HangingSignBlockEntity>> HANGING_SIGN = CommonPlatformHelper.registerBlockEntityType("hanging_sign", () -> CommonPlatformHelper.createBlockEntityType(ModHangingSignBlockEntity::new,
            ModBlocks.COCONUT_HANGING_SIGN.get(), ModBlocks.COCONUT_WALL_HANGING_SIGN.get(),
            ModBlocks.WALNUT_HANGING_SIGN.get(), ModBlocks.WALNUT_WALL_HANGING_SIGN.get()
    ));
    public static final Supplier<BlockEntityType<PotBlockEntity>> POT = CommonPlatformHelper.registerBlockEntityType("pot", () -> CommonPlatformHelper.createBlockEntityType(PotBlockEntity::new, ModBlocks.POT.get()));
}
