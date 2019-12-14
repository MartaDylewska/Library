package bookTransfer;

import book.Author;
import book.AuthorBook;
import book.Book;
import book.Bookshelf;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class BookTransferService implements IBookTransfer {

    private String SQL, message;

    @Override
    public List<BookTransfer> getReservedUserBooks(int userId) {
        List<BookTransfer> usersBooks = new ArrayList<>();

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from reservation inner join book on reservation.book_id = book.book_id " +
                "inner join author_book on reservation.book_id = author_book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on book.bookshelf_id = bookshelves.bookshelf_id " +
                "where reader_id = ? order by title;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setBookshelf(bookshelf);
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBook(book);
                authorBook.setAuthor(author);
                BookTransfer bookTransfer = new BookTransfer();
                bookTransfer.setAuthorBook(authorBook);
                bookTransfer.setDuedate(rs.getDate("duedate"));
                usersBooks.add(bookTransfer);
            } return usersBooks;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<BookTransfer> getLentUserBooks(int userId) {
        List<BookTransfer> usersBooks = new ArrayList<>();

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from lending inner join book on lending.book_id = book.book_id " +
                "inner join author_book on lending.book_id = author_book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on book.bookshelf_id = bookshelves.bookshelf_id " +
                "where reader_id = ? order by title;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, userId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setBookshelf(bookshelf);
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
                book.setTitle(rs.getString("title"));
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBook(book);
                authorBook.setAuthor(author);
                BookTransfer bookTransfer = new BookTransfer();
                bookTransfer.setAuthorBook(authorBook);
                bookTransfer.setDuedate(rs.getDate("duedate"));
                usersBooks.add(bookTransfer);
            } return usersBooks;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void reserveBook(int userId, int bookId) {

        SQL = "INSERT INTO reservation (book_id, reader_id, datecreate, duedate) " +
                "VALUES ((SELECT book_id FROM author_book WHERE book_id = ?), ?, ?, ?);";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now().plusDays(7)));

            preparedStatement.executeUpdate();
            message = "Zarezerwowano.";

        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void lendBook(int userId, int bookId) {

        SQL = "INSERT INTO lending (book_id, reader_id, creationdate, duedate) " +
                "VALUES ((SELECT book_id FROM author_book WHERE book_id = ?), ?, ?, ?);";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, bookId);
            preparedStatement.setInt(2, userId);
            preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement.setDate(4, Date.valueOf(LocalDate.now().plusMonths(1)));

            preparedStatement.executeUpdate();
            message = "Książka została wypożyczona.";

        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void acceptReturnBook(int userId, int bookId) {

        SQL = "delete from lending where book_id = ?;";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();

            message = "Książka została wzrócona.";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void unReserveBook(int bookId) {

        SQL = "delete from reservation where book_id = ?;";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public String getMessage() {
        return message;
    }
}
