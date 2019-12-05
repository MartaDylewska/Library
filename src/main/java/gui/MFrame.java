package gui;


import javax.swing.*;

public class MFrame extends JFrame {
    UserShowPanel userShowPanel;
    UserOperationsPanel userOperationsPanel;
    UserUpdatePanel userUpdatePanel;

   public MFrame(){
        setSize(700, 600);
        //UserPanel userPanel = new UserPanel();
        //add(userPanel);

       userOperationsPanel = new UserOperationsPanel();
       add(userOperationsPanel);

       userOperationsPanel.getSearchBtn().addActionListener(e -> {

           userShowPanel = new UserShowPanel();
           add(userShowPanel);
           remove(userOperationsPanel);
           repaint();
           revalidate();

           userShowPanel.getReturnBtn().addActionListener(e1 -> {
               add(userOperationsPanel);
               remove(userShowPanel);
               repaint();
               revalidate();
           });

       });

       userOperationsPanel.getUpdateBtn().addActionListener(e -> {
           userUpdatePanel = new UserUpdatePanel();
            add(userUpdatePanel);
            remove(userOperationsPanel);
            repaint();
            revalidate();

            userUpdatePanel.getReturnBtn().addActionListener(e1 -> {
                add(userOperationsPanel);
                remove(userUpdatePanel);
                repaint();
                revalidate();
            });
       });
    }
}
