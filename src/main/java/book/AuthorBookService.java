package book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorBookService {

//
//}private Connecting connect = new Connecting();
//    private String SQL, message;
//    private IAuthor authorService = new AuthorService();
//    private IBook bookService = new BookService();
//
//    @Override
//    public void addAuthorBook(String firstName, String lastName, String title, String genre, String publisher, String language, String alley, String bookstand, int shelf) {
//
//        int authorId = authorService.getAuthorId(firstName, lastName);
//        if(authorId == 0) {
//            authorService.addAuthor(firstName, lastName);
//            authorId = authorService.getAuthorId(firstName, lastName);
//        }
//
//        int bookId = bookService.getBookId(title);
//        if(bookId == 0) {
//            bookService.addBook(title, authorId, genre, publisher, language, alley, bookstand, shelf);
//            bookId = bookService.getBookId(title);
//        }
//
//        int authorBookId = getAuthorBookId(authorId, bookId);
//
//        SQL = "insert into author_book(author_id, book_id) values (?,?);";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            if (authorBookId == 0) {
//                preparedStatement = conn.prepareStatement(SQL);
//
//                preparedStatement.setInt(1, authorId);
//                preparedStatement.setInt(2, bookId);
//
//                preparedStatement.executeUpdate();
//                message = "Dodano do bazy.";
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void removeBooksOfAuthor(String firstName, String lastName) {
//
//        int authorId = authorService.getAuthorId(firstName, lastName);
//
//        SQL = "delete from book where book.author_id = ?;";
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
//        authorService.removeAuthor(firstName, lastName);
//    }
//
//    @Override
//    public void removeAuthorBook(String firstName, String lastName, String title) {
//
//        int authorId = authorService.getAuthorId(firstName, lastName);
//        int bookId = bookService.getBookId(title);
//
//        SQL = "delete from author_book where author_id = ? and book_id = ?;";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//            preparedStatement.setInt(2, bookId);
//
//            preparedStatement.executeUpdate();
//            message = "Usunięto z bazy.";
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    @Override
//    public void removeBook(String title) {
//
//        int bookId = bookService.getBookId(title);
//
//        SQL = "delete from author_book where author_book.book_id = ?;";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(2, bookId);
//
//            preparedStatement.executeUpdate();
//            message = "Usunięto z bazy.";
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        bookService.removeBook(bookId);
//    }
//
//    @Override
//    public void editAuthorBook(int id, String firstName, String lastName, String title) {
//
//        int authorId = authorService.getAuthorId(firstName, lastName);
//        int bookId = bookService.getBookId(title);
//
//        SQL = "update author_book set author_id = ?, book_id = ? where author_id = ?";
//        PreparedStatement preparedStatement;
//
//        try (Connection conn = connect.connectDB()) {
//            preparedStatement = conn.prepareStatement(SQL);
//
//            preparedStatement.setInt(1, authorId);
//            preparedStatement.setInt(2, bookId);
//            preparedStatement.setInt(3, id);
//
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
//
////    @Override
////    public void editBook(int id, String firstName, String lastName) {
////
////        int authorId = iauthor.getAuthorId(firstName, lastName);
////
////        if(authorId == 0){
////            iauthor.addAuthor(firstName, lastName);
////            authorId = iauthor.getAuthorId(firstName, lastName);
////        }
////
////        SQL = "update book set author_id = ? where author_id = ?";
////        PreparedStatement preparedStatement;
////
////        try (Connection conn = connect.connectDB()) {
////            preparedStatement = conn.prepareStatement(SQL);
////
////            preparedStatement.setInt(1, authorId);
////            preparedStatement.setInt(2, id);
////
////            preparedStatement.executeUpdate();
////            message = "Autor książki zmieniony na: " + firstName + " " + lastName;
////        } catch (SQLException e) {
////            System.out.println(e.getMessage());
////        }
////    }
//
////    @Override
////    public void editBook(int oldAuthorId, int newAuthorId) {
////
////        SQL = "update book set author_id = ? where author_id = ?";
////        PreparedStatement preparedStatement;
////
////        try (Connection conn = connect.connectDB()) {
////            preparedStatement = conn.prepareStatement(SQL);
////
////            preparedStatement.setInt(1, newAuthorId);
////            preparedStatement.setInt(2, oldAuthorId);
////
////            preparedStatement.executeUpdate();
////            message = "Autor książki został zmieniony";
////        } catch (SQLException e) {
////            System.out.println(e.getMessage());
////        }
////    }
//
//    @Override
//    public AuthorBook getAuthorBook(int authorId, int bookId) {
//
//        AuthorBook authorBook = null;
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
//                authorBook = new AuthorBook();
//                Author author = authorService.getAuthor(rs.getInt("author_id"));
//                Book book = bookService.getBook(rs.getInt("book_id"));
//                authorBook.setAuthor(author);
//                authorBook.setBook(book);
//            }
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return authorBook;
//    }
//
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
//
//    @Override
//    public String getMessage() {
//        return message;
//    }
}