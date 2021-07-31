package springboot.demo.util;

import java.lang.reflect.Field;

public class Reflection {

    public static void setPrivate(Object obj, String keyName, Object value){
        Class<?> aClass = obj.getClass();
        Field declaredField = null;
        try {
            declaredField = aClass.getDeclaredField(keyName);
            declaredField.setAccessible(true);
            declaredField.set(obj,value);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
