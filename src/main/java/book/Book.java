package book;

import java.util.ArrayList;
import java.util.List;

class Book implements IBook{

    private IAuthor author;
    private String title, genre, publisher;
    private long ISBN;
    private static int _idBook = 0;
    private int idBook;
    private List<Book> books = new ArrayList<>();

    Book(){}

    private Book(Author author, String title, String genre, String publisher) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        setIdBook();
        setISBN();
    }

    private IAuthor getAuthor() {
        return author;
    }

    private void setAuthor(IAuthor author) {
        this.author = author;
    }

    private String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    private String getGenre() {
        return genre;
    }

    private void setGenre(String genre) {
        this.genre = genre;
    }

    private String getPublisher() {
        return publisher;
    }

    private void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getISBN() {
        return ISBN;
    }

    private void setISBN() {
        this.ISBN = System.currentTimeMillis();
    }

    private int getIdBook() {
        return idBook;
    }

    private void setIdBook() {
        _idBook++;
        this.idBook = idBook + _idBook;
    }

    @Override
    public Book getBook(int id) {
        Book book = null;
        boolean present = false;

        for(Book book1 : books){
            if(book1.getIdBook() == id){
                book = book1;
                present = true;
            }
        }

        if(!present)
            System.out.println("Nie ma takiej książki.");

        return book;
    }

    @Override
    public Book addBook(Author author, String title, String genre, String publisher) {
        Book book = new Book(author, title, genre, publisher);
        books.add(book);
        return book;
    }

    @Override
    public void removeBook(int id) {
        Book book = getBook(id);
        books.remove(book);

    }

    @Override
    public void editBook(int id, Author author) {
        Book book = getBook(id);
        book.setAuthor(author);
    }

    @Override
    public void editBook(int id, String change) {
        Book book = getBook(id);
        book.setTitle(change);
    }

    @Override
    public List<Book> getBooks(Author author) {
        List<Book> booksOfAuthor = new ArrayList<>();

        for(Book book : books){
            if(book.getAuthor().equals(author)){
                booksOfAuthor.add(book);
            }
        }

        if(booksOfAuthor.size() == 0)
            System.out.println("Nie ma książek autora: " + author);

        return booksOfAuthor;
    }

    @Override
    public List<Book> getBooks(String search) {
        List<Book> booksOfSearch = new ArrayList<>();

        for(Book book : books){
            if(search.equals(book.getTitle()) || search.equals(book.getGenre()) || search.equals(book.getPublisher())){
                booksOfSearch.add(book);
            }
        }

        if(booksOfSearch.size() == 0)
            System.out.println("Nie ma książek o danym szukaniu.");

        return booksOfSearch;
    }
    @Override
    public List<Book> getAllBooks() {
        return books;
    }

    @Override
    public String toString(){
        return "Książka: " + title + ", " + author + ", " + publisher + ", ISBN: " + ISBN;
    }
}
