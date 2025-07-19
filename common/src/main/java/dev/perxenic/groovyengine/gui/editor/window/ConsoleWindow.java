package dev.perxenic.groovyengine.gui.editor.window;

import dev.perxenic.groovyengine.gui.editor.component.ConsoleOutput;
import imgui.ImGui;
import imgui.type.ImBoolean;

public class ConsoleWindow {
    private final ConsoleOutput console;
    private boolean isOpen = true;

    public ConsoleWindow() {
        this.console = new ConsoleOutput();
    }

    public void render() {
        if (!isOpen) return;

        ImGui.begin("Console", new ImBoolean(isOpen));

        if (ImGui.button("Clear")) {
            console.clear();
        }

        ImGui.sameLine();

        if (ImGui.button("Test Log")) {
            console.log("Test info message");
            console.warn("Test warning message");
            console.error("Test error message");
        }

        console.render();

        ImGui.end();
    }

    public ConsoleOutput getConsole() {
        return console;
    }
}