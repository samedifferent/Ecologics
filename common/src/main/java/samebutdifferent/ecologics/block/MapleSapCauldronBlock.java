package samebutdifferent.ecologics.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction.Dispatcher;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import samebutdifferent.ecologics.registry.ModTags;

public class MapleSapCauldronBlock extends AbstractCauldronBlock
{
	public static final IntegerProperty LEVEL = IntegerProperty.create("level", 1, 3);
	
	public MapleSapCauldronBlock(Properties properties, Dispatcher interactions) {
		super(properties, interactions);
		this.registerDefaultState(this.stateDefinition.any().setValue(LEVEL, 3));
	}
	
    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    	super.createBlockStateDefinition(builder);
        builder.add(LEVEL);
    }
	
    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean bool) {
    	return Items.CAULDRON.getDefaultInstance();
    }
    
	@Override
	public boolean isFull(BlockState state) {
		return state.getValue(LEVEL) == 3;
	}
	
	@Override
	public boolean isRandomlyTicking(BlockState state) {
		return state.getValue(LEVEL) > 1;
	}
	
	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, InsideBlockEffectApplier applier, boolean isPrecise) {
	   if (level instanceof ServerLevel && this.isOverHotBlock(level, pos)) {
	       if (entity instanceof LivingEntity && !entity.fireImmune()) {
	           entity.hurtServer((ServerLevel)level, level.damageSources().hotFloor(), 2.0F);
	       }
	   }
	   super.entityInside(state, level, pos, entity, applier, isPrecise);
	}
	
    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (this.isOverHotBlock(level, pos) && random.nextFloat() < 0.4F) { // A reasonable boil speed.
            int currentLevel = state.getValue(LEVEL);
            if (currentLevel > 1) {
                level.setBlock(pos, state.setValue(LEVEL, currentLevel - 1), 3); // Progressively boils down: 3 -> 2 -> 1
            }
        }
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // Only bubble if it is sitting over an active heat source block tag
        if (isOverHotBlock(level, pos)) {
            int currentLevel = state.getValue(LEVEL);
            
            // Calculate particle height based on the liquid level state
            // Level 3 is near full, level 1 is down near the bottom
            double liquidHeight = 0.3D + (currentLevel * 0.2D); 
            
            // Adjust the spawn count. As it thickens (Level 1), it can boil slower/thicker
            int bubbleCount = currentLevel == 1 ? 1 : 2;

            for (int i = 0; i < bubbleCount; i++) {
                if (random.nextFloat() < 0.15F) { // Control frequency so it doesn't overload the screen
                    // Randomize position within the cauldron inner basin walls
                    double x = pos.getX() + 0.2D + random.nextDouble() * 0.6D;
                    double y = pos.getY() + liquidHeight;
                    double z = pos.getZ() + 0.2D + random.nextDouble() * 0.6D;

                    // Spawn a vanilla bubble particle moving slowly upward
                    level.addParticle(ParticleTypes.BUBBLE, x, y, z, 0.0D, 0.03D, 0.0D);
                    
                    // Optional: Spawn a tiny splash or pop particle right above it 
                    if (random.nextFloat() < 0.3F) {
                        level.addParticle(ParticleTypes.SPLASH, x, y + 0.02D, z, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
            // Play a rare boiling/pop sound effect so it loops naturally without overlapping too loudly
            if (random.nextFloat() < 0.05F) {
                level.playLocalSound(pos.getX() + 0.5D, pos.getY() + liquidHeight, pos.getZ() + 0.5D, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundSource.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.5F + random.nextFloat() * 0.3F, false);
            }
        }
    }
	
	private boolean isOverHotBlock(Level level, BlockPos pos) {
		BlockState state = level.getBlockState(pos.below());
		if (state.is(ModTags.BlockTags.CONDITIONALLY_HOT_BLOCKS) && state.hasProperty(BlockStateProperties.LIT)) {
			return state.getValue(BlockStateProperties.LIT);
		}
		return level.getBlockState(pos.below()).is(ModTags.BlockTags.HOT_BLOCKS);
	}

}
