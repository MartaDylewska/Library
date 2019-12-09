package gui;


import gui.admin.*;
import gui.librarian.*;
import gui.login.LoginPanel;
import gui.reader.*;
import gui.user.*;

import javax.swing.*;

public class MFrame extends JFrame {
    UserShowPanel userShowPanel;
    UserOperationsPanel userOperationsPanel;
    UserUpdatePanel userUpdatePanel;
    UserDeletePanel userDeletePanel;
    UsersShowAllPanel usersShowAllPanel;
    UserAddPanel userAddPanel;
    ReaderOperationsPanel readerOperationsPanel;
    ReaderAddPanel readerAddPanel;
    ReaderShowPanel readerShowPanel;
    ReaderUpdatePanel readerUpdatePanel;
    ReaderDeletePanel readerDeletePanel;
    ReadersShowAllPanel readersShowAllPanel;
    LibrarianOperationsPanel librarianOperationsPanel;
    LibrarianAddPanel librarianAddPanel;
    LibrarianShowPanel librarianShowPanel;
    LibrarianUpdatePanel librarianUpdatePanel;
    LibrarianDeletePanel librarianDeletePanel;
    LibrariansShowAllPanel librariansShowAllPanel;
    AdminOperationsPanel adminOperationsPanel;
    AdminShowPanel adminShowPanel;
    AdminAddPanel adminAddPanel;
    AdminDeletePanel adminDeletePanel;
    AdminUpdatePanel adminUpdatePanel;
    AdminsShowAllPanel adminsShowAllPanel;
    LoginPanel loginPanel;


