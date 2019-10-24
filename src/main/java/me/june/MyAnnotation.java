package me.june;

import java.lang.annotation.*;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 24/10/2019
 * Time: 9:15 오후
 **/
// 기본값은 CLASS 이다.
// (애노테이션 정보를 CLASS 파일 까지만 유지한다.)
// RUNTIME 까지 정보를 유지하려면 RUNTIME으로 지정을 해주어야한다.
// 바이트코드를 메모리에 로드할때까지 유지한다.
@Retention(RetentionPolicy.RUNTIME)

// 애노테이션 사용 위치를 클래스와, 필드로 제한한다.
@Target({ElementType.TYPE, ElementType.FIELD})

// 상속이 가능한 애노테이션 설정
// 상위클래스에 존재하는 애노테이션을 하위클래스에서 조회할수 있음
@Inherited
public @interface MyAnnotation {

    // 값들을 가질수 있는데 가질수 있는 타입은 제한되어있음
    // 기본형 타입과, Wrapper타입들만 가질수 있음

    // 기본 값도 지정이 가능하다.
    String name() default "juneyoung";

    int number() default 100;

    // 값을 받는 속성을 value로 지정하면 애노테이션으로 값을 받을때 애트리뷰트를 생략할수 있다.
    String value() default "hello";

}
