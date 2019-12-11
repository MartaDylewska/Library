package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class AuthorService implements IAuthor {

    private String SQL, message;

    @Override
    public int addAuthor(String firstName, String lastName) {

        int authorId = getAuthorId(firstName, lastName);

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        SQL = "insert into author(first_name, last_name) values (?,?);";

        try  {
            if (authorId == 0) {
                preparedStatement = connection.prepareStatement(SQL);

                preparedStatement.setString(1, firstName);
                preparedStatement.setString(2, lastName);
                preparedStatement.executeUpdate();
                message = "Autor dodany do bazy.";

            }else{
                System.out.println("Autor istnieje w bazie");
            }
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return authorId;
    }

    @Override
    public int getAuthorId(String firstName, String lastName) {

        int authorId = 0;
        String SQL = "select author_id from author where first_name = ? and last_name = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                authorId = rs.getInt(1);}
            return authorId;
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);

        }

    }

    @Override
    public void removeAuthor(String firstName, String lastName) {

        SQL = "delete from author where first_name = ? and last_name = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            preparedStatement.executeUpdate();
            System.out.println("Author removed from database.");
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void removeAuthor(int id) {

        SQL = "delete from author where author_id = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            message = "Autor usunięty z bazy.";
            System.out.println(message);
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<Author> getAuthors() {

        List<Author> authors = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        SQL = "select * from author;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            ResultSet resultSet = preparedStatement.executeQuery(SQL);
            while (resultSet.next()) {
                Author author = new Author();
                author.setFirstName(resultSet.getString("first_name"));
                author.setLastName(resultSet.getString("last_name"));
                author.setId(resultSet.getInt("author_id"));
                authors.add(author);
            }
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return authors;
    }

    @Override
    public Author getAuthor(int id) {

        Author author = null;
        String SQL = "select * from author where author_id = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
            }
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return author;
    }

    @Override
    public Author getAuthor(String firstName, String lastName) {

        Author author = null;
        String SQL = "select * from author where first_name = ? and last_name = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
            }
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return author;
    }

    @Override
    public List<Author> getAuthors(String name) {

        List<Author> authors = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        String SQL = "select * from author where first_name = ? or last_name = ?;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, name);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
                authors.add(author);
            }
        } catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }

        return authors;
    }

 /*   @Override
    public void editAuthor(int id, String firstName, String lastName) {

        SQL = "update author set first_name = ?, last_name = ? where author_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

<<<<<<< HEAD
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3, id);
=======
                preparedStatement.executeUpdate();
            } catch (SQLException e){
                System.err.println("Error during invoke SQL query: \n" + e.getMessage());
                throw  new RuntimeException("Error during invoke SQL query");
            }
            finally {
                closeDBResources(connection,preparedStatement);
            }
        } else {
>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        message = "Autor zmieniony na: " + firstName + " " + lastName;
    }*/

    @Override
    public String getMessage(){
        return message;
    }
}