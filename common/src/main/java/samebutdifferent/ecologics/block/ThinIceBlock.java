package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.IceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModSoundEvents;

public class ThinIceBlock extends IceBlock {
    public static final IntegerProperty AGE = BlockStateProperties.AGE_3;
    
    public ThinIceBlock(BlockBehaviour.Properties properties) {
        super(properties.isValidSpawn((state, blockGetter, pos, entityType) -> entityType.equals(EntityType.POLAR_BEAR) || entityType.equals(ModEntityTypes.PENGUIN)));
        this.registerDefaultState(this.stateDefinition.any().setValue(AGE, 0));
    }

    @Override
    public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, double fallDistance) {
    	Holder<Enchantment> ffholder = level.registryAccess().lookupOrThrow(Registries.ENCHANTMENT).listElements().filter(ench -> ench.is(Enchantments.FEATHER_FALLING)).findFirst().get();
        if (fallDistance > 1 && entity instanceof LivingEntity && EnchantmentHelper.getEnchantmentLevel(ffholder, (LivingEntity)entity) == 0) {
            level.playSound(null, pos, ModSoundEvents.THIN_ICE_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
            replaceIfThinIce(pos, 3, level);
            replaceIfThinIce(pos.north(), 2, level);
            replaceIfThinIce(pos.east(), 2, level);
            replaceIfThinIce(pos.south(), 2, level);
            replaceIfThinIce(pos.west(), 2, level);
            replaceIfThinIce(pos.north().west(), 1, level);
            replaceIfThinIce(pos.north().east(), 1, level);
            replaceIfThinIce(pos.south().west(), 1, level);
            replaceIfThinIce(pos.south().east(), 1, level);
        }
    }

    private void replaceIfThinIce(BlockPos pos, int age, Level level) {
        BlockState state = level.getBlockState(pos);
        if (state.is(ModBlocks.THIN_ICE)) {
            level.setBlock(pos, ModBlocks.THIN_ICE.defaultBlockState().setValue(AGE, Math.min(state.getValue(AGE) + age, 3)), 2);
        }
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof Player && state.getValue(AGE) > 0) {
            this.crack(state, level, pos);
        }
    }
    
    @SuppressWarnings("deprecation")
	private boolean crack(BlockState state, Level level, BlockPos pos) {
        int age = state.getValue(AGE);
        if (age < 3) {
            level.setBlock(pos, state.setValue(AGE, age + 1), 2);
            level.playSound(null, pos, ModSoundEvents.THIN_ICE_CRACK, SoundSource.BLOCKS, 0.7F, 0.9F + level.random.nextFloat() * 0.2F);
            return false;
        } else {
        	if (level.environmentAttributes().getValue(EnvironmentAttributes.WATER_EVAPORATES, pos)) {
        		level.removeBlock(pos, false);
        		return true;
        	}
            BlockState blockstate = level.getBlockState(pos.below());
            if (blockstate.blocksMotion() || blockstate.liquid()) {
                level.setBlockAndUpdate(pos, IceBlock.meltsInto());
            }
            level.playSound(null, pos, SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 1.0F, 1.0F);
            return true;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(AGE);
    }
}
