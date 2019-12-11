package gui.reservation;

import book.Book;
import book.BookService;
import book.IBook;
import config.Validation;
import event.EventDBServiceImpl;
import event.IEventDBService;
import images.IPosterDBService;
import images.PosterDBServiceImpl;
import lending.ILendingDBService;
import lending.Lending;
import lending.LendingDBServiceImpl;
import reader.IReaderDBService;
import reader.ReaderDBServiceImpl;
import reservation.IReservationDBService;
import reservation.Reservation;
import reservation.ReservationDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

public class ReservationShowPanel extends JPanel {
    private JComboBox reservationChooserBx;
    private JButton viewReservationsBtn, makeLendingBtn, returnBtn;
    private JLabel titleLbl, dateLbl, posterLbl, shortDescLbl, posterShowLbl;
    private JTextField titleTxt, dateTxt;
    private JTextArea shortDescTxt;
    private JLabel cardIdLbl;
    private JTextField cardIdTxt;

    int fieldLength = 200;
    int deltaY = 20;
    Reservation selectedReservation = null;

    private IPosterDBService posterDBService = new PosterDBServiceImpl();
    private IEventDBService eventDBService = new EventDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();
    private IReservationDBService reservationDBService = new ReservationDBServiceImpl();
    private IBook bookDBService = new BookService();
    private ILendingDBService lendingDBService = new LendingDBServiceImpl();

    public ReservationShowPanel() {
        setLayout(null);
        createItems();
        addItems();
        setCompVisibility(false);
        setComponentsEditability(false);
        addActionViewReservationBtn();
        addActionMakeLendingBtn();
    }

    private void addItems() {
        add(reservationChooserBx);
        add(dateLbl);
        add(dateTxt);
        add(posterLbl);
        add(posterShowLbl);
        add(shortDescLbl);
        add(shortDescTxt);
        add(titleLbl);
        add(titleTxt);
        add(viewReservationsBtn);
        add(returnBtn);
        add(makeLendingBtn);
        add(cardIdLbl);
        add(cardIdTxt);
    }

    private void createItems() {
        createReservationChooserBx();
        createDateLbl();
        createDateTxt();
        createPosterLbl();
        createPosterShowLbl();
        createShortDescLbl();
        createShortDescTxt();
        createTitleLbl();
        createTitleTxt();
        createViewReservationsBtn();
        createReturnBtn();
        createMakeLendingBtn();
        createCardIdLbl();
        createCardIdTxt();
    }

    private void addActionMakeLendingBtn() {
        makeLendingBtn.addActionListener(e -> {
            List<Reservation> reservationList = reservationDBService.showReservationForUser(Integer.parseInt(cardIdTxt.getText()));
            if (reservationChooserBx.getSelectedIndex() != 0) {
                //setCompVisibility(true);
                int book_id = 0;
                int reader_id = 0;
                for (Reservation reservation : reservationList) {
                    if ((reservation.getIdReservation())== Integer.parseInt(reservationChooserBx.getSelectedItem().toString())) {
                        selectedReservation = reservation;
                        book_id = reservation.getBook_id();
                        reader_id = reservation.getReader_id();
                    }
                }
                if (book_id != 0 && reader_id != 0) {
                    LocalDate creationDate = LocalDate.now();
                    LocalDate dueDate = creationDate.plusDays(30);
                    Lending lending = new Lending();
                    lending.setBook_id(book_id);
                    lending.setDateCreation(creationDate);
                    lending.setDateDue(dueDate);
                    lending.setReader_id(reader_id);
                    lendingDBService.addLending(lending);
                    Book book = bookDBService.getBook(book_id);
                    //zmienić na setBookAvailability
                    bookDBService.setBookAvailability(book_id,false);
                    reservationChooserBx.removeItem(selectedReservation.getIdReservation());
                    reservationDBService.deleteReservation(selectedReservation.getIdReservation());

                    JOptionPane.showMessageDialog(this, "Książka została wypożyczona");
                }
                else{
                    JOptionPane.showMessageDialog(this,"Id obiektu to null");
                }
            }

        });
    }

