package gui.general;

import javax.swing.*;

public class BackgroundPanel extends JPanel {

    public BackgroundPanel() {

        JLabel myLabel = new JLabel();
        myLabel.setBounds(0,0, getWidth(), getHeight());

        ImageIcon image = null;

        try {
            image = new ImageIcon(this.getClass().getClassLoader().getResource("library.jpg"));
        } catch (Exception e) {
            System.out.println("Problem with picture.");
        }

        myLabel.setIcon(image);

        add(myLabel);
    }
}