
import card.CardDBServiceImpl;
import card.ICardDBService;
import city.City;
import city.CityDBServiceImpl;
import city.ICityDBService;
import gui.MFrame;
import images.IPosterDBService;
import images.PosterDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MFrame window;
            window = new MFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    //------------TESTOWANIE OBRAZKÓW-----------------
        //IPosterDBService posterDB = new PosterDBServiceImpl();
        //posterDB.addImage("C:\\Users\\e495405\\Desktop\\Baza danych zdjęcia\\poster2.png");


    }
}
