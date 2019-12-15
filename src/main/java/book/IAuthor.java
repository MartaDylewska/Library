package book;

import java.util.List;

public interface IAuthor {

    void addAuthor(String firstName, String lastName);
    List<Author> getAuthors();
    List<Author> getAuthors(String name);
    Author getAuthor(int id);
    int getAuthorId(String firstName, String lastName);
    void editAuthor(int id, String firstName, String lastName);
    void removeAuthor(int id);
    String getMessage();
}
