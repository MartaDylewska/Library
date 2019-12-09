
import gui.MFrame;
import gui.book.BookFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            BookFrame window;
            window = new BookFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    //------------TESTOWANIE OBRAZKÓW-----------------
        //IPosterDBService posterDB = new PosterDBServiceImpl();
        //posterDB.addImage("C:\\Users\\e495405\\Desktop\\Baza danych zdjęcia\\poster2.png");
      /*  //--------OBSŁUGA ZAPYTAŃ ZWIĄZANYCH Z KARTAMI------------------------------------

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
*/

    }
}
