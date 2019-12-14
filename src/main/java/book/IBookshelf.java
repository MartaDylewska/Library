package book;

public interface IBookshelf {

    int getBookshelf(String alley, String bookstand, int shelf);
    String[] getAlleys();
    String[] getBookstands();
    String[] getShelves();
}
