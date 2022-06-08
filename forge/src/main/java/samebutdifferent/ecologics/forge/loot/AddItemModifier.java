package samebutdifferent.ecologics.forge.loot;

import com.google.gson.JsonObject;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddItemModifier extends LootModifier {
    private final Item item;

    protected AddItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> objectArrayList, LootContext arg) {
        objectArrayList.add(new ItemStack(item));
        return objectArrayList;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AddItemModifier>
    {
        @Override
        public AddItemModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
            Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation((GsonHelper.getAsString(object, "item"))));
            return new AddItemModifier(ailootcondition, item);
        }

        @Override
        public JsonObject write(AddItemModifier instance) {
            return new JsonObject();
        }
    }
}
