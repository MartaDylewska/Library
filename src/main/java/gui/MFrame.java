package gui;

import config.Validation;
import gui.admin.*;
import gui.book.*;
import gui.event.EventAddPanel;
import gui.event.EventDeletePanel;
import gui.event.EventSeeAllPanel;
import gui.event.EventSignInPanel;
import gui.lending.AcceptReturnPanel;
import gui.lending.LendingShowPanel;
import gui.librarian.*;
import gui.login.LoginPanel;
import gui.poster.PosterOperationsPanel;
import gui.reader.*;
import gui.reservation.ReservationShowPanel;
import gui.user.*;
import images.IPosterDBService;
import images.PosterDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;
import gui.librarian.LibrarianAddPanel;
import gui.librarian.LibrarianOperationsPanel;
import gui.librarian.LibrarianShowPanel;
import gui.librarian.LibrarianUpdatePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
    EventAddPanel eventAddPanel;
    EventDeletePanel eventDeletePanel;
    EventSignInPanel eventSignInPanel;
    EventSeeAllPanel eventSeeAllPanel;
    BookAddPanel bookAddPanel;
    BookDeletePanel bookDeletePanel;
    BookEditPanel bookEditPanel;
    BookGetPanel bookGetPanel;
    BookReservePanel bookReservePanel;
    ReservationShowPanel reservationShowPanel;
    LendingShowPanel lendingShowPanel;
    AcceptReturnPanel acceptReturnPanel;
   // ImagePanel imagePanel;


    public MFrame() {
        setSize(700, 600);

        setTitle("Biblioteka");
        setResizable(false);
        setLocationRelativeTo(null);


//----------------login panel----------------

        loginPanel = new LoginPanel();

        add(loginPanel);
        loginPanel.setBackground(new Color(0,0,0,0));

        addLoginKeyListener();
        addRegisterKeyListener();

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

                            readerEntryPanel.getJoinEventBtn().addActionListener(e1 -> {
                                eventSignInPanel = new EventSignInPanel();
                                eventSignInPanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                add(eventSignInPanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                eventSignInPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(readerEntryPanel);
                                    remove(eventSignInPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            readerEntryPanel.getLendingsBtn().addActionListener(e1 -> {
                                lendingShowPanel = new LendingShowPanel(readerEntryPanel);
                                lendingShowPanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                add(lendingShowPanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                lendingShowPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(readerEntryPanel);
                                    remove(lendingShowPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            readerEntryPanel.getCheckEventBtn().addActionListener(e1 -> {
                                eventSeeAllPanel = new EventSeeAllPanel(readerEntryPanel);
                                eventSeeAllPanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                add(eventSeeAllPanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                eventSeeAllPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(readerEntryPanel);
                                    remove(eventSeeAllPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

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

                            /*readerEntryPanel.getLendingsBtn().addActionListener(e1 -> {
                                reservationShowPanel = new ReservationShowPanel();
                                reservationShowPanel.getCardIdLbl().se
                            });*/

                            readerEntryPanel.getDoBookingBtn().addActionListener(e1 -> {
                                bookReservePanel = new BookReservePanel();
                                bookReservePanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                add(bookReservePanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                bookReservePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(readerEntryPanel);
                                    remove(bookReservePanel);
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

                            librarianEntryPanel.getAddEventBtn().addActionListener(e1 -> {
                                eventAddPanel = new EventAddPanel();
                                add(eventAddPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                eventAddPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(eventAddPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getConfirmReservationBtn().addActionListener(e1 -> {
                                reservationShowPanel = new ReservationShowPanel();
                                add(reservationShowPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                reservationShowPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(reservationShowPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getAcceptReturnBtn().addActionListener(e1 -> {
                                acceptReturnPanel = new AcceptReturnPanel();
                                add(acceptReturnPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                acceptReturnPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(acceptReturnPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getDeleteEventBtn().addActionListener(e1 -> {
                                eventDeletePanel = new EventDeletePanel();
                                add(eventDeletePanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                eventDeletePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(eventDeletePanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getAddBookBtn().addActionListener(e1 -> {
                                bookAddPanel = new BookAddPanel();
                                add(bookAddPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                bookAddPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(bookAddPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getDeleteBookBtn().addActionListener(e1 -> {
                                bookDeletePanel = new BookDeletePanel();
                                add(bookDeletePanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                bookDeletePanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(bookDeletePanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getUpdateBookBtn().addActionListener(e1 -> {
                                bookGetPanel = new BookGetPanel();
                                add(bookGetPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                bookGetPanel.getEdit().addActionListener(e2 -> {
                                    if(bookGetPanel.getResultList().isSelectionEmpty() == false){

                                    bookEditPanel = new BookEditPanel(bookGetPanel);
                                    add(bookEditPanel);
                                    remove(bookGetPanel);
                                    repaint();
                                    revalidate();

                                    bookEditPanel.getReturnBtn().addActionListener(e3 -> {
                                        add(bookGetPanel);
                                        remove(bookEditPanel);
                                        repaint();
                                        revalidate();
                                    });
                                }
                                    else {
                                        JOptionPane.showMessageDialog(this,"Książka nie została wybrana");
                                    }});


                                bookGetPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(bookGetPanel);
                                    repaint();
                                    revalidate();
                                });
                            });


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



//

    }
    private void addLoginKeyListener() {
        loginPanel.getLoginBtn().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    loginPanel.getLoginBtn().doClick();}
            @Override
            public void keyReleased(KeyEvent e) {
            }});
        }

    private void addRegisterKeyListener() {
        loginPanel.getRegisterBtn().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    loginPanel.getRegisterBtn().doClick();}
            @Override
            public void keyReleased(KeyEvent e) {
            }});
    }



    }

