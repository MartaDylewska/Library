package gui.librarian;

import gui.general.MyButton;

import javax.swing.*;

public class LibrarianEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private MyButton addBook, findBook, findAuthor, lendBook;
    private MyButton deleteReaderProfileBtn;
    private MyButton addEventBtn, deleteEventBtn, updateEventBtn;
    private MyButton returnBtn;
    private int firstBtnY = 120;
    private int distanceY = 40;
    private int buttonHeight = 30;

    public LibrarianEntryPanel() {

        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
    }

    private void addAllBtns() {
        add(addBook);
        add(findBook);
        add(findAuthor);
        add(lendBook);
        add(addEventBtn);
        add(deleteReaderProfileBtn);
        add(deleteEventBtn);
        //add(updateEventBtn);
        add(returnBtn);
    }

    private void createAllBtns() {

        createShowBookBtn();
        createLendBookBtn();
        createShowAuthorBtn();
        createAddBookBtn();
        createDeleteReaderProfileBtn();
        createAddEventBtn();
        createDeleteEventBtn();
        //createUpdateEventBtn();
        createReturnBtn();
    }

    private void createAddBookBtn() {
        addBook = new MyButton(true);
        addBook.setText("Dodaj książkę");
        addBook.setBounds(250, firstBtnY, 200, buttonHeight);
    }
    private void createShowBookBtn() {
        findBook = new MyButton(true);
        findBook.setText("Znajdź książkę");
        findBook.setBounds(250, firstBtnY + distanceY, 200, buttonHeight);
    }
    private void createShowAuthorBtn() {
        findAuthor = new MyButton(true);
        findAuthor.setText("Znajdź autora");
        findAuthor.setBounds(250, firstBtnY + 2 * distanceY, 200, buttonHeight);
    }
    private void createLendBookBtn() {
        lendBook = new MyButton(true);
        lendBook.setText("Wypożycz / przyjmij zwrot");
        lendBook.setBounds(250, firstBtnY + 3 * distanceY, 200, buttonHeight);
    }
    private void createDeleteReaderProfileBtn() {
        deleteReaderProfileBtn = new MyButton(true);
        deleteReaderProfileBtn.setText("Usuń profil czytelnika");
        deleteReaderProfileBtn.setBounds(250, firstBtnY + 4 * distanceY, 200, buttonHeight);
    }
    private void createAddEventBtn() {
        addEventBtn = new MyButton(true);
        addEventBtn.setText("Dodaj wydarzenie");
        addEventBtn.setBounds(250, firstBtnY + 5 * distanceY, 200, buttonHeight);
    }
    private void createDeleteEventBtn() {
        deleteEventBtn = new MyButton(true);
        deleteEventBtn.setText("Usuń wydarzenie");
        deleteEventBtn.setBounds(250, firstBtnY + 6 * distanceY, 200, buttonHeight);
    }
    private void createUpdateEventBtn(){
        updateEventBtn = new MyButton(true);
        updateEventBtn.setText("Aktualizuj wydarzenie");
        updateEventBtn.setBounds(250, firstBtnY + 7*distanceY,200,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new MyButton(false);
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(250, firstBtnY + 7 * distanceY, 200, buttonHeight);
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
        nameLbl.setBounds(250, 40, 200, 50);
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250, 80, 200, 50);
        cardNrLbl.setText("----------");
    }

    public MyButton getAddBook() {
        return addBook;
    }

    public MyButton getFindBook() {
        return findBook;
    }

    public MyButton getFindAuthor() {
        return findAuthor;
    }

    public MyButton getLendBook() {
        return lendBook;
    }

    public MyButton getDeleteReaderProfileBtn() {
        return deleteReaderProfileBtn;
    }
    public MyButton getAddEventBtn() {
        return addEventBtn;
    }
    public MyButton getDeleteEventBtn() {
        return deleteEventBtn;
    }

    public MyButton getReturnBtn() {
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
