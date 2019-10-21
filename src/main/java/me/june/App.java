package me.june;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException {
        // Class 로딩이 끝나면 class타입의 인스턴스를 만들어 힙에 저장됨
        Class<Book> bookClass = Book.class;

        // 특정 인스턴스가 있다면 getClass() 를 사용해 가져올 수 있다.
        Book book = new Book();
        Class<? extends Book> aClass = book.getClass();

        // FQCN 으로 접근이 가능함
        Class<?> aClass1 = Class.forName("me.june.Book");


        // 클래스의 필드들에 접근
        // 이 메소드는 public한 것만 접근이 가능하다
        Field[] fields = bookClass.getFields();
        Arrays.stream(fields).forEach(System.out::println);

        // 모든 필드에 접근이 가능하다.
        Field[] declaredFields = bookClass.getDeclaredFields();
        Arrays.stream(declaredFields).forEach(System.out::println);

        // 필드의 값을 가져오기
        Arrays.stream(declaredFields).forEach(f -> {
            try {
                // private 한 변수의 값을 가져오기 위한 옵션
                f.setAccessible(true);
                System.out.printf("%s %s", f, f.get(book));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        // 생성자들 접근
        Arrays.stream(bookClass.getDeclaredConstructors()).forEach(System.out::println);

        // 부모클래스 접근
        Class<? super MyBook> superclass = MyBook.class.getSuperclass();
        System.out.println(superclass);

        // 인터페이스 접근
        Arrays.stream(MyBook.class.getInterfaces()).forEach(System.out::println);
    }
}
