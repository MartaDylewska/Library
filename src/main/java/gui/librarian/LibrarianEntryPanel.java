package gui.librarian;

import javax.swing.*;

public class LibrarianEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private JButton addBookBtn, deleteBookBtn, updateBookBtn;
    private JButton confirmReservationBtn, acceptReturnBtn;
    private JButton deleteReaderProfileBtn;
    private JButton addEventBtn, deleteEventBtn, updateEventBtn;
    private JButton returnBtn;
    private int firstBtnY = 10;
    private int distanceY = 50;
    private int buttonHeight = 40;

    public LibrarianEntryPanel() {
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
    }

    private void addAllBtns() {
        add(addBookBtn);
        add(acceptReturnBtn);
        add(addEventBtn);
        add(confirmReservationBtn);
        add(deleteReaderProfileBtn);
        add(deleteBookBtn);
        add(deleteEventBtn);
        add(updateBookBtn);
        //add(updateEventBtn);
        add(returnBtn);
    }

    private void createAllBtns() {
        createAcceptReturnBtn();
        createDeleteBookBtn();
        createConfirmReservationBtn();
        createUpdateBookBtn();
        createAddBookBtn();
        createDeleteReaderProfileBtn();
        createAddEventBtn();
        createDeleteEventBtn();
        //createUpdateEventBtn();
        createReturnBtn();
    }

    private void createAddBookBtn() {
        addBookBtn = new JButton();
        addBookBtn.setText("Dodaj książkę");
        addBookBtn.setBounds(300, firstBtnY, 250, buttonHeight);
    }
    private void createDeleteBookBtn() {
        deleteBookBtn = new JButton();
        deleteBookBtn.setText("Usuń książkę");
        deleteBookBtn.setBounds(300, firstBtnY + distanceY, 250, buttonHeight);
    }
    private void createUpdateBookBtn() {
        updateBookBtn = new JButton();
        updateBookBtn.setText("Aktualizuj książkę");
        updateBookBtn.setBounds(300, firstBtnY + 2 * distanceY, 250, buttonHeight);
    }
    private void createConfirmReservationBtn() {
        confirmReservationBtn = new JButton();
        confirmReservationBtn.setText("Zatwierdź rezerwację");
        confirmReservationBtn.setBounds(300, firstBtnY + 3 * distanceY, 250, buttonHeight);
    }
    private void createAcceptReturnBtn() {
        acceptReturnBtn = new JButton();
        acceptReturnBtn.setText("Przyjmij zwrot");
        acceptReturnBtn.setBounds(300, firstBtnY + 4 * distanceY, 250, buttonHeight);
    }
    private void createDeleteReaderProfileBtn() {
        deleteReaderProfileBtn = new JButton();
        deleteReaderProfileBtn.setText("Usuń profil czytelnika");
        deleteReaderProfileBtn.setBounds(300, firstBtnY + 5 * distanceY, 250, buttonHeight);
    }
    private void createAddEventBtn() {
        addEventBtn = new JButton();
        addEventBtn.setText("Dodaj wydarzenie");
        addEventBtn.setBounds(300, firstBtnY + 6 * distanceY, 250, buttonHeight);
    }
    private void createDeleteEventBtn() {
        deleteEventBtn = new JButton();
        deleteEventBtn.setText("Usuń wydarzenie");
        deleteEventBtn.setBounds(300, firstBtnY + 7 * distanceY, 250, buttonHeight);
    }
    private void createUpdateEventBtn(){
        updateEventBtn = new JButton();
        updateEventBtn.setText("Aktualizuj wydarzenie");
        updateEventBtn.setBounds(300, firstBtnY + 8*distanceY,250,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(300, firstBtnY + 9 * distanceY, 250, buttonHeight);
    }

    private void addAllLabels() {
        add(cardNrLbl);
        add(nameLbl);
    }

    private void createAllLabels() {
        createCardNrLbl();
        createNameLbl();
    }

    private void createNameLbl() {
        nameLbl = new JLabel();
        nameLbl.setText("-------");
        nameLbl.setBounds(50, 50, 200, 50);
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(50, 150, 200, 50);
        cardNrLbl.setText("----------");
    }

    public JButton getAddBookBtn() {
        return addBookBtn;
    }

    public JButton getDeleteBookBtn() {
        return deleteBookBtn;
    }

    public JButton getUpdateBookBtn() {
        return updateBookBtn;
    }

    public JButton getConfirmReservationBtn() {
        return confirmReservationBtn;
    }

    public JButton getAcceptReturnBtn() {
        return acceptReturnBtn;
    }
    public JButton getDeleteReaderProfileBtn() {
        return deleteReaderProfileBtn;
    }
    public JButton getAddEventBtn() {
        return addEventBtn;
    }
    public JButton getDeleteEventBtn() {
        return deleteEventBtn;
    }

   /* public JButton getUpdateEventBtn() {
        return updateEventBtn;
    }*/

    public JButton getReturnBtn() {
        return returnBtn;
    }

    public void setNameLbl(String name) {
        this.nameLbl.setText(name);
    }

    public void setCardNrLbl(String cardNr) {
        this.cardNrLbl.setText(cardNr);
    }

    public JLabel getCardNrLbl() {
        return cardNrLbl;
    }
}
