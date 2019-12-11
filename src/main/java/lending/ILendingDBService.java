package lending;

import java.util.List;

public interface ILendingDBService {

    void addLending(Lending lending);
    void deleteLending(int idLending);
    List<Lending> showAllLendings();
    List<Lending> showLendingsForUser(int cardNr);
}
