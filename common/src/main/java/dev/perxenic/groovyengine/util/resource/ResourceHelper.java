package dev.perxenic.groovyengine.util.resource;

import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class ResourceHelper {
    private static final Logger LOGGER = Logger.getLogger(ResourceHelper.class.getName());

    public static Optional<String> readResourceAsString(
            ResourceManager manager, Identifier location) {
        try {
            Optional<Resource> resource = manager.getResource(location);
            if (resource.isPresent()) {
                try (InputStream stream = resource.get().getInputStream()) {
                    return Optional.of(new String(stream.readAllBytes(),
                            StandardCharsets.UTF_8));
                }
            }
        } catch (IOException e) {
            LOGGER.warning("Failed to read resource " + location + ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public static List<Resource> getResources(
            ResourceManager manager, String path, String extension) {
        return manager.findResources(path, id -> id.getPath().endsWith(extension))
                .values()
                .stream()
                .toList();
    }

    public static Optional<InputStream> getResourceStream(
            ResourceManager manager, Identifier location) {
        try {
            Optional<Resource> resource = manager.getResource(location);
            if (resource.isPresent()) {
                return Optional.of(resource.get().getInputStream());
            }
        } catch (IOException e) {
            LOGGER.warning("Failed to get resource stream " + location +
                    ": " + e.getMessage());
        }
        return Optional.empty();
    }

    public static boolean resourceExists(
            ResourceManager manager, Identifier location) {
        return manager.getResource(location).isPresent();
    }
}
