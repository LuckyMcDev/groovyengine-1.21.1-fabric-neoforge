package dev.perxenic.groovyengine.gui.editor.window;

import imgui.ImGui;
import imgui.flag.ImGuiWindowFlags;
import imgui.type.ImBoolean;
import imgui.type.ImString;

public class SettingsWindow {
    private boolean isOpen = false;
    private final ImString scriptPath;
    private final ImBoolean autoReload;
    private final ImBoolean showLineNumbers;

    public SettingsWindow() {
        this.scriptPath = new ImString(256);
        this.autoReload = new ImBoolean(true);
        this.showLineNumbers = new ImBoolean(true);
    }

    public void render() {
        if (!isOpen) return;

        ImGui.begin("Settings", new ImBoolean(isOpen),
                ImGuiWindowFlags.AlwaysAutoResize);

        ImGui.text("Script Directory");
        if (ImGui.inputText("##scriptpath", scriptPath)) {
            // Handle path change
        }

        ImGui.separator();

        if (ImGui.checkbox("Auto Reload Scripts", autoReload)) {
            // Handle auto reload setting
        }

        if (ImGui.checkbox("Show Line Numbers", showLineNumbers)) {
            // Handle line numbers setting
        }

        ImGui.separator();

        if (ImGui.button("Save Settings")) {
            saveSettings();
        }

        ImGui.sameLine();

        if (ImGui.button("Reset to Defaults")) {
            resetSettings();
        }

        ImGui.end();
    }

    public void show() {
        isOpen = true;
    }

    private void saveSettings() {
        // Save settings to config file
    }

    private void resetSettings() {
        scriptPath.set(getDefaultScriptPath());
        autoReload.set(true);
        showLineNumbers.set(true);
    }

    private String getDefaultScriptPath() {
        return System.getProperty("user.home") + "/.minecraft/GroovyEngine/scripts";
    }
}