    private void addActionViewReservationBtn() {
        viewReservationsBtn.addActionListener(e -> {
            if(!Validation.checkIfInteger(cardIdTxt.getText())) {JOptionPane.showMessageDialog(this,"Niepoprawny numer");}

            else if(!Validation.checkUserExists(Integer.parseInt(cardIdTxt.getText()))) {
                JOptionPane.showMessageDialog(this,"Użytkownika brak w systemie");
            }
            else {
                IUserDBService userDBService = new UserDBServiceImpl();
                User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
                if (!Validation.checkIfReader(user)){
                    JOptionPane.showMessageDialog(this,"Użytkownik nie jest czytelnikiem");
                }
                else {
                    makeLendingBtn.setVisible(true);
                    List<Reservation> reservationList = reservationDBService.showReservationForUser(Integer.parseInt(cardIdTxt.getText()));
                    for (Reservation r : reservationList) {
                        reservationChooserBx.addItem(r.getIdReservation());
                    }
                    reservationChooserBx.setVisible(true);
                }}

//to już do przycisku "zatwierdź"
           /* if (reservationChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                makeLendingBtn.setVisible(true);
                for (Reservation reservation : reservationList) {
                    if ((String.valueOf(reservation.getIdReservation()).equals(reservationChooserBx.getSelectedItem()))) {
                        selectedReservation = reservation;
                    }
                }

            } else {
                JOptionPane.showMessageDialog(this, "Proszę wybrać wydarzenie");
            }*/
        });
    }

    private void createCardIdLbl() {
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(30, 30, 100, 20);
    }

    private void createCardIdTxt() {
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(150, 30, 100, 20);
    }

    private void createMakeLendingBtn() {
        makeLendingBtn = new JButton();
        makeLendingBtn.setText("Wypożycz");
        makeLendingBtn.setBounds(450, 150, 200, 50);
        makeLendingBtn.setVisible(false);
    }

    private void createViewReservationsBtn() {
        viewReservationsBtn = new JButton();
        viewReservationsBtn.setText("Pokaż rezerwacje");
        viewReservationsBtn.setBounds(450, 10, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 50);
    }

    private void createReservationChooserBx() {
        reservationChooserBx = new JComboBox();
        //List<Reservation> reservationList = reservationDBService.showReservationForUser(Integer.parseInt(cardIdTxt.getText()));
        reservationChooserBx.addItem("---wybierz---");
        /*for (Event e : eventList) {
            reservationChooserBx.addItem(e.getDateEvent().toString() + " " + e.getTitle());
        }*/
        reservationChooserBx.setBounds(30, 60, 300, 50);
        reservationChooserBx.setVisible(false);
    }

    private void createShortDescLbl() {
        shortDescLbl = new JLabel();
        shortDescLbl.setText("Krótki opis");
        shortDescLbl.setBounds(20, 140 + deltaY + 300 + 20, 100, 30);
    }

    private void createShortDescTxt() {
        shortDescTxt = new JTextArea();
        shortDescTxt.setBounds(150, 140 + deltaY + 300 + 20, fieldLength, 100);
        shortDescTxt.setWrapStyleWord(true);
        shortDescTxt.setLineWrap(true);
    }


    private void createPosterLbl() {
        posterLbl = new JLabel();
        posterLbl.setText("Plakat");
        posterLbl.setBounds(20, 140 + deltaY, 100, 30);
    }

    private void createPosterShowLbl() {
        posterShowLbl = new JLabel();
        posterShowLbl.setBounds(150, 140 + deltaY, fieldLength, 300);
    }


    private void createDateLbl() {
        dateLbl = new JLabel();
        dateLbl.setText("Data");
        dateLbl.setBounds(20, 100 + deltaY, 100, 30);
    }

    private void createDateTxt() {
        dateTxt = new JTextField();
        dateTxt.setBounds(150, 100 + deltaY, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(20, 60 + deltaY, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 60 + deltaY, fieldLength, 30);
    }


    private void setCompVisibility(boolean visibility) {
        titleLbl.setVisible(visibility);
        dateLbl.setVisible(visibility);
        posterLbl.setVisible(visibility);
        shortDescLbl.setVisible(visibility);
        titleTxt.setVisible(visibility);
        dateTxt.setVisible(visibility);
        posterShowLbl.setVisible(visibility);
        shortDescTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        titleTxt.setEditable(editability);
        dateTxt.setEditable(editability);
        shortDescTxt.setEditable(editability);
    }

    public JLabel getPosterShowLbl() {
        return posterShowLbl;
    }

    public void setPosterShowLbl(JLabel posterShowLbl) {
        this.posterShowLbl = posterShowLbl;
    }

    public JLabel getCardIdLbl() {
        return cardIdLbl;
    }

    public void setCardIdLbl(JLabel cardIdLbl) {
        this.cardIdLbl = cardIdLbl;
    }

    public JButton getReturnBtn() {
        return returnBtn;
    }
}
