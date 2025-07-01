package samebutdifferent.ecologics.mixin.fabric;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxPlayable;

@Mixin(JukeboxPlayable.class)
public interface RecordItemAccessor {
    /*@Invoker("<init>")
    static JukeboxPlayable invokeConstructor(int analogOutput, SoundEvent soundEvent, Item.Properties properties, int lengthInSeconds) {
        throw new AssertionError();
    }*/
}
