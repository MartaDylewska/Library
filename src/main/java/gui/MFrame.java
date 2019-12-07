package gui;


import javax.swing.*;

public class MFrame extends JFrame {
    UserShowPanel userShowPanel;
    UserOperationsPanel userOperationsPanel;
    UserUpdatePanel userUpdatePanel;
    UserDeletePanel userDeletePanel;
    UsersShowAllPanel usersShowAllPanel;
    UserAddPanel userAddPanel;

   public MFrame(){
        setSize(700, 600);

        BookPanel bookPanel = new BookPanel();
        add(bookPanel);

//        BookAddPanel bookAddPanel = new BookAddPanel();
//        add(bookAddPanel);

//       userOperationsPanel = new UserOperationsPanel();
//       add(userOperationsPanel);
//
//       userOperationsPanel.getSearchBtn().addActionListener(e -> {
//
//           userShowPanel = new UserShowPanel();
//           add(userShowPanel);
//           remove(userOperationsPanel);
//           repaint();
//           revalidate();
//
//           userShowPanel.getReturnBtn().addActionListener(e1 -> {
//               add(userOperationsPanel);
//               remove(userShowPanel);
//               repaint();
//               revalidate();
//           });
//
//       });
//
//       userOperationsPanel.getUpdateBtn().addActionListener(e -> {
//           userUpdatePanel = new UserUpdatePanel();
//            add(userUpdatePanel);
//            remove(userOperationsPanel);
//            repaint();
//            revalidate();
//
//            userUpdatePanel.getReturnBtn().addActionListener(e1 -> {
//                add(userOperationsPanel);
//                remove(userUpdatePanel);
//                repaint();
//                revalidate();
//            });
//       });
//
//       userOperationsPanel.getDeleteBtn().addActionListener(e -> {
//           userDeletePanel = new UserDeletePanel();
//           add(userDeletePanel);
//           remove(userOperationsPanel);
//           repaint();
//           revalidate();
//
//           userDeletePanel.getReturnBtn().addActionListener(e1 -> {
//               add(userOperationsPanel);
//               remove(userDeletePanel);
//               repaint();
//               revalidate();
//           });
//       });
//
//       userOperationsPanel.getShowAllBtn().addActionListener(e -> {
//           usersShowAllPanel = new UsersShowAllPanel();
//           add(usersShowAllPanel);
//           remove(userOperationsPanel);
//           repaint();
//           revalidate();
//
//           usersShowAllPanel.getReturnBtn().addActionListener(e1 -> {
//               add(userOperationsPanel);
//               remove(usersShowAllPanel);
//               repaint();
//               revalidate();
//           });
//       });
//       userOperationsPanel.getAddBtn().addActionListener(e -> {
//           userAddPanel = new UserAddPanel();
//           add(userAddPanel);
//           remove(userOperationsPanel);
//           repaint();
//           revalidate();
//
//           userAddPanel.getReturnBtn().addActionListener(e1 -> {
//               add(userOperationsPanel);
//               remove(userAddPanel);
//               repaint();
//               revalidate();
//           });
//       });
    }
}