   public MFrame(){
        setSize(700, 600);
//----------------login panel----------------
        loginPanel = new LoginPanel();
        add(loginPanel);

        loginPanel.getRegisterBtn().addActionListener(e -> {
            readerAddPanel = new ReaderAddPanel();
            add(readerAddPanel);
            remove(loginPanel);
            repaint();
            revalidate();

            readerAddPanel.getReturnBtn().addActionListener(e1 -> {
                String cardNo = readerAddPanel.getCardIdTxt().getText();
                StringBuilder pass = new StringBuilder();
                for (char c : readerAddPanel.getPassField().getPassword())
                    pass.append(c);
                loginPanel.setCardNrTxt(cardNo);
                loginPanel.setPassTxt(pass.toString());
                add(loginPanel);
                remove(readerAddPanel);
                repaint();
                revalidate();
            });
        });

//-------------ADMIN INTERFACE-----------------------------
/*
        adminOperationsPanel = new AdminOperationsPanel();
        add(adminOperationsPanel);

       adminOperationsPanel.getSearchBtn().addActionListener(e -> {
           adminShowPanel = new AdminShowPanel();
           add(adminShowPanel);
           remove(adminOperationsPanel);
           repaint();
           revalidate();

           adminShowPanel.getReturnBtn().addActionListener(e1 -> {
               add(adminOperationsPanel);
               remove(adminShowPanel);
               repaint();
               revalidate();
           });
       });


       adminOperationsPanel.getAddBtn().addActionListener(e -> {
           adminAddPanel = new AdminAddPanel();
           add(adminAddPanel);
           remove(adminOperationsPanel);
           repaint();
           revalidate();

           adminAddPanel.getReturnBtn().addActionListener(e1 -> {
               add(adminOperationsPanel);
               remove(adminAddPanel);
               repaint();
               revalidate();
           });
       });

       adminOperationsPanel.getDeleteBtn().addActionListener(e -> {
           adminDeletePanel = new AdminDeletePanel();
           add(adminDeletePanel);
           remove(adminOperationsPanel);
           repaint();
           revalidate();

           adminDeletePanel.getReturnBtn().addActionListener(e1 -> {
               add(adminOperationsPanel);
               remove(adminDeletePanel);
               repaint();
               revalidate();
           });
       });

       adminOperationsPanel.getUpdateBtn().addActionListener(e -> {
           adminUpdatePanel = new AdminUpdatePanel();
           add(adminUpdatePanel);
           remove(adminOperationsPanel);
           repaint();
           revalidate();

           adminUpdatePanel.getReturnBtn().addActionListener(e1 -> {
               add(adminOperationsPanel);
               remove(adminUpdatePanel);
               repaint();
               revalidate();
           });
       });

       adminOperationsPanel.getShowAllBtn().addActionListener(e -> {
           adminsShowAllPanel = new AdminsShowAllPanel();
           add(adminsShowAllPanel);
           remove(adminOperationsPanel);
           repaint();
           revalidate();

           adminsShowAllPanel.getReturnBtn().addActionListener(e1 -> {
               add(adminOperationsPanel);
               remove(adminsShowAllPanel);
               repaint();
               revalidate();
           });
       });

*/


//-------------LIBRARIAN INTERFACE--------------------------
/*
        librarianOperationsPanel = new LibrarianOperationsPanel();
        add(librarianOperationsPanel);

       librarianOperationsPanel.getAddBtn().addActionListener(e -> {
           librarianAddPanel = new LibrarianAddPanel();
           add(librarianAddPanel);
           remove(librarianOperationsPanel);
           repaint();
           revalidate();

           librarianAddPanel.getReturnBtn().addActionListener(e1 -> {
               add(librarianOperationsPanel);
               remove(librarianAddPanel);
               repaint();
               revalidate();
           });
       });

       librarianOperationsPanel.getSearchBtn().addActionListener(e -> {
           librarianShowPanel = new LibrarianShowPanel();
           add(librarianShowPanel);
           remove(librarianOperationsPanel);
           repaint();
           revalidate();

           librarianShowPanel.getReturnBtn().addActionListener(e1 -> {
               add(librarianOperationsPanel);
               remove(librarianShowPanel);
               repaint();
               revalidate();
           });
       });

       librarianOperationsPanel.getUpdateBtn().addActionListener(e -> {
           librarianUpdatePanel = new LibrarianUpdatePanel();
           add(librarianUpdatePanel);
           remove(librarianOperationsPanel);
           repaint();
           revalidate();

           librarianUpdatePanel.getReturnBtn().addActionListener(e1 -> {
               add(librarianOperationsPanel);
               remove(librarianUpdatePanel);
               repaint();
               revalidate();
           });
       });

       librarianOperationsPanel.getDeleteBtn().addActionListener(e -> {
           librarianDeletePanel = new LibrarianDeletePanel();
           add(librarianDeletePanel);
           remove(librarianOperationsPanel);
           repaint();
           revalidate();

           librarianDeletePanel.getReturnBtn().addActionListener(e1 -> {
               add(librarianOperationsPanel);
               remove(librarianDeletePanel);
               repaint();
               revalidate();
           });
       });

       librarianOperationsPanel.getShowAllBtn().addActionListener(e -> {
           librariansShowAllPanel = new LibrariansShowAllPanel();
           add(librariansShowAllPanel);
           remove(librarianOperationsPanel);
           repaint();
           revalidate();

           librariansShowAllPanel.getReturnBtn().addActionListener(e1 -> {
               add(librarianOperationsPanel);
               remove(librariansShowAllPanel);
               repaint();
               revalidate();
           });
       });
*/

//-------------READER INTERFACE-----------------------------
/*
       readerOperationsPanel = new ReaderOperationsPanel();
       add(readerOperationsPanel);

       readerOperationsPanel.getAddBtn().addActionListener(e -> {
           readerAddPanel = new ReaderAddPanel();
           add(readerAddPanel);
           remove(readerOperationsPanel);
           repaint();
           revalidate();

           readerAddPanel.getReturnBtn().addActionListener(e1 -> {
               add(readerOperationsPanel);
               remove(readerAddPanel);
               repaint();
               revalidate();
           });
       });

       readerOperationsPanel.getSearchBtn().addActionListener(e -> {

           readerShowPanel = new ReaderShowPanel();
           add(readerShowPanel);
           remove(readerOperationsPanel);
           repaint();
           revalidate();

           readerShowPanel.getReturnBtn().addActionListener(e1 -> {
               add(readerOperationsPanel);
               remove(readerShowPanel);
               repaint();
               revalidate();
           });

       });

       readerOperationsPanel.getUpdateBtn().addActionListener(e -> {
           readerUpdatePanel = new ReaderUpdatePanel();
           add(readerUpdatePanel);
           remove(readerOperationsPanel);
           repaint();
           revalidate();

           readerUpdatePanel.getReturnBtn().addActionListener(e1 -> {
               add(readerOperationsPanel);
               remove(readerUpdatePanel);
               repaint();
               revalidate();
           });
       });

       readerOperationsPanel.getDeleteBtn().addActionListener(e -> {
           readerDeletePanel = new ReaderDeletePanel();
           add(readerDeletePanel);
           remove(readerOperationsPanel);
           repaint();
           revalidate();

           readerDeletePanel.getReturnBtn().addActionListener(e1 -> {
               add(readerOperationsPanel);
               remove(readerDeletePanel);
               repaint();
               revalidate();
           });
       });

       readerOperationsPanel.getShowAllBtn().addActionListener(e -> {
           readersShowAllPanel = new ReadersShowAllPanel();
           add(readersShowAllPanel);
           remove(readerOperationsPanel);
           repaint();
           revalidate();

           readersShowAllPanel.getReturnBtn().addActionListener(e1 -> {
               add(readerOperationsPanel);
               remove(readersShowAllPanel);
               repaint();
               revalidate();
           });
       });
*/

   /*     BookPanel bookPanel = new BookPanel();
        add(bookPanel);*/
//        BookAddPanel bookAddPanel = new BookAddPanel();
//        add(bookAddPanel);

/*       userOperationsPanel = new UserOperationsPanel();
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

       userOperationsPanel.getDeleteBtn().addActionListener(e -> {
           userDeletePanel = new UserDeletePanel();
           add(userDeletePanel);
           remove(userOperationsPanel);
           repaint();
           revalidate();

           userDeletePanel.getReturnBtn().addActionListener(e1 -> {
               add(userOperationsPanel);
               remove(userDeletePanel);
               repaint();
               revalidate();
           });
       });

       userOperationsPanel.getShowAllBtn().addActionListener(e -> {
           usersShowAllPanel = new UsersShowAllPanel();
           add(usersShowAllPanel);
           remove(userOperationsPanel);
           repaint();
           revalidate();

           usersShowAllPanel.getReturnBtn().addActionListener(e1 -> {
               add(userOperationsPanel);
               remove(usersShowAllPanel);
               repaint();
               revalidate();
           });
       });
       userOperationsPanel.getAddBtn().addActionListener(e -> {
           userAddPanel = new UserAddPanel();
           add(userAddPanel);
           remove(userOperationsPanel);
           repaint();
           revalidate();

           userAddPanel.getReturnBtn().addActionListener(e1 -> {
               add(userOperationsPanel);
               remove(userAddPanel);
               repaint();
               revalidate();
           });
       });*/
    }
}
