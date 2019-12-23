package gui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Auxiliary {
    public static final Font panelFont = new Font("Arial Narrow", Font.BOLD, 16);
    public static final Font panelPlainFont = new Font("Arial Narrow", Font.PLAIN, 16);
    public static final Font buttonFont = new Font("Arial Narrow", Font.BOLD, 16);
    public static Border blackBorder(){
        return BorderFactory.createLineBorder(Color.black, 1);
    }
    public static void setImageAsBackground(JPanel panel){
        JLabel myLabel = new JLabel();
        myLabel.setBounds(0,0, 700, 600);
        ImageIcon image = null;
        try {
            image = new ImageIcon("src/main/resources/library_small.png");
        } catch (Exception e) {
            System.out.println("Problem with picture library_small.png");
        }
        myLabel.setIcon(image);
        panel.add(myLabel);
    }

}
