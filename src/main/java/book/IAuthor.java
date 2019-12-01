package book;

import java.util.List;

public interface IAuthor {

    void addAuthor(String firstName, String lastName);
    List<Author> getAuthors();
    Author getAuthor(int id);
    Author getAuthor(String name);
    String getAuthorId(String firstName, String lastName);
    void removeAuthor(int id);
    void removeAuthor(String name);
    void editAuthor(int id, String firstName, String lastName);
    void editAuthor(String name, String firstName, String lastName);
}
