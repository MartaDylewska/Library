import card.CardDBServiceImpl;
import card.ICardDBService;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

public class Main {
    public static void main(String[] args) {

        //--------OBSŁUGA ZAPYTAŃ ZWIĄZANYCH Z KARTAMI------------------------------------

        ICardDBService cardDB = new CardDBServiceImpl();
        //System.out.println("Lista wszystkich kart w bazie");
        //System.out.println(cardDB.getAllCardsFromDB());
        //System.out.println(cardDB.readCardFromDB(150012));
        //System.out.println(cardDB.getAllCardsFromDB());
        //cardDB.addCardInDB();
        //cardDB.deleteCardFromDB(150014);
        //-----Adding new user to db--------
        //User user1 = new User("Mieczysław","Lampacz","mielam@o2.pl","miele123","ul. Wrotycza 15 02-287 Warszawa");
        IUserDBService userDB = new UserDBServiceImpl();
        //userDB.addUserInDB(user1);


    }
}
