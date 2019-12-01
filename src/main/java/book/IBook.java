package book;

import java.util.List;

public interface IBook {

    Book getBook(int id);
    void addBook(String title, String genre, String publisher, String language, String firstName, String lastName);
    void removeBook(int id);
    void editBook(int id, Author author);
    void editBook(int id, String change);
    List<Book> getBooks(Author author);
    List<Book> getBooks(String search);
    List<Book> getAllBooks();
    long getISBN();
}
