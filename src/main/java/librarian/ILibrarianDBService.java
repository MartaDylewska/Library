package librarian;

import java.time.LocalDate;
import java.util.List;

public interface ILibrarianDBService {
    void addLibrarianInDB(Librarian librarian);
    void deleteLibrarianFromDB(int idUser);
    Librarian readLibrarianFromDB(int idCard);
    List<Librarian> getAllLibrariansFromDB();
    void updateLibrarianInDB(int userId, String salary, LocalDate employmentDate);

}
