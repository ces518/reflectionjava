package me.june;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.InvocationHandlerAdapter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;

import java.lang.reflect.*;
import java.nio.ByteBuffer;

import static net.bytebuddy.matcher.ElementMatchers.named;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 11/11/2019
 * Time: 10:29 오후
 **/
public class BookServiceTest {

    // 매번 프록시 클래스를 만드는것이 아닌, 런타임에 생성해준다.
    // 어떤 인터페이스 타입의 구현체인지 2번째 인자로 넣어주어야함
    BookService bookService = (BookService) Proxy.newProxyInstance(BookService.class.getClassLoader(), new Class[]{ BookService.class },
            new InvocationHandler() {
                // 리얼 서브젝트가 여기 존재해야한다.
                BookService bookService = new DefaultBookService();

                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    // proxy는 여기서 생성한 proxy 객체
                    // 여기에 부가적인 기능을 넣어준다.

                    if (method.getName().equals("rent")) {
                        // 이방식은 bookService에 return 에서도 프록시가 적용된다.
                        // rent에만 적용하고 싶다면 추가적인 코딩을 해주어야한다.

                        // 프록시를 사용하여 부가적인 기능을 추가함
                        System.out.println("aaaaa");
                        Object invoke = method.invoke(bookService, args);
                        System.out.println("bbbbb");
                        return invoke;
                    }

                    return method.invoke(bookService, args);
                    // InvocationHandler 가 유연하지 않다.
                    // Spring AOP가 이 구조를 뜯어고침
                }
            });
    // java의 다이나믹 프록시는 클래스 기반의 프록시를 만들수 없다.
    // 반드시 인터페이스 여야한다.

    @Test
    public void di () throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // invocation handler 를 넘겨준것처럼 handler를 넘겨주어야 한다.
        // 이전의 다이나믹 프록시와 마찬가지로 프록시 객체가 부가적인 일을 할 부분을 구현해준다.
        MethodInterceptor handler = new MethodInterceptor() {
            // 리얼 서브젝트 클래스
            BookService bookService = new DefaultBookService();

            @Override
            public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                System.out.println("aaaa");
                Object invoke = method.invoke(bookService, args);
                System.out.println("bbbb");
                return invoke;
            }
        };
        // Enhancer 가 CGlib의 핵심 클래스이다.
        BookService bookService = (BookService) Enhancer.create(BookService.class, handler);

        // bytebuddy 를 사용한 방법
        // 인스턴스를 바로 만들어주지 않고, 클래스를 만들어 주어야한다.
        // 프록시 클래스를 만드는것과 비슷한다.
        Class<? extends BookService> proxyClass = new ByteBuddy().subclass(BookService.class)
                // rent method를 선택하여 InvocationHandler에게 위임한다.
                .method(named("rent")).intercept(InvocationHandlerAdapter.of(new InvocationHandler() {
                    BookService bookService = new DefaultBookService();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        Object invoke = method.invoke(bookService, args);
                        return invoke;
                    }
                }))
                .make().load(BookService.class.getClassLoader()).getLoaded();

        // 아무런 것도 변경되지 않은 하위클래스를 생성
        BookService bookService2 = proxyClass.getConstructor(null).newInstance();
    }
}
