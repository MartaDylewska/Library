package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorService implements IAuthor {

    private Connecting connect = new Connecting();
    private String SQL, message;

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
                message = "Author added to database.";
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
            message = "Author removed from database.";
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

    @Override
    public Author getAuthor(int id) {

        Author author = null;
        String SQL = "select * from author where author_id = ?;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, String.valueOf(id));

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                author = new Author(rs.getString(1), rs.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return author;
    }

    @Override
    public void editAuthor(int id, String firstName, String lastName) {

        SQL = "update author set first_name = ?, set last_name = ? where author_id = ?";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, String.valueOf(id));

            preparedStatement.executeUpdate();
            message = "Author changed to " + firstName + " " + lastName;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String getMessage(){
        return message;
    }
}
