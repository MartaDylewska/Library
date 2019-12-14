package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class BookshelfService implements IBookshelf{

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

    @Override
    public String[] getAlleys() {

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        List<String> listOfAlleys = new ArrayList<>();
//        listOfAlleys.add("-");
        String SQL = "select distinct alley from bookshelves order by alley;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String alley = rs.getString("alley");
                listOfAlleys.add(alley);
            }
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return listOfAlleys.toArray(new String[0]);
    }

    @Override
    public String[] getBookstands() {

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        List<String> listOfBookstands = new ArrayList<>();
//        listOfBookstands.add("-");

        String SQL = "select distinct bookstand from bookshelves order by bookstand;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String bookstand = rs.getString("bookstand");
                listOfBookstands.add(bookstand);
            }
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return listOfBookstands.toArray(new String[0]);
    }

    @Override
    public String[] getShelves() {

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        List<String> listOfShelves = new ArrayList<>();
//        listOfShelves.add("0");

        String SQL = "select distinct shelf from bookshelves order by shelf;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String shelf = String.valueOf(rs.getInt("shelf"));
                listOfShelves.add(shelf);
            }
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return listOfShelves.toArray(new String[0]);
    }
}
