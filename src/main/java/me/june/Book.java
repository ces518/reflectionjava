package me.june;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 21/10/2019
 * Time: 10:37 오후
 **/
@MyAnnotation
public class Book {
    public static String A = "A";

    private String B = "B";

    public Book() {
    }

    public Book(String b) {
        B = b;
    }

    public void c() {
        System.out.println("C");
    }

    public int d(int left, int right) {
        return left + right;
    }

    public String getTitle() {
        return "title";
    }
}
