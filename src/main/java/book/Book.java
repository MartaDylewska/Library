package book;

public class Book{

    private String title, genre, publisher, language;
    private long ISBN;
    private Bookshelf bookshelf;
    private int bookId;

    Book(){
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public long getISBN() {
        return ISBN;
    }

    void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    public Bookshelf getBookshelf() {
        return bookshelf;
    }

    void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString(){
        return title + ", " + publisher + ", " + bookshelf;
    }
}