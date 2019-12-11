package gui.lending;

import book.Book;
import book.BookService;
import book.IBook;
import event.EventDBServiceImpl;
import event.IEventDBService;
import gui.reader.ReaderEntryPanel;
import images.IPosterDBService;
import images.PosterDBServiceImpl;
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

public class LendingShowPanel extends JPanel {
    private JComboBox lendingChooserBx;
    private JButton viewLendingsBtn,  returnBtn;
    private JLabel titleLbl, dateCreationLbl, dateDueLbl;
    private JTextField titleTxt, dateCreationTxt, dateDueTxt;
    private JLabel cardIdTxt;
    int fieldLength = 200;
    int deltaY = 20;
    Lending selectedLending = null;
    ReaderEntryPanel readerEntryPanel;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();
    private ILendingDBService lendingDBService = new LendingDBServiceImpl();
    private IBook bookDBService = new BookService();

    public LendingShowPanel(ReaderEntryPanel readerEntryPanel) {

        setLayout(null);
        this.readerEntryPanel = readerEntryPanel;
        createCardIdTxt();
        add(cardIdTxt);
        cardIdTxt.setText(readerEntryPanel.getCardNrLbl().getText());
        createItems();
        addItems();
        cardIdTxt.setVisible(false);
        setCompVisibility(false);
        setComponentsEditability(false);
        addActionViewLendingBtn();
    }

    private void addItems() {
        //add(cardIdTxt);
        //System.out.println(cardIdTxt.getText());
        add(lendingChooserBx);
        add(dateCreationLbl);
        add(dateCreationTxt);
        add(dateDueLbl);

        add(titleLbl);
        add(titleTxt);
        add(viewLendingsBtn);
        add(returnBtn);
        add(dateDueTxt);

    }

    private void createItems() {
        //createCardIdTxt();
        createEventChooserBx();
        createDateCreationLbl();
        createDateCreationTxt();
        createDateDueLbl();
        createDateDueTxt();
        createTitleLbl();
        createTitleTxt();
        createViewEventBtn();
        createReturnBtn();

    }


    private void addActionViewLendingBtn() {
        viewLendingsBtn.addActionListener(e -> {
            List<Lending> lendingList = lendingDBService.showLendingsForUser(Integer.parseInt(cardIdTxt.getText()));


            if (lendingChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                Book book = null;
                for (Lending lending : lendingList) {
                    if (((lending.getIdLending() + " " + lending.getDateCreation().toString()).equals(lendingChooserBx.getSelectedItem()))) {
                        selectedLending = lending;
                        book =  bookDBService.getBook(lending.getBook_id());

                    }
                }
                titleTxt.setText(book.getTitle());
                dateCreationTxt.setText(selectedLending.getDateCreation().toString());
                dateDueTxt.setText(selectedLending.getDateDue().toString());


            } else {
                JOptionPane.showMessageDialog(this, "Proszę wybrać wypożyczenie");
            }
        });
    }

    private void createCardIdTxt(){
        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
    }


    private void createViewEventBtn() {
        viewLendingsBtn = new JButton();
        viewLendingsBtn.setText("Podgląd");
        viewLendingsBtn.setBounds(450, 10, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 50);
    }

    private void createEventChooserBx() {
        lendingChooserBx = new JComboBox();
        //int idEvent = selectedEvent.getIdEvent();
        //User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
       // Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        //List<Lending> lendingList = lendingDBService.showLendingsForUser(Integer.parseInt(cardIdTxt.getText()));
        lendingChooserBx.addItem("---wybierz---");
        /*for (Lending l : lendingList) {
            lendingChooserBx.addItem(l.getIdLending() + " " + l.getDateCreation().toString());
        }*/
        lendingChooserBx.setBounds(30, 10, 300, 50);
    }

    private void createDateDueLbl() {
        dateDueLbl = new JLabel();
        dateDueLbl.setText("Data zwrotu");
        dateDueLbl.setBounds(20, 140 + deltaY, 100, 30);
    }

    private void createDateDueTxt(){
        dateDueTxt = new JTextField();
        dateDueTxt.setBounds(150, 140+deltaY,100,30);
    }


    private void createDateCreationLbl() {
        dateCreationLbl = new JLabel();
        dateCreationLbl.setText("Data wypożyczenia");
        dateCreationLbl.setBounds(20, 100 + deltaY, 100, 30);
    }

    private void createDateCreationTxt() {
        dateCreationTxt = new JTextField();
        dateCreationTxt.setBounds(150, 100 + deltaY, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(20, 60 + deltaY, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 60 + deltaY, fieldLength+150, 30);
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

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }

    public JButton getReturnBtn() {
        return returnBtn;
    }
}
