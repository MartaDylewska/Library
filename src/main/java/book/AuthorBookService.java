package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorBookService implements IAuthorBook{

    private Connecting connect = new Connecting();
    private String SQL, message;
    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();

    @Override
    public void addAuthorBook(String firstName, String lastName, String title, String genre, String publisher, String language, String alley, String bookstand, int shelf) {

        authorService.addAuthor(firstName, lastName);
        int authorId = authorService.getAuthorId(firstName, lastName);

        bookService.addBook(title, genre, publisher, language, alley, bookstand, shelf);
        int bookId = bookService.getBookId(title);

        SQL = "insert into author_book(author_id, book_id) values (?,?);";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2, bookId);

            preparedStatement.executeUpdate();
            message = "Dodano do bazy.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBooksOfAuthor(String firstName, String lastName) {

        int authorId = authorService.getAuthorId(firstName, lastName);

        SQL = "delete from author_book where author_book.author_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);

            preparedStatement.executeUpdate();
            message = "Książki " + firstName + " " + lastName + " zostały usunięte z bazy.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
//        authorService.removeAuthor(firstName, lastName);
    }

    @Override
    public void removeAuthorBook(String firstName, String lastName, String title) {

        int authorId = authorService.getAuthorId(firstName, lastName);
        int bookId = bookService.getBookId(title);

        SQL = "delete from author_book where author_book.author_id = ? author_book.book_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2, bookId);

            preparedStatement.executeUpdate();
            message = "Usunięto z bazy.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void removeBook(String title) {

        int bookId = bookService.getBookId(title);

        SQL = "delete from author_book where book_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(2, bookId);

            preparedStatement.executeUpdate();
            message = "Usunięto z bazy.";
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

//        bookService.removeBook(bookId);
    }

    @Override
    public void editAuthorBook(int id, String firstName, String lastName, String title) {

        int authorId = authorService.getAuthorId(firstName, lastName);
        int bookId = bookService.getBookId(title);

        SQL = "update author_book set author_id = ?, book_id = ? where author_id = ?";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2,bookId);
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public AuthorBook getAuthorBook(int authorId, int bookId) {

        AuthorBook authorBook = null;

        String SQL = "select * from author_book where author_id = ? and book_id = ?;";

        try (Connection conn = connect.connectDB()) {
            PreparedStatement preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);
            preparedStatement.setInt(2, bookId);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                authorBook = new AuthorBook();
                Author author = authorService.getAuthor(rs.getInt("author_id"));
                Book book = bookService.getBook(rs.getInt("book_id"));
                authorBook.setAuthor(author);
                authorBook.setBook(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return authorBook;
    }

//    @Override
//    public int getAuthorBookId(int authorId, int bookId) {
//
//        int authorBookId = 0;
//
//        String SQL = "select * from author_book where author_id = ? and book_id = ?;";
//
//        try (Connection conn = connect.connectDB()) {
//            PreparedStatement preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//            preparedStatement.setInt(2, bookId);
//
//            ResultSet rs = preparedStatement.executeQuery();
//            while (rs.next()) {
//                authorBookId = rs.getInt("author_book_id");
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return authorBookId;
//    }

    @Override
    public List<AuthorBook> getAllBooks() {

        List<AuthorBook> allBooks = new ArrayList<>();

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id;";

        try (Connection conn = connect.connectDB()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(SQL);

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBook(book);
                authorBook.setAuthor(author);
                allBooks.add(authorBook);

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return allBooks;
    }

    @Override
    public List<AuthorBook> getBooksOfAuthor(int authorId) {

        List<AuthorBook> booksOfAuthor = new ArrayList<>();

        SQL ="select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where author_book.author_id = ?;";
        PreparedStatement preparedStatement;

        try (Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBook(book);
                authorBook.setAuthor(author);

                booksOfAuthor.add(authorBook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return booksOfAuthor;
    }

    @Override
    public List<AuthorBook> getBySearch(String search) {

        List<AuthorBook> booksOfSearch = new ArrayList<>();

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id " +
                "where title = ? or publisher = ? or lang = ? or genre = ?;";
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
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setId(rs.getInt("author_id"));
                Bookshelf bookshelf = new Bookshelf(rs.getString("alley"), rs.getString("bookstand"), rs.getInt("shelf"));
                book.setTitle(rs.getString("title"));
                book.setBookshelf(bookshelf);
                book.setPublisher(rs.getString("publisher"));
                book.setLanguage(rs.getString("lang"));
                book.setGenre(rs.getString("genre"));
                book.setISBN(rs.getLong("isbn"));
                book.setBookId(rs.getInt("book_id"));
                AuthorBook authorBook = new AuthorBook();
                authorBook.setBook(book);
                authorBook.setAuthor(author);
                booksOfSearch.add(authorBook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return booksOfSearch;
    }

    @Override
    public String getMessage() {
        return message;
    }
}