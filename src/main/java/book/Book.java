package book;

public class Book{

    private String title, genre, publisher, language;
    private long ISBN;
    private Bookshelf bookshelf;
    private int bookId;
    private boolean isAvailable;

    public Book(){
    }

    public Book(String title, String genre, String publisher, String language, long ISBN, Bookshelf bookshelf, boolean isAvailable, int bookId) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
        this.ISBN = ISBN;
        this.bookshelf = bookshelf;
        this.isAvailable = isAvailable;
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    public void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString(){
        return title + ", " + publisher;
    }
}