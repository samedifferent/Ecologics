package samebutdifferent.ecologics.mixin;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.RecordItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecordItem.class)
public class RecordItemAccessor {
    @Invoker("<init>")
    public static RecordItem invokeConstructor(int analogOutput, SoundEvent soundEvent, Item.Properties properties) {
        throw new AssertionError();
    }
}
