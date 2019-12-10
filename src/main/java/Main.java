
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


    }
}
