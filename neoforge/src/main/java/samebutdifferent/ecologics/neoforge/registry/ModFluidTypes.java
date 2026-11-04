package samebutdifferent.ecologics.neoforge.registry;

import java.util.function.Supplier;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.neoforge.fluid.MapleSapFluidType;
import samebutdifferent.ecologics.registry.ModItems;

public class ModFluidTypes 
{
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.FLUID_TYPES, Ecologics.MOD_ID);
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(BuiltInRegistries.FLUID, Ecologics.MOD_ID);
    
    public static final Supplier<FluidType> MAPLE_SAP_TYPE = FLUID_TYPES.register("maple_sap", () -> new MapleSapFluidType(false, FluidType.Properties.create()));
    public static final Supplier<FluidType> MAPLE_SYRUP_TYPE = FLUID_TYPES.register("maple_syrup", () -> new MapleSapFluidType(true, FluidType.Properties.create()));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> MAPLE_SAP = FLUIDS.register("maple_sap", () -> new BaseFlowingFluid.Source(makeProperties(false)));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> MAPLE_SYRUP = FLUIDS.register("maple_syrup", () -> new BaseFlowingFluid.Source(makeProperties(true)));
    
    public static final Supplier<FluidStack> MAPLE_SAP_FLUID_STACK = () -> new FluidStack(MAPLE_SAP.get(), 1000);
    public static final Supplier<FluidStack> MAPLE_SYRUP_FLUID_STACK = () -> new FluidStack(MAPLE_SYRUP.get(), 250);
    
    private static BaseFlowingFluid.Properties makeProperties(boolean isSyrup) {
        return new BaseFlowingFluid.Properties(isSyrup ? MAPLE_SYRUP_TYPE : MAPLE_SAP_TYPE, () -> isSyrup ?  MAPLE_SYRUP.get() : MAPLE_SAP.get(), () -> isSyrup ?  MAPLE_SYRUP.get() : MAPLE_SAP.get()).bucket(() -> isSyrup ? ModItems.MAPLE_SYRUP_BOTTLE : ModItems.MAPLE_SAP_BUCKET);
    }
    
    public static void register(IEventBus modEventBus) {
        FLUIDS.register(modEventBus);
    }
}
