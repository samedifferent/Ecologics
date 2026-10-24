package samebutdifferent.ecologics.block.interaction;

import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import samebutdifferent.ecologics.block.MapleSapCauldronBlock;
import samebutdifferent.ecologics.registry.ModBlocks;
import samebutdifferent.ecologics.registry.ModItems;

import net.minecraft.core.cauldron.CauldronInteractions;

public class MapleSapCauldronInteraction 
{
	public static final CauldronInteraction.Dispatcher MAPLE_SAP_CAULDRON_INTERACTIONS = new CauldronInteraction.Dispatcher();

	@SuppressWarnings("unused")
	public static void registerCauldronInteractions() {
	    CauldronInteractions.EMPTY.put(ModItems.MAPLE_SAP_BUCKET, (state, level, pos, player, hand, stack) -> {
	        return CauldronInteractions.emptyBucket(level, pos, player, hand, stack, ModBlocks.MAPLE_SAP_CAULDRON.defaultBlockState().setValue(MapleSapCauldronBlock.LEVEL, 3), SoundEvents.BUCKET_EMPTY);
	    });
	    MAPLE_SAP_CAULDRON_INTERACTIONS.put(Items.BUCKET, (state, level, pos, player, hand, stack) -> {
	        if (state.getValue(MapleSapCauldronBlock.LEVEL) == 3) {
	            return CauldronInteractions.fillBucket(state, level, pos, player, hand, stack, new ItemStack(ModItems.MAPLE_SAP_BUCKET), _ -> true, SoundEvents.BUCKET_FILL);
	        }
	        return InteractionResult.PASS;
	    });
	    MAPLE_SAP_CAULDRON_INTERACTIONS.put(Items.GLASS_BOTTLE, (state, level, pos, player, hand, stack) -> {
	        if (state.is(ModBlocks.MAPLE_SAP_CAULDRON) && state.getValue(MapleSapCauldronBlock.LEVEL) == 1) {
	            if (!level.isClientSide()) {
	                // Turn item into syrup bottle
	                player.setItemInHand(hand, ItemUtils.createFilledResult(stack, player, new ItemStack(ModItems.MAPLE_SYRUP_BOTTLE)));
	                // Turn the cauldron completely empty since the syrup was taken
	                level.setBlock(pos, Blocks.CAULDRON.defaultBlockState(), 11);
	                level.playSound(null, pos, SoundEvents.BOTTLE_FILL, SoundSource.BLOCKS, 1.0F, 1.0F);
	            }
	            return InteractionResult.SUCCESS;
	        }
	        return InteractionResult.PASS;
	    });
	}
}
