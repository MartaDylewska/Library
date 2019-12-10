package book;

import java.util.List;

public interface IAuthor {

    int addAuthor(String firstName, String lastName);
    List<Author> getAuthors();
    int getAuthorId(String firstName, String lastName);
    void removeAuthor(String firstName, String lastName);
    void removeAuthor(int id);
    void editAuthor(int id, String firstName, String lastName);
    Author getAuthor(int id);
    List<Author> getAuthors(String name);
    Author getAuthor(String firstName, String lastName);
    String getMessage();
}
