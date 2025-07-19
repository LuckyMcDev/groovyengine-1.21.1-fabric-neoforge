package dev.perxenic.groovyengine.gui.editor.component;

import imgui.ImGui;
import imgui.flag.ImGuiInputTextFlags;
import imgui.type.ImString;
import java.util.function.Consumer;

public class CodeEditor {
    private final ImString content;
    private boolean isDirty = false;
    private final Consumer<String> onContentChanged;

    public CodeEditor(Consumer<String> onContentChanged) {
        this.content = new ImString(65536);
        this.onContentChanged = onContentChanged;
    }

    public void render() {
        ImGui.beginChild("CodeEditor", 0, 0, true);

        if (ImGui.inputTextMultiline("##code", content,
                ImGui.getContentRegionAvailX(),
                ImGui.getContentRegionAvailY(),
                ImGuiInputTextFlags.AllowTabInput)) {
            isDirty = true;
            onContentChanged.accept(content.get());
        }

        ImGui.endChild();
    }

    public void setContent(String newContent) {
        content.set(newContent);
        isDirty = false;
    }

    public String getContent() {
        return content.get();
    }

    public boolean isDirty() {
        return isDirty;
    }

    public void clearDirty() {
        isDirty = false;
    }
}