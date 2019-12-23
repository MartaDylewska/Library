package gui.librarian;

import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;

import javax.swing.*;
import java.awt.*;

public class LibrarianEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private CustButton addBook, findBook, findAuthor, lendBook;
    private CustButton deleteReaderProfileBtn;
    private CustButton addEventBtn, deleteEventBtn, updateEventBtn;
    private CustButton returnBtn;
    private int firstBtnY = 105;
    private int distanceY = 40;
    private int buttonHeight = 30;
    private JLabel rectLabel;

    public LibrarianEntryPanel() {

        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        //rectLabel.setText("aa");
        rectLabel.setBounds(200,20,300,410);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
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
        addBook = new CustButton();
        addBook.setText("Dodaj książkę");
        addBook.setBounds(250, firstBtnY, 200, buttonHeight);
    }
    private void createShowBookBtn() {
        findBook = new CustButton();
        findBook.setText("Znajdź książkę");
        findBook.setBounds(250, firstBtnY + distanceY, 200, buttonHeight);
    }
    private void createShowAuthorBtn() {
        findAuthor = new CustButton();
        findAuthor.setText("Znajdź autora");
        findAuthor.setBounds(250, firstBtnY + 2 * distanceY, 200, buttonHeight);
    }
    private void createLendBookBtn() {
        lendBook = new CustButton();
        lendBook.setText("Wypożycz / przyjmij zwrot");
        lendBook.setBounds(250, firstBtnY + 3 * distanceY, 200, buttonHeight);
    }
    private void createDeleteReaderProfileBtn() {
        deleteReaderProfileBtn = new CustButton();
        deleteReaderProfileBtn.setText("Usuń profil czytelnika");
        deleteReaderProfileBtn.setBounds(250, firstBtnY + 4 * distanceY, 200, buttonHeight);
    }
    private void createAddEventBtn() {
        addEventBtn = new CustButton();
        addEventBtn.setText("Dodaj wydarzenie");
        addEventBtn.setBounds(250, firstBtnY + 5 * distanceY, 200, buttonHeight);
    }
    private void createDeleteEventBtn() {
        deleteEventBtn = new CustButton();
        deleteEventBtn.setText("Usuń wydarzenie");
        deleteEventBtn.setBounds(250, firstBtnY + 6 * distanceY, 200, buttonHeight);
    }
    private void createUpdateEventBtn(){
        updateEventBtn = new CustButton();
        updateEventBtn.setText("Aktualizuj wydarzenie");
        updateEventBtn.setBounds(250, firstBtnY + 7*distanceY,200,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new CustButton();
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
        nameLbl.setBounds(250,40,200,30);
        nameLbl.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        nameLbl.setForeground(new Color(78,52,46,255));
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250,60,200,30);
        cardNrLbl.setText("----------");
        cardNrLbl.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        cardNrLbl.setForeground(new Color(78,52,46,255));
    }

    public CustButton getAddBook() {
        return addBook;
    }

    public CustButton getFindBook() {
        return findBook;
    }

    public CustButton getFindAuthor() {
        return findAuthor;
    }

    public CustButton getLendBook() {
        return lendBook;
    }

    public CustButton getDeleteReaderProfileBtn() {
        return deleteReaderProfileBtn;
    }
    public CustButton getAddEventBtn() {
        return addEventBtn;
    }
    public CustButton getDeleteEventBtn() {
        return deleteEventBtn;
    }

    public CustButton getReturnBtn() {
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
