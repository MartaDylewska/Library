package book;

class Book{

    private Author author;
    private String title, genre, publisher, language;
    private long ISBN;

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

    private void setAuthor(Author author) {
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

    private long getISBN() {
        return ISBN;
    }

    private void setISBN() {
        this.ISBN = System.currentTimeMillis();
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString(){
        return title + ", " + author + ", " + publisher + ", ISBN: " + ISBN;
    }
}
