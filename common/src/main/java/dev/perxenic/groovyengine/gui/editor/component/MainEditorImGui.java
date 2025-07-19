package dev.perxenic.groovyengine.gui.editor.component;

import dev.perxenic.groovyengine.gui.editor.window.ConsoleWindow;
import dev.perxenic.groovyengine.gui.editor.window.DocsWindow;
import dev.perxenic.groovyengine.gui.editor.window.EditorWindow;
import dev.perxenic.groovyengine.gui.imgui.ImGuiImpl;
import dev.perxenic.groovyengine.scripting.gui.GuiRegistry;
import imgui.ImGui;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class MainEditorImGui {

    public static boolean showConsole = true;
    public static boolean showDocs = false;
    public static boolean showEditorWindow = false;

    public static void render() {
        ImGuiImpl.draw(io -> {
            ImGui.pushFont(ImGuiImpl.getDefaultFont());

            renderMenuBar();

            GuiRegistry.renderAll();

            if (showConsole) ConsoleWindow.render();
            if (showDocs) DocsWindow.render();
            if (showEditorWindow) EditorWindow.draw();

            ImGui.popFont();
        });
    }

    private static void renderMenuBar() {
        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("Windows")) {
                if (ImGui.menuItem("Console", "", showConsole)) showConsole = !showConsole;
                ImGui.separator();
                if (ImGui.menuItem("Script Editor", "", showEditorWindow)) showEditorWindow = !showEditorWindow;
                ImGui.separator();
                if (ImGui.menuItem("Documentation", "", showDocs)) showDocs = !showDocs;
                ImGui.endMenu();
            }

            if (ImGui.beginMenu("View")) {
                if (ImGui.menuItem("Reset Layout")) {
                    showConsole = true;
                    showEditorWindow = true;
                    showDocs = true;
                }
                ImGui.endMenu();
            }

            ImGui.endMainMenuBar();
        }
    }

}
