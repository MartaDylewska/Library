package gui;

import config.Validation;
import gui.admin.*;
import gui.book.*;
import gui.bookTransfer.*;
import gui.event.*;
import gui.general.BackgroundPanel;
import gui.librarian.*;
import gui.login.LoginPanel;
import gui.reader.*;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MFrame extends JFrame {


    private ReaderAddPanel readerAddPanel;
    private ReaderUpdatePanel readerUpdatePanel;
    private ReaderDeletePanel readerDeletePanel;
    private LibrarianAddPanel librarianAddPanel;
    private LibrarianUpdatePanel librarianUpdatePanel;
    private LibrarianDeletePanel librarianDeletePanel;
    private AdminAddPanel adminAddPanel;
    private AdminDeletePanel adminDeletePanel;
    private AdminUpdatePanel adminUpdatePanel;
    private LoginPanel loginPanel;
    private ReaderEntryPanel readerEntryPanel;
    private LibrarianEntryPanel librarianEntryPanel;
    private AdminEntryPanel adminEntryPanel;
    private EventAddPanel eventAddPanel;
    private EventDeletePanel eventDeletePanel;
    private EventSignInPanel eventSignInPanel;
    private EventSeeAllPanel eventSeeAllPanel;
    private BookAddPanel bookAddPanel;
    private BookEditPanel bookEditPanel;
    private BookGetPanel bookGetPanel;
    private BookReservePanel bookReservePanel;
    private AuthorGetPanel authorGetPanel;
    private BookTransferPanel bookTransferPanel;
    private BookShowPanel bookShowPanel;

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

        BackgroundPanel backgroundPanel = new BackgroundPanel();
        add(backgroundPanel);


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
                                bookShowPanel = new BookShowPanel(readerEntryPanel);
                                bookShowPanel.getCardIdTxt().setText(readerEntryPanel.getCardNrLbl().getText());
                                add(bookShowPanel);
                                remove(readerEntryPanel);
                                repaint();
                                revalidate();

                                bookShowPanel.getReturnBtn().addActionListener(e2 -> {
                                    add(readerEntryPanel);
                                    remove(bookShowPanel);
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

                            librarianEntryPanel.getLendBook().addActionListener(e1 -> {
                                bookTransferPanel = new BookTransferPanel();
                                add(bookTransferPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                bookTransferPanel.getBack().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(bookTransferPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

//                            librarianEntryPanel.getAcceptReturnBtn().addActionListener(e1 -> {
//                                acceptReturnPanel = new AcceptReturnPanel();
//                                add(acceptReturnPanel);
//                                remove(librarianEntryPanel);
//                                repaint();
//                                revalidate();
//
//                                acceptReturnPanel.getReturnBtn().addActionListener(e2 -> {
//                                    add(librarianEntryPanel);
//                                    remove(acceptReturnPanel);
//                                    repaint();
//                                    revalidate();
//                                });
//                            });

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

                            librarianEntryPanel.getAddBook().addActionListener(e1 -> {
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

                            librarianEntryPanel.getFindAuthor().addActionListener(e1 -> {
                                authorGetPanel = new AuthorGetPanel();
                                add(authorGetPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                authorGetPanel.getBack().addActionListener(e2 -> {
                                    add(librarianEntryPanel);
                                    remove(authorGetPanel);
                                    repaint();
                                    revalidate();
                                });
                            });

                            librarianEntryPanel.getFindBook().addActionListener(e1 -> {
                                bookGetPanel = new BookGetPanel();
                                add(bookGetPanel);
                                remove(librarianEntryPanel);
                                repaint();
                                revalidate();

                                bookGetPanel.getEdit().addActionListener(e2 -> {
                                    if(!bookGetPanel.getResultList().isSelectionEmpty()){

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

