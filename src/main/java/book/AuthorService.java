package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthor {

    private Connecting connect = new Connecting();
    private String SQL;

    @Override
    public void addAuthor(String firstName, String lastName) {

        int authorId = getAuthorId(firstName, lastName);

        SQL = "insert into author(first_name, last_name) values (?,?);";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            if (authorId == 0) {
                preparedStatement = conn.prepareStatement(SQL);

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);

                preparedStatement.executeUpdate();
                System.out.println("Author added to database.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public int getAuthorId(String firstName, String lastName) {

        int authorId = 0;
        String SQL = "select author_id from author where first_name = ? and last_name = ?;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                authorId = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authorId;
    }

    @Override
    public void removeAuthor(String firstName, String lastName) {

        SQL = "delete from author where first_name = ? and last_name = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            preparedStatement.executeUpdate();
            System.out.println("Author removed from database.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Author> getAuthors() {

        List<Author> authors = new ArrayList<>();

        SQL = "select * from author;";

        try (Connection conn = connect.connectDB()) {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                Author author = new Author(resultSet.getString("first_name"), resultSet.getString("last_name"));
                authors.add(author);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authors;
    }
}
