package dev.perxenic.groovyengine.gui.editor.component;

import imgui.ImGui;
import java.util.function.BooleanSupplier;

public class EditorToolbar {
    public static void render(boolean canSave, boolean canRun,
                              Runnable onSave, Runnable onRun) {
        if (ImGui.beginMainMenuBar()) {
            if (ImGui.beginMenu("File")) {
                if (ImGui.menuItem("Save", "Ctrl+S", false, canSave)) {
                    onSave.run();
                }
                ImGui.separator();
                if (ImGui.menuItem("Exit", "Alt+F4")) {
                    ImGui.closeCurrentPopup();
                }
                ImGui.endMenu();
            }

            if (ImGui.beginMenu("Edit")) {
                if (ImGui.menuItem("Undo", "Ctrl+Z")) {
                    // Handle undo
                }
                if (ImGui.menuItem("Redo", "Ctrl+Y")) {
                    // Handle redo
                }
                ImGui.separator();
                if (ImGui.menuItem("Run Script", "F5", false, canRun)) {
                    onRun.run();
                }
                ImGui.endMenu();
            }

            ImGui.endMainMenuBar();
        }
    }
}