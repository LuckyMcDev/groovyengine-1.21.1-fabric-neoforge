package dev.perxenic.groovyengine.gui.editor.window;

import imgui.ImGui;
import dev.perxenic.groovyengine.gui.editor.component.*;
import imgui.type.ImBoolean;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class EditorWindow {
    private final ScriptList scriptList;
    private final CodeEditor codeEditor;
    private final ConsoleOutput console;
    private boolean isOpen = true;

    public EditorWindow(List<Path> scripts) {
        this.scriptList = new ScriptList(scripts, this::onScriptSelected);
        this.codeEditor = new CodeEditor(this::onCodeChanged);
        this.console = new ConsoleOutput();
    }

    public void render() {
        if (!isOpen) return;

        ImGui.begin("Script Editor", new ImBoolean(isOpen));

        EditorToolbar.render(
                codeEditor.isDirty(),
                scriptList.getSelectedScript() != null,
                this::saveCurrentScript,
                this::runCurrentScript
        );

        // Left panel - Script list
        ImGui.beginChild("left_panel", 200, 0, true);
        scriptList.render();
        ImGui.endChild();

        ImGui.sameLine();

        // Right panel - Editor and Console
        ImGui.beginChild("right_panel", 0, 0, false);

        // Editor area
        codeEditor.render();

        // Console area
        console.render();

        ImGui.endChild();

        ImGui.end();
    }

    private void onScriptSelected(Path script) {
        try {
            String content = Files.readString(script);
            codeEditor.setContent(content);
            console.log("Loaded script: " + script.getFileName());
        } catch (Exception e) {
            console.error("Failed to load script: " + e.getMessage());
        }
    }

    private void onCodeChanged(String newContent) {
        // Handle code changes
    }

    private void saveCurrentScript() {
        Path selected = scriptList.getSelectedScript();
        if (selected == null) return;

        try {
            Files.writeString(selected, codeEditor.getContent());
            codeEditor.clearDirty();
            console.log("Saved script: " + selected.getFileName());
        } catch (Exception e) {
            console.error("Failed to save script: " + e.getMessage());
        }
    }

    private void runCurrentScript() {
        Path selected = scriptList.getSelectedScript();
        if (selected == null) return;

        try {
            // Execute script through ScriptEngine
            console.log("Running script: " + selected.getFileName());
        } catch (Exception e) {
            console.error("Script execution failed: " + e.getMessage());
        }
    }
}
