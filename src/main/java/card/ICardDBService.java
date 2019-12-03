package card;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ICardDBService {

    void addCardInDB() ;
    void deleteCardFromDB(int idCard);
    Card readCardFromDB(int idCard);
    Card readLastCardFromDB();
    List<Card> getAllCardsFromDB();

}
