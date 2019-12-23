package bookTransfer;

import java.util.List;

public interface IBookTransfer {

    List<BookTransfer> getReservedUserBooks(int userId);
    int getReservedUserBooksCount(int readerId);
    List<BookTransfer> getLentUserBooks(int userId);
    int getLentUserBooksCount(int readerId);
    void reserveBook(int userId, int bookId);
    void lendBook(int userId, int bookId);
    void acceptReturnBook(int userId, int bookId);
    void unReserveBook(int bookId);
    String getMessage();
}
