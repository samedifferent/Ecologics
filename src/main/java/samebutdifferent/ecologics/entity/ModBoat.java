package samebutdifferent.ecologics.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import samebutdifferent.ecologics.Ecologics;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;

public class ModBoat extends BoatEntity {
    private static final TrackedData<String> WOOD_TYPE = DataTracker.registerData(ModBoat.class, TrackedDataHandlerRegistry.STRING);

    public ModBoat(EntityType<? extends BoatEntity> type, World level) {
        super(type, level);
        this.intersectionChecked = true;
    }

    public ModBoat(World level, double x, double y, double z) {
        this(ModEntityTypes.BOAT, level);
        this.setPosition(x, y, z);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(WOOD_TYPE, "coconut");
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound pCompound) {
        super.readCustomDataFromNbt(pCompound);
        pCompound.putString("Type", this.getWoodType());
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound pCompound) {
        super.writeCustomDataToNbt(pCompound);
        pCompound.putString("Type", this.getWoodType());
    }

    public String getWoodType() {
        return this.dataTracker.get(WOOD_TYPE);
    }

    public void setWoodType(String wood) {
        this.dataTracker.set(WOOD_TYPE, wood);
    }

    @Override
    public Item asItem() {
        switch(this.getWoodType()) {
            case "coconut":
                return ModItems.COCONUT_BOAT;
            default:
                return ModItems.COCONUT_BOAT;
        }
    }

    @Override
    public ItemStack getPickBlockStack() {
        return new ItemStack(Registry.ITEM.get(new Identifier(Ecologics.MOD_ID, this.getWoodType() + "_boat")));
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return super.createSpawnPacket();
        // TODO: return NetworkHooks.getEntitySpawningPacket(this);
    }
}
