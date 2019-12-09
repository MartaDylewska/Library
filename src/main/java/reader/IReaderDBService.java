package reader;

<<<<<<< HEAD
import librarian.Librarian;
=======
import user.User;
>>>>>>> books

import java.util.List;

public interface IReaderDBService {
    void addReaderInDB(int idUser);
    void deleteReaderFromDB(int idUser);
    Reader readReaderFromDB(int idUser);
    List<Reader> getAllReadersFromDB();
    void chooseBook();
    void makeReservation();
    void seeListOfBorrows();
}
