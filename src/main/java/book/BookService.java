package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBook {

    private IBook ibook;
    private List<Book> books = new ArrayList<>();
    private IAuthor iauthor;
    private Connecting connect = new Connecting();

    @Override
    public Book getBook(int id) {
        Book book = null;
//        boolean present = false;
//
//        for(Book book1 : books){
//            if(book1.getIdBook() == id){
//                book = book1;
//                present = true;
//            }
//        }
//
//        if(!present)
//            System.out.println("Nie ma takiej książki.");
//
        return book;
    }

    @Override
    public void addBook(String title, String genre, String publisher, String language, String firstName, String lastName) {

        Author author = new Author(firstName, lastName);
        Book book = new Book(author, title, genre,publisher,language);

        String SQL = "insert into book(title, author_id, publisher, lang, genre) values ('";
        PreparedStatement preparedStatement;

        try(Connection conn = connect.connectDB()) {
            preparedStatement = conn.prepareStatement(SQL + title + "'," + iauthor.getAuthorId(firstName, lastName) + ",'" + publisher+ "', '" +
                    language + "', '" + genre + "');");
            preparedStatement.executeUpdate();
            System.out.println("Book added to database.");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removeBook(int id) {
        Book book = getBook(id);
        books.remove(book);

    }

    @Override
    public void editBook(int id, Author author) {
//        Book book = getBook(id);
//        book.setAuthor(author);
    }

    @Override
    public void editBook(int id, String change) {
//        Book book = getBook(id);
//        book.setTitle(change);
    }

    @Override
    public List<Book> getBooks(Author author) {
        List<Book> booksOfAuthor = new ArrayList<>();
//
//        for(Book book : books){
//            if(book.getAuthor().equals(author)){
//                booksOfAuthor.add(book);
//            }
//        }
//
//        if(booksOfAuthor.size() == 0)
//            System.out.println("Nie ma książek autora: " + author);

        return booksOfAuthor;
    }

    @Override
    public List<Book> getBooks(String search) {
        List<Book> booksOfSearch = new ArrayList<>();

//        for(Book book : books){
//            if(search.equals(book.getTitle()) || search.equals(book.getGenre()) || search.equals(book.getPublisher())){
//                booksOfSearch.add(book);
//            }
//        }
//
//        if(booksOfSearch.size() == 0)
//            System.out.println("Nie ma książek o danym szukaniu.");

        return booksOfSearch;
    }
    @Override
    public List<Book> getAllBooks() {
        return books;
    }
}