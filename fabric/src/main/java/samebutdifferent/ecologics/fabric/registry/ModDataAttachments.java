package samebutdifferent.ecologics.fabric.registry;

import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import samebutdifferent.ecologics.Ecologics;

public class ModDataAttachments 
{
	public static void init() {}
	
	public static final AttachmentType<Boolean> CAMEL_HAS_CHEST = AttachmentRegistry.create(Identifier.fromNamespaceAndPath(Ecologics.MOD_ID, "camel_has_chest"), builder -> builder.initializer(() -> false).syncWith(ByteBufCodecs.BOOL, AttachmentSyncPredicate.all()));
}
