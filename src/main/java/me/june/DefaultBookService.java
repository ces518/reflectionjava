package me.june;

/**
 * Created by IntelliJ IDEA.
 * User: june
 * Date: 06/11/2019
 * Time: 8:58 오후
 **/
public class DefaultBookService implements BookService {

    @Inject
    BookRepository bookRepository;

    @Override
    public void rent (Book book) {
        System.out.println("rent" + book.getTitle());
    }
}
