package dev.perxenic.groovyengine.gui.editor.component;

import imgui.ImGui;
import imgui.flag.ImGuiTreeNodeFlags;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Consumer;

public class ScriptList {
    private Path selectedScript = null;
    private final List<Path> scripts;
    private final Consumer<Path> onScriptSelected;

    public ScriptList(List<Path> scripts, Consumer<Path> onScriptSelected) {
        this.scripts = scripts;
        this.onScriptSelected = onScriptSelected;
    }

    public void render() {
        ImGui.beginChild("ScriptList", 200, 0, true);

        if (ImGui.treeNodeEx("Scripts", ImGuiTreeNodeFlags.DefaultOpen)) {
            for (Path script : scripts) {
                boolean isSelected = script.equals(selectedScript);
                if (ImGui.selectable(script.getFileName().toString(), isSelected)) {
                    selectedScript = script;
                    onScriptSelected.accept(script);
                }
            }
            ImGui.treePop();
        }

        ImGui.endChild();
    }

    public Path getSelectedScript() {
        return selectedScript;
    }
}