package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBook {

//    private IAuthor iauthor = new AuthorService();
    private IBookshelf iBookshelf = new BookshelfService();
    private Connecting connect = new Connecting();
    private String SQL, message;

    @Override
    public Book getBook(int id) {

        Book book = null;

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where book.book_id = ? ;";

        // inner join author on author.author_id = book.author_id

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, id);

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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public Book getBook(String title) {
        Book book = null;

        SQL = "select * from book inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where title = ? ;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, title);

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
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    @Override
    public int getBookId(String title) {
        int bookId = 0;

        String SQL = "select book_id from book where title = ?;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, title);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                bookId = rs.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return bookId;
    }

    @Override
    public void addBook(String title, String genre, String publisher,
                        String language, String alley, String bookstand, int shelf) {

//        int authorId = iauthor.getAuthorId(firstName, lastName);
        int bookshelfId = iBookshelf.getBookshelf(alley, bookstand, shelf);

        SQL = "insert into book(title, publisher, lang, genre, availability, isbn, bookshelf_id) " +
                "values (?,?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {

            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, title);
//            preparedStatement.setInt(2, authorId);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, language);
            preparedStatement.setString(4, genre);
            preparedStatement.setBoolean(5,true);
            preparedStatement.setLong(6, System.currentTimeMillis());
            preparedStatement.setInt(7, bookshelfId);

            preparedStatement.executeUpdate();
            message = "Książka została dodana do bazy.";

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(int id) {

        SQL = "delete from book where book.book_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
            message = "Książka została usunięta z bazy.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, publisher);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, language);
            preparedStatement.setInt(5, id);

            preparedStatement.executeUpdate();
            message = "Książka została zedytowana.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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

        SQL = "update book set bookshelf_id = ? where book.book_id = ?";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, bookshelfId);
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();
            message = "Lokalizacja książki zmieniona na: alejka:" + alley + ", regał: " + bookstand + ", półka: " + shelf + ".";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setString(1, search);
            preparedStatement.setString(2, search);
            preparedStatement.setString(3, search);
            preparedStatement.setString(4, search);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
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

        SQL = "select * from book inner join author on author.author_id = book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id;";

        try (Connection conn = connect.connectDB()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Book book = new Book();
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