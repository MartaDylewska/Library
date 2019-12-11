package reservation;

import java.time.LocalDate;
import java.util.Objects;

public class Reservation {
    private int idReservation;
    private int reader_id;
    private int book_id;
    private LocalDate dateCreation;

    public Reservation(int idReservation, int reader_id, int book_id, LocalDate dateCreation) {
        this.idReservation = idReservation;
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.dateCreation = dateCreation;
    }

    public Reservation(int reader_id, int book_id, LocalDate dateCreation) {
        this.reader_id = reader_id;
        this.book_id = book_id;
        this.dateCreation = dateCreation;
    }

    public Reservation(){}

    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return getIdReservation() == that.getIdReservation() &&
                getReader_id() == that.getReader_id() &&
                getBook_id() == that.getBook_id() &&
                Objects.equals(getDateCreation(), that.getDateCreation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdReservation(), getReader_id(), getBook_id(), getDateCreation());
    }

    @Override
    public String toString() {
        return "Rezerwacja" + idReservation;
    }
}
