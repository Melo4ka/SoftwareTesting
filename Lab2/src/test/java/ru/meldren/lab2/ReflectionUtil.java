package ru.meldren.lab2;

import org.junit.platform.commons.util.ReflectionUtils;

import java.lang.reflect.Field;

public class ReflectionUtil {

    public static Object createInstance(Class<?> clazz, Class<?>[] parameterTypes, Object... args) throws Throwable {
        try {
            return clazz.getConstructor(parameterTypes).newInstance(args);
        } catch (Exception ex) {
            throw ex.getCause();
        }
    }

    public static void setField(Object object, String fieldName, Object value) {
        try {
            Field targetField = ReflectionUtils.findFields(
                    object.getClass(),
                    field -> field.getName().equals(fieldName),
                    ReflectionUtils.HierarchyTraversalMode.TOP_DOWN
            ).get(0);
            targetField.setAccessible(true);
            targetField.set(object, value);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
