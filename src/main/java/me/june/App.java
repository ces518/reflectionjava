package me.june;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
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

        // ㅇㅐ노테이션 접근
        // 기본적으로 주석과 동일한 취급을 하기 때문에
        // 바이트코드 로드시 해당 애노테이션 정보는 빼고 읽어온다.
        // getAnnotations() : 상위 클래스의 상속가능한 애노테이션정보까지 가져온다.
        // getDeclaredAnnotations() : 현재 클래스의 애노테이션 정보만 가져온다.
        Arrays.stream(MyBook.class.getAnnotations()).forEach(System.out::println);


        // 기본생성자를 가져와 인스턴스 생성
        Constructor<Book> constructor = bookClass.getConstructor(null);
        Book book1 = constructor.newInstance();

        // 파라메터가 존재하는 생성자를 가져와 인스턴스를 생성
        Constructor<Book> constructor1 = bookClass.getConstructor(String.class);
        Book hello = constructor1.newInstance("Hello");

        Field a = Book.class.getDeclaredField("A");
        // 값을 가져올때 해당 필드가 특정한 인스턴스에 해당하는 필드라면, 인스턴스를 넘겨줄 수 있다.
        // 하지만 지금은 static한 필드이기 때문에 null을 넘겨주면 가져올 수 있다.
        a.get(null);
        // 마찬가지로 첫번째인자는 특정 인스턴스, 두번째는 값을 지정해 변경할 수 있다.
        a.set(null, "BBBBB");

        Field b = Book.class.getDeclaredField("B");
        // B는 특정 인스턴스에 해당하는 필드이기 때문에 book 인스턴스를 넘겨주어야 한다.
        // private 변수이기 때문에 접근지시자를 무시하고 가져오도록 한다.
        b.setAccessible(true);
        b.get(book1);
        b.set(book1, "CXCCCCCC");

        Method c = Book.class.getDeclaredMethod("c");
        // 특정 인스턴스에 해당하는 메서드라면 인스턴스를 넘겨주어야한다.
        // private 메서드이기 떄문에 접근지시자를 무시하고 실행하도록 한다.
        c.setAccessible(true);
        c.invoke(book1);

        // 메서드를 가져올때 Primitive type, Wrapper type을 구분하므로 주의할것
        Method d = Book.class.getDeclaredMethod("d", int.class, int.class);
        int invoke = (int) d.invoke(book1, 1, 2);
    }
}
