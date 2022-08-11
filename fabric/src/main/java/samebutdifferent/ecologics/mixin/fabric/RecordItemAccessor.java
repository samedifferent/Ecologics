package samebutdifferent.ecologics.mixin.fabric;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecordItem.class)
public interface RecordItemAccessor {
    @Invoker("<init>")
    static RecordItem invokeConstructor(int analogOutput, SoundEvent soundEvent, Item.Properties properties, int lengthInSeconds) {
        throw new AssertionError();
    }
}
