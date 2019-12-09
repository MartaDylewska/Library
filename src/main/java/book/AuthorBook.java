package book;

import java.util.List;

public class AuthorBook {

    private List<Author> authorList;
    private List<Book> bookList;

    public AuthorBook(){

    }

    public List<Author> getAuthorList() {
        return authorList;
    }

    public void setAuthorList(List<Author> authorList) {
        this.authorList = authorList;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public String toString(){
        return authorList.toString() + ", " + bookList.toString();
    }
}
