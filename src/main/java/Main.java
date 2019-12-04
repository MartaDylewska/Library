import card.CardDBServiceImpl;
import card.ICardDBService;
import city.City;
import city.CityDBServiceImpl;
import city.ICityDBService;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.util.List;

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
        User user1 = new User("Mieczysław","Lampacz","mielam@o2.pl","miele123","ul. Wrotycza 15", "02-257");
        IUserDBService userDB = new UserDBServiceImpl();
        //userDB.deleteUserFromDB(150015);
        //userDB.addUserInDB(user1);
        //User userRead = userDB.readUserFromDB(150006);
        //System.out.println(userRead);
        List<User> userList = userDB.getAllUsersFromDB();
        for (User u: userList)
            System.out.println(u);

        //---------ADDING CITY LIST TO DB-------------------------
        ICityDBService cityDBService = new CityDBServiceImpl();
        //List<City> cities = cityDBService.loadAllCitiesFromCsv("src/main/resources/kodyPocztowe.csv");

        //cityDBService.addCitiesToDB(cities);
        System.out.println(cityDBService.getCityName("24-100"));


    }
}
