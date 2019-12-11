package reservation;

import java.util.List;

public interface IReservationDBService {

    void addReservation(Reservation reservation);
    void deleteReservation(int idReservation);
    List<Reservation> showAllReservations();
    List<Reservation> showReservationForUser(int cardNr);
}
