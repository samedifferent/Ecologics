package samebutdifferent.ecologics.neoforge.registry;

import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.world.entity.animal.camel.Camel;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentSyncHandler;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import samebutdifferent.ecologics.Ecologics;

public class ModDataAttachments 
{
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, Ecologics.MOD_ID);

    // Registers a synced boolean attachment with a default value of 'false'
    public static final Supplier<AttachmentType<Boolean>> CAMEL_HAS_CHEST = ATTACHMENTS.register("camel_has_chest", 
    	() -> AttachmentType.builder(() -> false).serialize(Codec.BOOL.fieldOf("ChestedCamel")).sync(new AttachmentSyncHandler<Boolean>() {
            @Override
            public Boolean read(IAttachmentHolder holder, RegistryFriendlyByteBuf buf, @Nullable Boolean previousValue) {
                boolean newValue = ByteBufCodecs.BOOL.decode(buf);
                if (holder instanceof Camel camel && camel.level().isClientSide()) { camel.refreshDimensions(); }
                return newValue;
            }
			@Override
			public void write(RegistryFriendlyByteBuf buf, Boolean attachment, boolean initialSync) { ByteBufCodecs.BOOL.encode(buf, attachment); }
        }).build()
	);
    
    public static void register(IEventBus modEventBus) {
    	ATTACHMENTS.register(modEventBus);
    }
}
