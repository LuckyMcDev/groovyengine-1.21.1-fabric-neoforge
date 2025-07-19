package dev.perxenic.groovyengine;

import dev.architectury.event.events.client.ClientGuiEvent;
import dev.architectury.event.events.client.ClientTickEvent;
import dev.perxenic.groovyengine.editor.core.MainEditorImGui;
import dev.perxenic.groovyengine.editor.core.MainEditorScreen;
import dev.perxenic.groovyengine.input.GroovyKeybinds;
import dev.perxenic.groovyengine.packs.structure.DatapackGenerator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class GroovyEngineClient {

    public static void init() {
        GroovyKeybinds.init();
        DatapackGenerator.generate();

        ClientTickEvent.CLIENT_POST.register(client -> {
            if (GroovyKeybinds.openMainEditorKey.wasPressed()) {
                client.setScreen(new MainEditorScreen(Text.of("Main Editor")));
            }
        });

        ClientGuiEvent.RENDER_HUD.register((drawContext, tickCounter) -> {
            MainEditorImGui.render();
        });
    }
}