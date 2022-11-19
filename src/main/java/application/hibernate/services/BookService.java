package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Book;

public interface BookService {
    Book saveBook(Book book, Long userId);

    List<Book> getAllBooks();
}
