package book;

import java.util.List;

public interface IAuthorBook {

    void addAuthorBook(String firstName, String lastName, String title);
    List<AuthorBook> getAllBooks(int option);
    List<AuthorBook> getBooksBySearch(String search, int option);
    List<AuthorBook> getBooksOfAuthor(int authorId, int option);
    List<AuthorBook> getBooksByTitle(String title, int option);
    List<AuthorBook> getNotLocatedBooks();
    void editAuthorBook(int id, String firstName, String lastName);
    void removeBooksOfAuthor(String firstName, String lastName);
    void removeBook(String title);
    String getMessage();
}
