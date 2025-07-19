package dev.perxenic.groovyengine.gui.editor.component;

import imgui.ImGui;
import imgui.flag.ImGuiCol;
import java.util.ArrayList;
import java.util.List;

public class ConsoleOutput {
    private final List<LogEntry> logs = new ArrayList<>();
    private boolean autoScroll = true;

    public void render() {
        ImGui.beginChild("Console", 0, 150, true);

        for (LogEntry log : logs) {
            switch (log.level) {
                case ERROR:
                    ImGui.pushStyleColor(ImGuiCol.Text, 1.0f, 0.2f, 0.2f, 1.0f);
                    break;
                case WARN:
                    ImGui.pushStyleColor(ImGuiCol.Text, 1.0f, 0.8f, 0.2f, 1.0f);
                    break;
                case INFO:
                    ImGui.pushStyleColor(ImGuiCol.Text, 0.8f, 0.8f, 0.8f, 1.0f);
                    break;
            }

            ImGui.textUnformatted(log.message);
            ImGui.popStyleColor();
        }

        if (autoScroll && ImGui.getScrollY() >= ImGui.getScrollMaxY()) {
            ImGui.setScrollHereY(1.0f);
        }

        ImGui.endChild();
    }

    public void log(String message) {
        logs.add(new LogEntry(LogLevel.INFO, message));
    }

    public void warn(String message) {
        logs.add(new LogEntry(LogLevel.WARN, message));
    }

    public void error(String message) {
        logs.add(new LogEntry(LogLevel.ERROR, message));
    }

    public void clear() {
        logs.clear();
    }

    private enum LogLevel {
        INFO, WARN, ERROR
    }

    private static class LogEntry {
        final LogLevel level;
        final String message;
        final long timestamp;

        LogEntry(LogLevel level, String message) {
            this.level = level;
            this.message = message;
            this.timestamp = System.currentTimeMillis();
        }
    }
}
