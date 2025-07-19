package dev.perxenic.groovyengine.util.mapping;

import java.util.Map;
import java.util.HashMap;

public class MappingSet {
    private final Map<String, String> classMap;
    private final Map<String, String> reverseClassMap;
    private final Map<String, Map<String, String>> methodMap;
    private final Map<String, Map<String, String>> reverseMethodMap;
    private final Map<String, Map<String, String>> fieldMap;
    private final Map<String, Map<String, String>> reverseFieldMap;

    public MappingSet() {
        this.classMap = new HashMap<>();
        this.reverseClassMap = new HashMap<>();
        this.methodMap = new HashMap<>();
        this.reverseMethodMap = new HashMap<>();
        this.fieldMap = new HashMap<>();
        this.reverseFieldMap = new HashMap<>();
    }

    public void addClass(String original, String mapped) {
        classMap.put(original, mapped);
        reverseClassMap.put(mapped, original);
    }

    public void addMethod(String className, String original, String mapped) {
        methodMap.computeIfAbsent(className, k -> new HashMap<>())
                .put(original, mapped);
        reverseMethodMap.computeIfAbsent(className, k -> new HashMap<>())
                .put(mapped, original);
    }

    public void addField(String className, String original, String mapped) {
        fieldMap.computeIfAbsent(className, k -> new HashMap<>())
                .put(original, mapped);
        reverseFieldMap.computeIfAbsent(className, k -> new HashMap<>())
                .put(mapped, original);
    }

    public String mapClass(String className) {
        return classMap.getOrDefault(className, className);
    }

    public String unmapClass(String className) {
        return reverseClassMap.getOrDefault(className, className);
    }

    public String mapMethod(String className, String methodName) {
        Map<String, String> methods = methodMap.get(className);
        return methods != null ? methods.getOrDefault(methodName, methodName) : methodName;
    }

    public String unmapMethod(String className, String methodName, String descriptor) {
        Map<String, String> methods = reverseMethodMap.get(className);
        return methods != null ? methods.getOrDefault(methodName, methodName) : methodName;
    }

    public String mapField(String className, String fieldName) {
        Map<String, String> fields = fieldMap.get(className);
        return fields != null ? fields.getOrDefault(fieldName, fieldName) : fieldName;
    }

    public String unmapField(String className, String fieldName) {
        Map<String, String> fields = reverseFieldMap.get(className);
        return fields != null ? fields.getOrDefault(fieldName, fieldName) : fieldName;
    }
}
