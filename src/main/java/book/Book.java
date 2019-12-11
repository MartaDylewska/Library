package book;

public class Book{

    private String title, genre, publisher, language;
    private long ISBN;
    private Bookshelf bookshelf;
    private int bookId;

    Book(){
    }

<<<<<<< HEAD
=======
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

    public Book(String title, String genre, String publisher, String language, long ISBN, Bookshelf bookshelf, boolean isAvailable) {
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
        this.ISBN = ISBN;
        this.bookshelf = bookshelf;
        this.isAvailable = isAvailable;
    }

    //    public Author getAuthor() {
//        return author;
//    }
//
//    void setAuthor(Author author) {
//        this.author = author;
//    }

>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc
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

<<<<<<< HEAD
=======
    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

>>>>>>> 5cd5476726d0fef5a965817db3a879e97e880edc
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