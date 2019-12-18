package gui.general;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {

    public MyButton(boolean option){

        if(option){
            setBackground(Color.blue);
            setForeground(Color.white);
        } else {
            setForeground(Color.blue);
            setBorder(null);
            setContentAreaFilled(false);
        }
    }

}
