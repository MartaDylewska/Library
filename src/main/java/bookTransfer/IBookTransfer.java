package bookTransfer;

import java.util.List;

public interface IBookTransfer {

    List<BookTransfer> getReservedUserBooks(int userId);
    List<BookTransfer> getLentUserBooks(int userId);
    void reserveBook(int userId, int bookId);
    void lendBook(int userId, int bookId);
    void acceptReturnBook(int userId, int bookId);
    void unReserveBook(int bookId);
    String getMessage();
}
