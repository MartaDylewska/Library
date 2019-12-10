
import gui.MFrame;
import gui.book.BookFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            //BookFrame window;
            //window = new BookFrame();
            MFrame window = new MFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });

    }
}
