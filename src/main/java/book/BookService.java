package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBook {

    private IAuthor iauthor = new AuthorService();
    private Connecting connect = new Connecting();
    private String SQL, message;

    @Override
    public Book getBook(int id) {

        Book book = null;
        Author author;

        SQL = "select * from book inner join author on author.author_id = book.book_id where book_id = ? ;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                book = new Book();
                author = new Author(rs.getString("first_name"), rs.getString("last_name"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(author);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public void addBook(String title, String genre, String publisher, String language, String firstName, String lastName) {

        int authorId = iauthor.getAuthorId(firstName, lastName);

        SQL = "insert into book(title, author_id, publisher, lang, genre, availability) values (?,?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {

            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, authorId);
            preparedStatement.setString(3, publisher);
            preparedStatement.setString(4, language);
            preparedStatement.setString(5, genre);
            preparedStatement.setBoolean(6,true);

            preparedStatement.executeUpdate();
            message = "Book added to database.";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(int id) {

        SQL = "delete from book where book_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            message = "Book removed from database.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(String firstName, String lastName) {

        int authorId = iauthor.getAuthorId(firstName, lastName);

        SQL = "delete from book where author_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);

            preparedStatement.executeUpdate();
            message = "Books of " + firstName + " " + lastName + " removed from database.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editBook(String key, String value,int id) {

        SQL = "update book set ? = ? where book_id = ?";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, key);
            preparedStatement.setString(2, value);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
            System.out.println("Book's " + key + " changed to " + value);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void editBook(int id, String firstName, String lastName) {

        int authorId = iauthor.getAuthorId(firstName, lastName);

        if(authorId == 0){
            iauthor.addAuthor(firstName, lastName);
            authorId = iauthor.getAuthorId(firstName, lastName);
        }

        SQL = "update book set author_id = ? where book_id = ?";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            message = "Book's author changed to " + firstName + " " + lastName;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Book> getBooks(String firstName, String lastName) {
        List<Book> booksOfAuthor = new ArrayList<>();

        int authorId = iauthor.getAuthorId(firstName, lastName);

        SQL = "select * from book where author_id = ? ;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(iauthor.getAuthor(rs.getInt("author_id")));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                booksOfAuthor.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booksOfAuthor;
    }

    @Override
    public List<Book> getBooks(String search) {

        List<Book> booksOfSearch = new ArrayList<>();

        SQL = "select * from book where title = ? or publisher = ? or lang = ? or genre = ? ;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);

            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(iauthor.getAuthor(rs.getInt("author_id")));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                booksOfSearch.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return booksOfSearch;
    }

    @Override
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        SQL = "select * from book;";

        try (Connection conn = connect.connectDB()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Book book = new Book();
                book.setTitle(rs.getString("title"));
                book.setAuthor(iauthor.getAuthor(rs.getInt("author_id")));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return books;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
