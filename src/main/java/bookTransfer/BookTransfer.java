package bookTransfer;

import book.*;

import java.sql.Date;

public class BookTransfer {

    private int reader_id, book_id;
    private Date creationdate, duedate;
    private AuthorBook authorBook;

    BookTransfer(){
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public AuthorBook getAuthorBook() {
        return authorBook;
    }

    public void setAuthorBook(AuthorBook authorBook) {
        this.authorBook = authorBook;
    }

    @Override
    public String toString() {
        //return authorBook +", " + authorBook.getBook().getBookshelf().toString() + ", ważne do: " + duedate.toString();
        return authorBook +", " + "ważne do: " + duedate.toString();
    }
}
