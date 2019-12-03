package book;

class Book{

    private String title, genre, publisher, language;
    private long ISBN;
    private Author author;
    private Storage storage;
    private boolean isAvailable;

    Book(){
        setISBN();
        setAvailable(true);
    }

    Book(Author author, String title, String genre, String publisher, String language) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.language = language;
        setISBN();
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

    private void setISBN() {
        this.ISBN = System.currentTimeMillis();
    }

    private String getLanguage() {
        return language;
    }

    void setLanguage(String language) {
        this.language = language;
    }

    Storage getStorage() {
        return storage;
    }

    void setStorage(Storage storage) {
        this.storage = storage;
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