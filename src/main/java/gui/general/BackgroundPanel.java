package gui.general;

import javax.swing.*;

public class BackgroundPanel extends JPanel {

    public BackgroundPanel() {
        setLayout(null);
        JLabel myLabel = new JLabel();
        myLabel.setBounds(0,0, 700, 600);
        ImageIcon image = null;

        try {
            image = new ImageIcon("src/main/resources/library.jpg");
        } catch (Exception e) {
            System.out.println("Problem with picture library.jpg.");
        }

        myLabel.setIcon(image);
        add(myLabel);
    }
}