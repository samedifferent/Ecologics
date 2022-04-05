package samebutdifferent.ecologics.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.world.World;
import samebutdifferent.ecologics.registry.ModEntityTypes;
import samebutdifferent.ecologics.registry.ModItems;

public class ModBoat extends BoatEntity {
    private static final TrackedData<String> WOOD_TYPE = DataTracker.registerData(ModBoat.class, TrackedDataHandlerRegistry.STRING);

    public ModBoat(EntityType<? extends BoatEntity> type, World world) {
        super(type, world);
        this.intersectionChecked = true;
    }

    public ModBoat(World world, double x, double y, double z) {
        this(ModEntityTypes.BOAT, world);
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
    protected void readCustomDataFromNbt(NbtCompound nbtCompound) {
        super.readCustomDataFromNbt(nbtCompound);
        if (nbtCompound.contains("Type", 8)) {
            this.setWoodType(nbtCompound.getString("Type"));
        }
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbtCompound) {
        super.writeCustomDataToNbt(nbtCompound);
        nbtCompound.putString("Type", this.getWoodType());
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
            case "walnut":
                return ModItems.WALNUT_BOAT;
            case "azalea":
                return ModItems.AZALEA_BOAT;
            case "flowering_azalea":
                return ModItems.FLOWERING_AZALEA_BOAT;
            default:
                return ModItems.COCONUT_BOAT;
        }
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return super.createSpawnPacket();
    }
}
