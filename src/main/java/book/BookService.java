package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class BookService implements IBook {

    private IBookshelf iBookshelf = new BookshelfService();
    private String SQL, message;

    @Override
    public Book getBook(int idBook) {

        Book book = null;
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where book.book_id = ? ;";
<<<<<<< HEAD

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, id);
=======
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, idBook);
>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                book = new Book();
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                book.setAvailable(rs.getBoolean("available"));
            }
            return book;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public Book getBook(String title) {
        Book book = null;
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where title = ? ;";

        try {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, title);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                book = new Book();
//                Author author = new Author(rs.getString("first_name"), rs.getString("last_name"));
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
//                book.setAuthor(author);
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                book.setAvailable(rs.getBoolean("available"));
            }
            return book;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public int getBookId(String title) {
        int bookId = 0;
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select book_id from book where title = ?;";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, title);

            ResultSet rs = preparedStatement.executeQuery();
<<<<<<< HEAD
            while (rs.next())
                bookId = rs.getInt("book_id");
=======
            while (rs.next()){
                bookId = rs.getInt(1);}
            return bookId;
>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public int addBook(String title, String genre, String publisher,
                        String language, String alley, String bookstand, int shelf) {

<<<<<<< HEAD
        int bookShelfId = iBookshelf.getBookshelf(alley, bookstand, shelf);
        int bookId = getBookId(title);

        if(bookId == 0) {
            SQL = "insert into book (title, publisher, lang, genre, isbn, bookshelf_id) " +
                    "values (?, ?, ?, ?, ?, ?);";
            PreparedStatement preparedStatement;

            try (Connection conn = connect.connectDB()) {

                preparedStatement = conn.prepareStatement(SQL);

                preparedStatement.setString(1, title);
                preparedStatement.setString(2, publisher);
                preparedStatement.setString(3, language);
                preparedStatement.setString(4, genre);
                preparedStatement.setLong(5, System.currentTimeMillis());
                preparedStatement.setInt(6, bookShelfId);
=======
        int bookshelfId = iBookshelf.getBookshelf(alley, bookstand, shelf);
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "insert into book(title, publisher, lang, genre, available, isbn, bookshelf_id) " +
                "values (?,?, ?, ?, ?, ?, ?);";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, title);
//            preparedStatement.setInt(2, authorId);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, genre);
            preparedStatement.setBoolean(5,true);
            preparedStatement.setLong(6, System.currentTimeMillis());
            preparedStatement.setInt(7, bookshelfId);
>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc

                preparedStatement.executeUpdate();
                message = "Książka została dodana do bazy.";

<<<<<<< HEAD
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
=======
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc
        }
        return bookId;
    }

    @Override
    public void removeBook(int id) {

        SQL = "delete from book where book_id = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            message = "Książka została usunięta z bazy.";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

//    @Override
//    public void removeBook(String firstName, String lastName) {
//
//        int authorId = iauthor.getAuthorId(firstName, lastName);
//
//        SQL = "delete from book where author_id = ?;";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//
//            preparedStatement.executeUpdate();
//            message = "Książki " + firstName + " " + lastName + " zostały usunięte z bazy.";
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    @Override
    public void editBook(int id, String title, String publisher, String genre, String language) {

        SQL = "update book set title = ?, publisher = ?, genre = ?, lang = ? where book.book_id = ?";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, language);
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
            message = "Książka została zedytowana.";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

//    @Override
//    public void editBook(int id, String firstName, String lastName) {
//
//        int authorId = iauthor.getAuthorId(firstName, lastName);
//
//        if(authorId == 0){
//            iauthor.addAuthor(firstName, lastName);
//            authorId = iauthor.getAuthorId(firstName, lastName);
//        }
//
//        SQL = "update book set author_id = ? where author_id = ?";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//            preparedStatement.setInt(2, id);
//
//            preparedStatement.executeUpdate();
//            message = "Autor książki zmieniony na: " + firstName + " " + lastName;
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

//    @Override
//    public void editBook(int oldAuthorId, int newAuthorId) {
//
//        SQL = "update book set author_id = ? where author_id = ?";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, newAuthorId);
//            preparedStatement.setInt(2, oldAuthorId);
//
//            preparedStatement.executeUpdate();
//            message = "Autor książki został zmieniony";
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }

    @Override
    public void editBook(int id, String alley, String bookstand, int shelf) {

        int bookshelfId = iBookshelf.getBookshelf(alley, bookstand,shelf);
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "update book set bookshelf_id = ? where book.book_id = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, bookshelfId);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            message = "Lokalizacja książki zmieniona na: alejka:" + alley + ", regał: " + bookstand + ", półka: " + shelf + ".";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void setBookAvailability(int bookId, boolean isAvailable) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "update book set available = ? where book.book_id = ?";

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setBoolean(1, isAvailable);
            preparedStatement.setInt(2, bookId);

            preparedStatement.executeUpdate();
            message = "Dostępność książki została zmieniona";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

//    @Override
//    public List<Book> getBooks(String firstName, String lastName) {
//
//        List<Book> booksOfAuthor = new ArrayList<>();
//
//        int authorId = iauthor.getAuthorId(firstName, lastName);
//
//        SQL = "select * from book inner join author on author.author_id = book.author_id " +
//                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where author.author_id = ?;";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//
//            while (rs.next()) {
//
//                Book book = new Book();
//                Author author = new Author(rs.getString("first_name"), rs.getString("last_name"));
//                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
//                book.setTitle(rs.getString("title"));
//                book.setAuthor(author);
//                book.setBookshelf(bookshelf);
//                book.setPublisher(rs.getString("publisher"));
//                book.setLanguage(rs.getString("lang"));
//                book.setGenre(rs.getString("genre"));
//                book.setISBN(rs.getLong("isbn"));
//                book.setBookId(rs.getInt("book_id"));
//
//                booksOfAuthor.add(book);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//        return booksOfAuthor;
//    }

    @Override
    public List<Book> getBooks(String search) {

        List<Book> booksOfSearch = new ArrayList<>();

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id " +
                "where title = ? or publisher = ? or lang = ? or genre = ? ;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                book.setAvailable(rs.getBoolean("available"));
                booksOfSearch.add(book);
            }
            return booksOfSearch;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<Book> getAllBooks() {

        List<Book> books = new ArrayList<>();

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id;";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery(SQL);

            while (rs.next()) {
                Book book = new Book();
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                book.setAvailable(rs.getBoolean("available"));
                books.add(book);
            } return books;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public String getMessage(){
        return message;
    }
}