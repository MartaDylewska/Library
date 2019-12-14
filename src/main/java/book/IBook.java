package book;

public interface IBook {

    Book getBook(int id);
    Book getBook(String title);
    int getBookId(String title);
    void addBook(String title, String genre, String publisher, String language,String alley, String bookstand, int shelf);
    void removeBook(int id);
//    void removeBook(String firstName, String lastName);
//    void editBook(int id, String firstName, String lastName);
    void editBook(int id, String title, String publisher, String genre, String language);
    void editBook(int id, String alley, String bookstand, int shelf);
    void setBookAvailability(int bookId, boolean isAvailable);
//    void editBook(int oldAuthorId, int newAuthorId);
//    List<Book> getBooks(String firstName, String lastName);
//    List<Book> getBooks(String search);
//    List<Book> getAllBooks();
    String getMessage();
}