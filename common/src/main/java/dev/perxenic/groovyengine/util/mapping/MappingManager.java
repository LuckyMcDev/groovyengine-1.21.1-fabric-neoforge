package dev.perxenic.groovyengine.util.mapping;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class MappingManager {
    private static final Logger LOGGER = Logger.getLogger(MappingManager.class.getName());

    private final Map<String, MappingSet> mappingSets;
    private final Map<String, String> classCache;
    private final Map<String, String> methodCache;
    private final Map<String, String> fieldCache;

    public MappingManager() {
        this.mappingSets = new ConcurrentHashMap<>();
        this.classCache = new ConcurrentHashMap<>();
        this.methodCache = new ConcurrentHashMap<>();
        this.fieldCache = new ConcurrentHashMap<>();
    }

    public void loadMappings(String namespace, MappingSet mappings) {
        mappingSets.put(namespace, mappings);
        LOGGER.info("Loaded mappings for namespace: " + namespace);
    }

    public String mapClass(String className, String fromNamespace, String toNamespace) {
        String cacheKey = fromNamespace + ":" + toNamespace + ":" + className;

        return classCache.computeIfAbsent(cacheKey, k -> {
            MappingSet fromSet = mappingSets.get(fromNamespace);
            MappingSet toSet = mappingSets.get(toNamespace);

            if (fromSet == null || toSet == null) {
                LOGGER.warning("Missing mapping set for " + fromNamespace + " or " + toNamespace);
                return className;
            }

            return toSet.mapClass(fromSet.unmapClass(className));
        });
    }

    public String mapMethod(String className, String methodName, String descriptor,
                            String fromNamespace, String toNamespace) {
        String cacheKey = fromNamespace + ":" + toNamespace + ":" +
                className + "." + methodName + descriptor;

        return methodCache.computeIfAbsent(cacheKey, k -> {
            MappingSet fromSet = mappingSets.get(fromNamespace);
            MappingSet toSet = mappingSets.get(toNamespace);

            if (fromSet == null || toSet == null) {
                return methodName;
            }

            return toSet.mapMethod(
                    fromSet.unmapClass(className),
                    fromSet.unmapMethod(className, methodName, descriptor)
            );
        });
    }

    public String mapField(String className, String fieldName,
                           String fromNamespace, String toNamespace) {
        String cacheKey = fromNamespace + ":" + toNamespace + ":" +
                className + "." + fieldName;

        return fieldCache.computeIfAbsent(cacheKey, k -> {
            MappingSet fromSet = mappingSets.get(fromNamespace);
            MappingSet toSet = mappingSets.get(toNamespace);

            if (fromSet == null || toSet == null) {
                return fieldName;
            }

            return toSet.mapField(
                    fromSet.unmapClass(className),
                    fromSet.unmapField(className, fieldName)
            );
        });
    }

    public void clearCache() {
        classCache.clear();
        methodCache.clear();
        fieldCache.clear();
    }
}
