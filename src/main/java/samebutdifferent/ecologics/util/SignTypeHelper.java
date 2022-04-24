package samebutdifferent.ecologics.util;



import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;


public interface SignTypeHelper
{
    Set<SignType> eco_getTypes();

    static SignType register(String name)
    {
        SignType type = createWoodType(name);

        ((SignTypeHelper) type).eco_getTypes().add(type);

        return type;
    }

    static SignType createWoodType(String name)
    {
        try {
            Constructor<SignType> constructor = SignType.class.getDeclaredConstructor(String.class);
            constructor.setAccessible(true);

            SignType type = constructor.newInstance(name);

            if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                TexturedRenderLayers.WOOD_TYPE_TEXTURES.put(type, new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, new Identifier("entity/signs/" + type.name)));
            }

            return type;
        }
        catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
