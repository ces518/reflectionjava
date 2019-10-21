package me.june;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 21/10/2019
 * Time: 10:37 오후
 **/
public class Book {
    public static String b = "BOOK";

    public static final String C = "BOOK";

    private String a = "a";

    public String d = "d";

    protected String e = "e";

    public Book() {
    }

    public Book(String a, String d, String e) {
        this.a = a;
        this.d = d;
        this.e = e;
    }

    private void f() {
        System.out.println("f");
    }

    public void g() {
        System.out.println("g");
    }

    public int h() {
        return 100;
    }
}
