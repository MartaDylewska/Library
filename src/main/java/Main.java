import card.CardDBServiceImpl;
import card.ICardDBService;

public class Main {
    public static void main(String[] args) {

        //--------OBSŁUGA ZAPYTAŃ ZWIĄZANYCH Z KARTAMI------------------------------------

        ICardDBService cardDB = new CardDBServiceImpl();
        System.out.println("Lista wszystkich kart w bazie");
        System.out.println(cardDB.getAllCardsFromDB());
        System.out.println(cardDB.readCardFromDB(150012));
        //System.out.println(cardDB.getAllCardsFromDB());
        //cardDB.addCardInDB();
        //cardDB.deleteCardFromDB(150014);


    }
}
