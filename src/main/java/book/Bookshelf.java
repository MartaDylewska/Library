package book;

public class Bookshelf {

    private String alley, bookstand;
    private int shelf;

    Bookshelf(String alley, String bookstand, int shelf) {
        this.alley = alley;
        this.bookstand = bookstand;
        this.shelf = shelf;
    }

    public String getAlley() {
        return alley;
    }

    public void setAlley(String alley) {
        this.alley = alley;
    }

    public String getBookstand() {
        return bookstand;
    }

    public void setBookstand(String bookstand) {
        this.bookstand = bookstand;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "lokalizacja: " + alley + ", " + bookstand + ", " + shelf + ".";
    }
}
