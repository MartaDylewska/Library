package book;

import javax.swing.*;
import java.awt.*;

public class BookConfigurationApp {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            MyFrame window;
            window = new MyFrame();
            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setVisible(true);
        });
    }
}
