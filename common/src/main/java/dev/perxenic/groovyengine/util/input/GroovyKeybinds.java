package dev.perxenic.groovyengine.util.input;

import dev.architectury.registry.client.keymappings.KeyMappingRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

@Environment(EnvType.CLIENT)
public class GroovyKeybinds {

    public static KeyBinding openMainEditorKey;

    public static void init() {
        openMainEditorKey = register("open_main_editor", InputUtil.GLFW_KEY_F6);
    }

    private static KeyBinding register(String name, int key) {
        KeyBinding keyBinding = new KeyBinding(
                "key.groovyengine." + name,
                InputUtil.Type.KEYSYM,
                key,
                "key.categories.groovyengine"
        );
        KeyMappingRegistry.register(keyBinding);
        return keyBinding;
    }
}