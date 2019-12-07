package book;

class Bookshelf {

    private String alley, bookstand;
    private int shelf;

    Bookshelf(String alley, String bookstand, int shelf) {
        this.alley = alley;
        this.bookstand = bookstand;
        this.shelf = shelf;
    }

    @Override
    public String toString() {
        return "located at:" + alley + ", " + bookstand + ", " + shelf + ".";
    }
}
