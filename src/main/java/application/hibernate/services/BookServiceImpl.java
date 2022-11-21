package application.hibernate.services;

import java.util.List;

import application.hibernate.entities.Book;
import application.hibernate.repos.BookRepository;

public class BookServiceImpl implements BookService {
    BookRepository bookRepository = new BookRepository();

    @Override
    public Book saveBook(Book book, Long userId) {
        return bookRepository.save(book, userId);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book update(Book book) {
        return bookRepository.update(book);
    }
}
