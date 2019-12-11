package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class BookshelfService implements IBookshelf{

    private Connecting connect = new Connecting();

    @Override
    public int getBookshelf(String alley, String bookstand, int shelf) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        int bookshelfId = 0;
        String SQL = "select bookshelf_id from bookshelves where alley = ? and bookstand = ? and shelf = ?;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, alley);
            preparedStatement.setString(2, bookstand);
            preparedStatement.setInt(3, shelf);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                bookshelfId = rs.getInt(1);
            }return bookshelfId;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }


    }
}
