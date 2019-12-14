package book;

import java.util.List;

public interface IAuthorBook {

    void addAuthorBook(String firstName, String lastName, String title);
//    void removeAuthorBook(String firstName, String lastName, String title);
    void removeBooksOfAuthor(String firstName, String lastName);
    void removeBook(String title);
//    void editAuthorBook(int id, String firstName, String lastName, String title);
//    AuthorBook getAuthorBook(int authorId, int bookId);
    List<AuthorBook> getAllBooks();
    List<AuthorBook> getBooksBySearch(String search);
    List<AuthorBook> getBooksOfAuthor(int authorId);
    List<AuthorBook> getBooksByTitle(String title);
    List<AuthorBook> getNotLocatedBooks();
    String getMessage();
}
