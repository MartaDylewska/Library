package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookshelfService implements IBookshelf{

    private Connecting connect = new Connecting();

    @Override
    public int getBookshelf(String alley, String bookstand, int shelf) {

        int bookshelfId = 0;
        String SQL = "select bookshelf_id from bookshelves where alley = ? and bookstand = ? and shelf = ?;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, alley);
            preparedStatement.setString(2, bookstand);
            preparedStatement.setInt(3, shelf);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                bookshelfId = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookshelfId;
    }
}
