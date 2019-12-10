package gui;

import config.Validation;
import gui.admin.*;
import gui.librarian.*;
import gui.login.LoginPanel;
import gui.poster.PosterOperationsPanel;
import gui.reader.*;
import gui.user.*;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;
import gui.librarian.LibrarianAddPanel;
import gui.librarian.LibrarianOperationsPanel;
import gui.librarian.LibrarianShowPanel;
import gui.librarian.LibrarianUpdatePanel;

import javax.swing.*;
import java.awt.*;

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
    ReaderEntryPanel readerEntryPanel;
    LibrarianEntryPanel librarianEntryPanel;
    AdminEntryPanel adminEntryPanel;
    PosterOperationsPanel posterOperationsPanel;


    public MFrame() {
        setSize(700, 600);

        setTitle("Biblioteka");
        setResizable(false);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int)screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        int posX = width / 2 - getWidth() / 2;
        int posY = height / 2 - getHeight() / 2;
        setLocation(posX, posY);
/*
        posterOperationsPanel = new PosterOperationsPanel();
        add(posterOperationsPanel);*/


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

        loginPanel.getLoginBtn().addActionListener(e -> {
            String cardIdTxt = loginPanel.getCardNrTxt().getText();
            String passTxt = loginPanel.getPasswordToString(loginPanel.getPassTxt());
            if (Validation.checkIfInteger(cardIdTxt)) {
                if (Validation.checkUserExists(Integer.parseInt(cardIdTxt))) {
                    IUserDBService userDBService = new UserDBServiceImpl();
                    User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt));
                    if (Validation.checkPassOk(user, passTxt)) {
                        if (Validation.checkIfReader(user)) {
                            //---------------------CZYTELNIK---------------------------
                            readerEntryPanel = new ReaderEntryPanel();
                            readerEntryPanel.setCardNrLbl(loginPanel.getCardNrTxt().getText());
                            readerEntryPanel.setNameLbl(user.getFirstName() + " " + user.getLastName());
                            add(readerEntryPanel);
                            remove(loginPanel);
                            repaint();
                            revalidate();

                            //operacje dotyczące reader entry panel
                            readerEntryPanel.getUpdateBtn().addActionListener(e2 -> {
                                readerUpdatePanel = new ReaderUpdatePanel();
                                readerUpdatePanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                readerUpdatePanel.getSearchReaderBtn().doClick();
                                add(readerUpdatePanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                readerUpdatePanel.getReturnBtn().addActionListener(e3 -> {
                                    add(readerEntryPanel);
                                    remove(readerUpdatePanel);
                                    repaint();
                                    revalidate();
                                });

                            });

                            //tutaj trzeba dodać doBooking (zrób rezerwację), oraz lendings (podejrzyj wypożyczenia)
                            //ja dodam eventy dzisiaj

                            readerEntryPanel.getReturnBtn().addActionListener(e1 -> {
                                loginPanel.setCardNrTxt("");
                                loginPanel.setPassTxt("");
                                add(loginPanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();
                            });
                        } else if (Validation.checkIfLibrarian(user))
                        //---------------------BIBILOTEKARZ---------------------------
                        {
                            librarianEntryPanel = new LibrarianEntryPanel();
                            librarianEntryPanel.setCardNrLbl(loginPanel.getCardNrTxt().getText());
                            librarianEntryPanel.setNameLbl(user.getFirstName() + " " + user.getLastName());
                            add(librarianEntryPanel);
                            remove(loginPanel);
                            repaint();
                            revalidate();

                            librarianEntryPanel.getDeleteReaderProfileBtn().addActionListener(e1 -> {
                                readerDeletePanel = new ReaderDeletePanel();
                                add(readerDeletePanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                readerDeletePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(readerDeletePanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getReturnBtn().addActionListener(e1 -> {
                                loginPanel.setCardNrTxt("");
                                loginPanel.setPassTxt("");
                                add(loginPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();
                            });
                        } else if(Validation.checkIfAdmin(user)){
                            //---------------------ADMINISTRATOR---------------------------
                            adminEntryPanel = new AdminEntryPanel();
                            adminEntryPanel.setCardNrLbl(loginPanel.getCardNrTxt().getText());
                            adminEntryPanel.setNameLbl(user.getFirstName() + " " + user.getLastName());
                            add(adminEntryPanel);
                            remove(loginPanel);
                            repaint();
                            revalidate();
                            //dodawanie bibiliotekarza
                            adminEntryPanel.getAddLibrarianBtn().addActionListener(e1 -> {
                                librarianAddPanel = new LibrarianAddPanel();
                                add(librarianAddPanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                librarianAddPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(librarianAddPanel);
                                    repaint();
                                    revalidate();
                                });
                            });
                            //usuwanie bibiliotekarza
                            adminEntryPanel.getDeleteLibrarianBtn().addActionListener(e1 -> {
                                librarianDeletePanel = new LibrarianDeletePanel();
                                add(librarianDeletePanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                librarianDeletePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(librarianDeletePanel);
                                    repaint();
                                    revalidate();
                                });
                            });
                            //update bibliotekarza
                            adminEntryPanel.getUpdateLibrarianBtn().addActionListener(e1 -> {
                                librarianUpdatePanel = new LibrarianUpdatePanel();
                                add(librarianUpdatePanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                librarianUpdatePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(librarianUpdatePanel);
                                    repaint();
                                    revalidate();
                                });
                            });
                            //dodawanie administratora
                            adminEntryPanel.getAddAdminBtn().addActionListener(e1 -> {
                                adminAddPanel = new AdminAddPanel();
                                add(adminAddPanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                adminAddPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(adminAddPanel);
                                    repaint();
                                    revalidate();
                                });
                            });
                            //usuwanie administratora
                            adminEntryPanel.getDeleteAdminBtn().addActionListener(e1 -> {
                                adminDeletePanel = new AdminDeletePanel();
                                add(adminDeletePanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                adminDeletePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(adminDeletePanel);
                                    repaint();
                                    revalidate();
                                });
                            });
                            //update administratora
                            adminEntryPanel.getUpdateAdminBtn().addActionListener(e1 -> {
                                adminUpdatePanel = new AdminUpdatePanel();
                                add(adminUpdatePanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();

                                adminUpdatePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(adminEntryPanel);
                                    remove(adminUpdatePanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            adminEntryPanel.getReturnBtn().addActionListener(e1 -> {
                                loginPanel.setCardNrTxt("");
                                loginPanel.setPassTxt("");
                                add(loginPanel);
                                remove(adminEntryPanel);
                                repaint();
                                revalidate();
                            });

                        } else
                        {
                            JOptionPane.showMessageDialog(this, "Brak użytkownika w bazie");
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Niepoprawnie wprowadzone hasło");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Użytkownik o tym numerze karty nie istnieje w systemie");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Niepoprawny numer karty");
            }
        });


    }
}
