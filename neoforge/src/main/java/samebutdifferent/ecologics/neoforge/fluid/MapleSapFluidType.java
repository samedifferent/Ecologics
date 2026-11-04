package samebutdifferent.ecologics.neoforge.fluid;

import java.util.function.Consumer;

import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;

public class MapleSapFluidType extends FluidType 
{
    private final boolean isSyrup;
    
    public MapleSapFluidType(boolean isSyrup, Properties properties) {
        super(properties);
        this.isSyrup = isSyrup;
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        consumer.accept(new IClientFluidTypeExtensions() {
            private static final ResourceLocation WATER_STILL = ResourceLocation.withDefaultNamespace("block/water_still");
            private static final ResourceLocation WATER_FLOW = ResourceLocation.withDefaultNamespace("block/water_flow");

            @Override
            public ResourceLocation getStillTexture() {
                return WATER_STILL;
            }

            @Override
            public ResourceLocation getFlowingTexture() {
                return WATER_FLOW;
            }

            @Override
            public int getTintColor() {
                return isSyrup ? 0xE0FF7835 : 0xC0FFD6AD; 
            }
        });
    }
}
