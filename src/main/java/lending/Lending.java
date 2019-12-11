package lending;

import java.time.LocalDate;
import java.util.Objects;

public class Lending {

    private int idLending;
    private int reader_id;
    private int book_id;
    private LocalDate dateCreation;
    private LocalDate dateDue;

    public Lending(int idLending, int reader_id, int book_id, LocalDate dateCreation, LocalDate dateDue) {
        this.idLending = idLending;
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.dateCreation = dateCreation;
        this.dateDue = dateDue;
    }

    public Lending(int reader_id, int book_id, LocalDate dateCreation, LocalDate dateDue) {
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.dateCreation = dateCreation;
        this.dateDue = dateDue;
    }

    public Lending(){}

    public int getIdLending() {
        return idLending;
    }

    public void setIdLending(int idLending) {
        this.idLending = idLending;
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

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateDue() {
        return dateDue;
    }

    public void setDateDue(LocalDate dateDue) {
        this.dateDue = dateDue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Lending)) return false;
        Lending lending = (Lending) o;
        return getIdLending() == lending.getIdLending() &&
                getReader_id() == lending.getReader_id() &&
                getBook_id() == lending.getBook_id() &&
                Objects.equals(getDateCreation(), lending.getDateCreation()) &&
                Objects.equals(getDateDue(), lending.getDateDue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdLending(), getReader_id(), getBook_id(), getDateCreation(), getDateDue());
    }

    @Override
    public String toString() {
        return idLending + " " +
                dateCreation.toString();
    }
}
