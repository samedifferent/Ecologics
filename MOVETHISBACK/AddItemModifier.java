package samebutdifferent.ecologics.loot;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddItemModifier extends LootModifier {
    private final Item item;

    protected AddItemModifier(LootCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(item));
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<AddItemModifier>
    {
        @Override
        public AddItemModifier read(Identifier location, JsonObject object, LootCondition[] ailootcondition) {
            Item item = ForgeRegistries.ITEMS.getValue(new Identifier((JsonHelper.getString(object, "item"))));
            return new AddItemModifier(ailootcondition, item);
        }

        @Override
        public JsonObject write(AddItemModifier instance) {
            return new JsonObject();
        }
    }
}
