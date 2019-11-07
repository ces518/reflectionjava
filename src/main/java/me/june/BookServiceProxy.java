package me.june;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 07/11/2019
 * Time: 10:14 오후
 **/
public class BookServiceProxy implements BookService {

    BookService bookService = new DefaultBookService();

    @Override
    public void rent(Book book) {
        System.out.println("======= logging ==========");
        bookService.rent(book);
    }
}
