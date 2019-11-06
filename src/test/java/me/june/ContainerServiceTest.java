package me.june;


import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ContainerServiceTest {

    @Test
    public void bookRepository () {
        BookRepository object = ContainerService.getObject(BookRepository.class);
        assertNotNull(object);
    }

    @Test
    public void bookService () {
        BookService object = ContainerService.getObject(BookService.class);
        assertNotNull(object);
        assertNotNull(object.bookRepository);
    }
}
