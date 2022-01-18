package samebutdifferent.ecologics.data;

import net.minecraft.data.DataGenerator;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.registries.RegistryObject;
import org.apache.commons.lang3.text.WordUtils;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;
import samebutdifferent.ecologics.registry.ModSoundEvents;

import java.util.function.Supplier;

public class LangGenerator extends LanguageProvider {
    public LangGenerator(DataGenerator gen, String locale) {
        super(gen, Ecologics.MOD_ID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + Ecologics.MOD_ID, "Ecologics");
        for (RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {
            addItem(item, formatId(item));
        }
        for (RegistryObject<EntityType<?>> entity : ModEntityTypes.ENTITY_TYPES.getEntries()) {
            addEntityType(entity, formatId(entity));
        }
        add("death.attack.coconut", "%1$s was bonked by a falling coconut");
        add("death.attack.coconut.player", "%1$s was bonked by a falling coconut whilst fighting %2$s");
        addSoundEvent(ModSoundEvents.COCONUT_SMASH, "Coconut smashes");
        addEntitySoundEvents(ModEntityTypes.COCONUT_CRAB, "Coconut Crab", "hisses");
        addEntitySoundEvents(ModEntityTypes.SQUIRREL, "Squirrel", "sniffs");
    }

    private String formatId(RegistryObject object) {
        return WordUtils.capitalize(object.getId().getPath().replace("_", " "));
    }

    public void addEntitySoundEvents(Supplier<? extends EntityType<?>> key, String name, String ambientSoundWord) {
        add(Ecologics.MOD_ID + ".subtitles." + key.get().getDescriptionId() + ".ambient", name + " " + ambientSoundWord);
        add(Ecologics.MOD_ID + ".subtitles." + key.get().getDescriptionId() + ".death", name + " dies");
        add(Ecologics.MOD_ID + ".subtitles." + key.get().getDescriptionId() + ".hurt", name + " hurts");
    }

    public void addSoundEvent(Supplier<? extends SoundEvent> key, String subtitle) {
        add(Ecologics.MOD_ID + ".subtitles." + key.get().getLocation().getPath(), subtitle);
    }
}
