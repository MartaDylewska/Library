package book;

class Book{

    private String title, genre, publisher, language;
    private long ISBN;
    private Author author;
    private Bookshelf bookshelf;
    private boolean isAvailable;

    Book(){
    }

    private Author getAuthor() {
        return author;
    }

    void setAuthor(Author author) {
        this.author = author;
    }

    private String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    private String getGenre() {
        return genre;
    }

    void setGenre(String genre) {
        this.genre = genre;
    }

    private String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    private long getISBN() {
        return ISBN;
    }

    void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    private String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    Bookshelf getBookshelf() {
        return bookshelf;
    }

    void setBookshelf(Bookshelf bookshelf) {
        this.bookshelf = bookshelf;
    }

    boolean isAvailable() {
        return isAvailable;
    }

    void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString(){
        return title + ", " + author + ", " + publisher + ", ISBN: " + ISBN;
    }
}