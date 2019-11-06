package me.june;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 06/11/2019
 * Time: 8:57 오후
 **/
public class ContainerService {

    // Method paramter로 넘기는 타입으로 리턴타입을 받는다.
    public static <T> T getObject(Class<T> classType){
        T instance = createInstance(classType);
        Arrays.stream(classType.getDeclaredFields()).forEach(f -> {
            if (f.getAnnotation(Inject.class) != null) {
                Class<?> type = f.getType();
                Object instance1 = createInstance(type);
                f.setAccessible(true);
                try {
                    f.set(instance, instance1);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        return instance;
    }

    // instance를 생성하는 메소드
    private static <T> T createInstance(Class<T> classType) {
        try {
            return classType.getConstructor(null).newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
