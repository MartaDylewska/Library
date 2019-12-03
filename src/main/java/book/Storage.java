package book;

public class Storage {

    private String alley;
    private int shelf;

    public Storage(String alley, int shelf) {
        this.alley = alley;
        this.shelf = shelf;
    }

    @Override
    public String toString(){
        return alley + " " + shelf;
    }
}
