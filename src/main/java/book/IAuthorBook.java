package book;

import java.util.List;

public interface IAuthorBook {

    void addAuthorBook(String firstName, String lastName, String title, String genre, String publisher, String language, String alley, String bookstand, int shelf);
    void removeAuthorBook(String firstName, String lastName, String title);
    void removeBooksOfAuthor(String firstName, String lastName);
    void removeBook(String title);
    void editAuthorBook(int id, String firstName, String lastName, String title);
    AuthorBook getAuthorBook(int authorId, int bookId);
    String getMessage();
    List<AuthorBook> getAllBooks();
    List<AuthorBook> getBySearch(String search);
    List<AuthorBook> getBooksOfAuthor(int authorId);
    List<AuthorBook> getByTitle(String title);
}
