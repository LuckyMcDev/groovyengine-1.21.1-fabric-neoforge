package dev.perxenic.groovyengine.scripting.input;

import dev.architectury.event.events.common.TickEvent;
import groovy.lang.Closure;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import java.util.HashMap;
import java.util.Map;

@Environment(EnvType.CLIENT)
public class KeyInputHandler {

    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static final Map<String, Closure<?>> keyListeners = new HashMap<>();
    private static final Map<String, Boolean> lastKeyState = new HashMap<>();

    public static void init() {
        TickEvent.PLAYER_POST.register(client -> {
            if (client == null) return;

            for (String keyName : keyListeners.keySet()) {
                int keyCode = getKeyCodeFromName(keyName);
                if (keyCode == -1) continue;

                boolean isPressed = InputUtil.isKeyPressed(mc.getWindow().getHandle(), keyCode);
                boolean wasPressed = lastKeyState.getOrDefault(keyName, false);

                if (isPressed && !wasPressed) {
                    Closure<?> callback = keyListeners.get(keyName);
                    try {
                        callback.call();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                lastKeyState.put(keyName, isPressed);
            }
        });
    }

    public static void onPress(String keyName, Closure<?> callback) {
        keyListeners.put(keyName.toUpperCase(), callback);
        lastKeyState.put(keyName.toUpperCase(), false);
    }

    private static int getKeyCodeFromName(String keyName) {
        InputUtil.Key key = InputUtil.fromTranslationKey("key.keyboard." + keyName.toLowerCase());
        if (key == InputUtil.UNKNOWN_KEY) {
            return -1;
        }
        return key.getCode();
    }


}
