package gui.lending;

import book.Book;
import book.BookService;
import book.IBook;
import gui.reader.ReaderEntryPanel;
import lending.ILendingDBService;
import lending.Lending;
import lending.LendingDBServiceImpl;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.util.List;

public class AcceptReturnPanel extends JPanel {
    private JComboBox lendingChooserBx;
    private JButton viewLendingsBtn, acceptReturnBtn, viewOneLendingBtn,returnBtn;
    private JLabel titleLbl, dateCreationLbl, dateDueLbl,lendingIdLbl;
    private JTextField titleTxt, dateCreationTxt, dateDueTxt;
    private JTextField cardIdTxt;
    private JLabel cardIdLbl;
    int fieldLength = 200;
    int deltaY = 20;
    Lending selectedLending = null;
    Book book = null;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();
    private ILendingDBService lendingDBService = new LendingDBServiceImpl();
    private IBook bookDBService = new BookService();

    public AcceptReturnPanel() {

        setLayout(null);
        createCardIdLbl();
        add(cardIdLbl);
        createItems();
        addItems();
        //cardIdTxt.setVisible(false);
        setCompVisibility(false);
        setComponentsEditability(false);
        addActionViewLendingsBtn();
        addActionAcceptReturnBtn();
        addActionViewOneLendingBtn();
    }

    private void addItems() {
        //add(cardIdTxt);
        //System.out.println(cardIdTxt.getText());
        add(lendingIdLbl);
        add(lendingChooserBx);
        add(dateCreationLbl);
        add(dateCreationTxt);
        add(dateDueLbl);
        add(cardIdTxt);
        add(titleLbl);
        add(titleTxt);
        add(viewLendingsBtn);
        add(returnBtn);
        add(dateDueTxt);
        add(acceptReturnBtn);
        add(viewOneLendingBtn);

    }

    private void createItems() {
        //createCardIdTxt();
        createLendingIdLbl();
        createCardIdTxt();
        createEventChooserBx();
        createDateCreationLbl();
        createDateCreationTxt();
        createDateDueLbl();
        createDateDueTxt();
        createTitleLbl();
        createTitleTxt();
        createViewLendingsBtn();
        createReturnBtn();
        createAcceptReturnBtn();
        createViewOneLendingBtn();

    }


    private void addActionViewLendingsBtn() {
        viewLendingsBtn.addActionListener(e -> {
            List<Lending> lendingList = lendingDBService.showLendingsForUser(Integer.parseInt(cardIdTxt.getText()));
            for (Lending l : lendingList) {
                lendingChooserBx.addItem(l.getIdLending() + " " + l.getDateCreation().toString());
            }
            lendingChooserBx.setVisible(true);

        });
    }

    private void addActionViewOneLendingBtn(){
        viewOneLendingBtn.addActionListener(e -> {
            List<Lending> lendingList = lendingDBService.showLendingsForUser(Integer.parseInt(cardIdTxt.getText()));

            setCompVisibility(true);

            for (Lending lending : lendingList) {
                if (((lending.getIdLending() + " " + lending.getDateCreation().toString()).equals(lendingChooserBx.getSelectedItem()))) {
                    selectedLending = lending;
                    book =  bookDBService.getBook(lending.getBook_id());
                    titleTxt.setText(book.getTitle());
                    dateCreationTxt.setText(lending.getDateCreation().toString());
                    dateDueTxt.setText(lending.getDateDue().toString());
                    lendingIdLbl.setText(String.valueOf(lending.getIdLending()));
                }
            }
        });
    }

    private void addActionAcceptReturnBtn(){
        acceptReturnBtn.addActionListener(e -> {
            int idLending = Integer.parseInt(lendingIdLbl.getText());
            book.setAvailable(true);
            lendingDBService.deleteLending(idLending);
            lendingChooserBx.removeItem(idLending);
            JOptionPane.showMessageDialog(this,"Wypozyczenie usunięto");

        });
    }

    private void createLendingIdLbl(){
        lendingIdLbl = new JLabel();
        lendingIdLbl.setBounds(30,500, 30,30);
    }

    private void createCardIdLbl(){
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(30,10, 150,30);
    }

    private void createCardIdTxt(){
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(200,10,100,30);
    }

    private void createViewLendingsBtn() {
        viewLendingsBtn = new JButton();
        viewLendingsBtn.setText("Podgląd");
        viewLendingsBtn.setBounds(450, 10, 200, 50);
    }

    private void createViewOneLendingBtn() {
        viewOneLendingBtn = new JButton();
        viewOneLendingBtn.setText("Podgląd wypożyczenia");
        viewOneLendingBtn.setBounds(450, 90, 200, 50);
    }

    private void createAcceptReturnBtn() {
        acceptReturnBtn = new JButton();
        acceptReturnBtn.setText("Przyjmij zwrot");
        acceptReturnBtn.setBounds(450, 150, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 50);
    }

    private void createEventChooserBx() {
        lendingChooserBx = new JComboBox();
        //int idEvent = selectedEvent.getIdEvent();

        lendingChooserBx.addItem("---wybierz---");
       /* for (Lending l : lendingList) {
            lendingChooserBx.addItem(l.getIdLending() + " " + l.getDateCreation().toString());
        }*/
        lendingChooserBx.setBounds(30, 10+40, 300, 50);
        lendingChooserBx.setVisible(false);
    }

    private void createDateDueLbl() {
        dateDueLbl = new JLabel();
        dateDueLbl.setText("Data zwrotu");
        dateDueLbl.setBounds(20, 140 + deltaY +40, 100, 30);
    }

    private void createDateDueTxt(){
        dateDueTxt = new JTextField();
        dateDueTxt.setBounds(150, 140+deltaY+40,100,30);
    }


    private void createDateCreationLbl() {
        dateCreationLbl = new JLabel();
        dateCreationLbl.setText("Data wypożyczenia");
        dateCreationLbl.setBounds(20, 100 + deltaY+40, 100, 30);
    }

    private void createDateCreationTxt() {
        dateCreationTxt = new JTextField();
        dateCreationTxt.setBounds(150, 100 + deltaY+40, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(20, 60 + deltaY+40, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 60 + deltaY+40, fieldLength+50, 30);
    }

    private void setCompVisibility(boolean visibility) {
        titleLbl.setVisible(visibility);
        dateCreationLbl.setVisible(visibility);
        dateDueLbl.setVisible(visibility);
        titleTxt.setVisible(visibility);
        dateCreationTxt.setVisible(visibility);
        dateDueTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        titleTxt.setEditable(editability);
        dateCreationTxt.setEditable(editability);
        dateDueTxt.setEditable(editability);
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
