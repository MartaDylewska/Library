package gui.general;

import gui.Auxiliary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustButton extends JButton {
    public CustButton(){
        setBackground(new Color(78,52,46,255));
        setFont(Auxiliary.buttonFont);
        setForeground(Color.WHITE);
        setFocusPainted(false);
        setFocusable(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.decode("#a1887f"));
            }
            @Override
            public void mouseClicked(MouseEvent e){setBackground(new Color(78,52,46,255));}
            @Override
            public void mouseExited(MouseEvent e) {setBackground(new Color(78,52,46,255));}
        });
    }
}
