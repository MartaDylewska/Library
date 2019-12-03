package book;

import java.util.List;

public interface IAuthor {

    void addAuthor(String firstName, String lastName);
    List<Author> getAuthors();
    int getAuthorId(String firstName, String lastName);
    void removeAuthor(String firstName, String lastName);
    void editAuthor(int id, String firstName, String lastName);
    Author getAuthor(int id);
    String getMessage();
}
