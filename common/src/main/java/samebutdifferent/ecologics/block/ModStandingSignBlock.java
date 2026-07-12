package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.WoodType;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.block.entity.ModSignBlockEntity;

public class ModStandingSignBlock extends StandingSignBlock 
{
    public ModStandingSignBlock(WoodType type, Properties properties) {
        super(type, properties);
    }
    
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ModSignBlockEntity(pos, state);
    }
    
    public void fixLootTable(String blockName) {
    	ResourceLocation resourceLocation = ResourceLocation.fromNamespaceAndPath(Ecologics.MOD_ID, blockName);
    	this.drops = ResourceKey.create(Registries.LOOT_TABLE, resourceLocation.withPrefix("blocks/"));
    }
}
