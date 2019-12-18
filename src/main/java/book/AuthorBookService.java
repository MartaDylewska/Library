package book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class AuthorBookService implements IAuthorBook{

    private String SQL, message;
    private IAuthor authorService = new AuthorService();
    private IBook bookService = new BookService();

    @Override
    public void addAuthorBook(String firstName, String lastName, String title) {

        SQL = "INSERT INTO author_book (author_id, book_id) " +
                "VALUES ((SELECT author_id FROM author WHERE first_name = ? and last_name = ?), " +
                "(SELECT book_id FROM book WHERE title = ?));";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3,title);

            preparedStatement.executeUpdate();
            message = "Dodano książkę z autorem do bazy.";

        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void removeBooksOfAuthor(String firstName, String lastName) {

        int authorId = authorService.getAuthorId(firstName, lastName);
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "delete from author_book where author_book.author_id = ?;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setInt(1, authorId);
            preparedStatement.executeUpdate();

            message = "Książki " + firstName + " " + lastName + " zostały usunięte z bazy.";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void removeBook(String title) {

        int bookId = bookService.getBookId(title);

        SQL = "delete from author_book where book_id = ?;";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, bookId);

            preparedStatement.executeUpdate();
            message = "Usunięto książkę z bazy.";
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void editAuthorBook(int id, String firstName, String lastName) {

        SQL = "update author_book set author_id = (select author_id " +
                "from author where first_name = ? and last_name = ?) " +
                "where book_id = ?;";

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setInt(3,id);

            preparedStatement.executeUpdate();
            message = "Dodano książkę z autorem do bazy.";

        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<AuthorBook> getAllBooks(int option) {

        List<AuthorBook> allBooks = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                    "inner join author on author.author_id = author_book.author_id " +
                    "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id ";

        if(option == 1){
            SQL = SQL + "order by title;";
        } else if(option == 2){
            SQL = SQL + "where available = true order by title;";
        } else {
            SQL = SQL + "where available = false order by title;";
        }

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
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
            return allBooks;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<AuthorBook> getBooksOfAuthor(int authorId, int option) {

        List<AuthorBook> booksOfAuthor = new ArrayList<>();

        SQL ="select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id where author_book.author_id = ?";

        if(option == 1){
            SQL = SQL + "order by title;";
        } else {
            SQL = SQL + "and available = true order by title;";
        }

        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement;


        try  {
            preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setInt(1, authorId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
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
    public List<AuthorBook> getBooksBySearch(String search, int option) {

        List<AuthorBook> booksOfSearch = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id " +
                "where publisher LIKE ? or lang LIKE ? or genre LIKE ? ";

        if(option == 1){
            SQL = SQL + "order by title;";
        } else {
            SQL = SQL + "and available = true order by title;";
        }

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, "%"+search+"%");
            preparedStatement.setString(2, "%"+search+"%");
            preparedStatement.setString(3, "%"+search+"%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
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
            }return booksOfSearch;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<AuthorBook> getBooksByTitle(String title, int option) {

        List<AuthorBook> booksOfSearch = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "inner join bookshelves on bookshelves.bookshelf_id = book.bookshelf_id " +
                "where title like ? ";

        if(option == 1){
            SQL = SQL + "order by title;";
        } else {
            SQL = SQL + "and available = true order by title;";
        }

        try  {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, "%" + title + "%");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
                author.setFirstName(rs.getString("first_name"));
                author.setLastName(rs.getString("last_name"));
                author.setAuthorId(rs.getInt("author_id"));
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
            } return booksOfSearch;
        } catch (SQLException e) {
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<AuthorBook> getNotLocatedBooks() {
        List<AuthorBook> booksOfSearch = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        SQL = "select * from author_book inner join book on author_book.book_id = book.book_id " +
                "inner join author on author.author_id = author_book.author_id " +
                "where bookshelf_id = 126 order by last_name;";

        try  {
            preparedStatement = connection.prepareStatement(SQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                Author author = new Author();
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
                booksOfSearch.add(authorBook);
            } return booksOfSearch;
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