package gui.reader;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;
import librarian.Librarian;

import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDeletePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, passTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private CustButton searchReaderBtn, deleteReaderBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel imageLbl;
    private JLabel rectLabel;
    private List<Component> componentPlainList = new ArrayList<>();
    private List<Component> componentBoldList = new ArrayList<>();

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public ReaderDeletePanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(false);
        createSearchBtn();
        add(searchReaderBtn);
        actionSearchReaderBtn();
        createReturnBtn();
        add(returnBtn);
        setComponentsEditability(false);
        createDeleteBtn();
        add(deleteReaderBtn);
        actionDeleteReaderBtn();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
        setFontForAllElements();
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(10,10,680,350);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void setFontForAllElements(){
        componentPlainList.add(firstNameTxt);
        componentPlainList.add(lastNameTxt);
        componentPlainList.add(emailTxt);
        componentPlainList.add(cardIdTxt);
        componentPlainList.add(postalCodeTxt);
        componentPlainList.add(cityNameTxt);
        componentPlainList.add(streetAndBuildingTxt);

        componentBoldList.add(firstNameLbl);
        componentBoldList.add(lastNamelbl);
        componentBoldList.add(emailLbl);
        componentBoldList.add(passLbl);
        componentBoldList.add(cardIdLbl);
        componentBoldList.add(postalCodeLbl);
        componentBoldList.add(cityNameLbl);
        componentBoldList.add(streetAndBuildingLbl);

        for (Component c: componentPlainList) {
            c.setFont(Auxiliary.panelPlainFont);
        }

    }

    private void actionDeleteReaderBtn() {

        deleteReaderBtn.addActionListener(e -> {
            int cardId = Integer.parseInt(cardIdTxt.getText());
            User user = userDBService.readUserFromDB(cardId);
            readerDBService.deleteReaderFromDB(user.getIdUser());
            //userDBService.deleteUserFromDB(cardId);

            firstNameTxt.setText("");
            lastNameTxt.setText("");
            emailTxt.setText("");
            postalCodeTxt.setText("");
            cityNameTxt.setText("");
            streetAndBuildingTxt.setText("");
            cardIdTxt.setText("");
            setCompVisibility(false);
            JOptionPane.showMessageDialog(this, "Użytkownik usunięty z systemu");
        });
    }

    private void actionSearchReaderBtn() {
        searchReaderBtn.addActionListener(e -> {

            if (Validation.checkIfInteger(cardIdTxt.getText())) {
                //setComponentsEditability(false);
                int cardId = Integer.parseInt(cardIdTxt.getText());
                System.out.println(cardId);
                User user = userDBService.readUserFromDB(cardId);
                //pobieramy czytelnika na podst idUsera
                Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
                System.out.println(reader);
                if (!(reader.getIdReader() == 0)) {
                    deleteReaderBtn.setVisible(true);
                    setCompVisibility(true);
                    firstNameTxt.setText(user.getFirstName());
                    lastNameTxt.setText(user.getLastName());
                    emailTxt.setText(user.getEmail());
                    postalCodeTxt.setText(user.getPostalCode());
                    cityNameTxt.setText(cityDBService.getCityName(user.getPostalCode()));
                    streetAndBuildingTxt.setText(user.getStreetBuilding());
                } else {
                    cardIdTxt.setText("");
                    setCompVisibility(false);
                    JOptionPane.showMessageDialog(this, "Brak użytkownika o tym numerze w bazie czytelników");
                }
            } else{
                JOptionPane.showMessageDialog(this, "Wpisz poprawny numer karty");
                cardIdTxt.setText("");
                setCompVisibility(false);
            }
        });
    }

    private void createDeleteBtn() {
        deleteReaderBtn = new CustButton();
        deleteReaderBtn.setText("Usuń użytkownika");
        deleteReaderBtn.setVisible(false);
        deleteReaderBtn.setBounds(400, 150, 200, 30);
    }

    private void createSearchBtn() {
        searchReaderBtn = new CustButton();
        searchReaderBtn.setText("Wyszukaj");
        searchReaderBtn.setBounds(400, 20, 200, 30);
    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 30);
    }

    private void addAllLabels() {
        add(cardIdLbl);
        add(cardIdTxt);
        add(firstNameLbl);
        add(firstNameTxt);
        add(lastNamelbl);
        add(lastNameTxt);
        add(emailLbl);
        add(emailTxt);
        add(postalCodeLbl);
        add(postalCodeTxt);
        add(cityNameLbl);
        add(cityNameTxt);
        add(streetAndBuildingLbl);
        add(streetAndBuildingTxt);
    }

    private void createAllLabels() {
        createCardidLbl();
        createCardIdTxt();
        createFirstnameLbl();
        createFirstNameTxt();
        createLastnameLbl();
        createLastNameTxt();
        createEmailLbl();
        createEmailTxt();
        createPostalCodeLbl();
        createPostalCodeTxt();
        createCityNameLbl();
        createCityNameTxt();
        createStreetAndBuildingLbl();
        createStreetAndBuildingTxt();
    }

    private void createStreetAndBuildingLbl() {
        streetAndBuildingLbl = new JLabel();
        streetAndBuildingLbl.setText("Ulica/nr");
        streetAndBuildingLbl.setBounds(20, 260, 100, 30);
        streetAndBuildingLbl.setFont(Auxiliary.panelFont);
    }

    private void createStreetAndBuildingTxt() {
        streetAndBuildingTxt = new JTextField();
        streetAndBuildingTxt.setBounds(150, 260, fieldLength, 30);
    }

    private void createCityNameLbl() {
        cityNameLbl = new JLabel();
        cityNameLbl.setText("Miasto");
        cityNameLbl.setBounds(20, 220, 100, 30);
        cityNameLbl.setFont(Auxiliary.panelFont);
    }

    private void createCityNameTxt() {
        cityNameTxt = new JTextField();
        cityNameTxt.setBounds(150, 220, fieldLength, 30);
        cityNameTxt.setEditable(false);
    }

    private void createPostalCodeLbl() {
        postalCodeLbl = new JLabel();
        postalCodeLbl.setText("Kod pocztowy");
        postalCodeLbl.setBounds(20, 180, 100, 30);
        postalCodeLbl.setFont(Auxiliary.panelFont);
    }

    private void createPostalCodeTxt() {
        postalCodeTxt = new JTextField();
        postalCodeTxt.setBounds(150, 180, fieldLength, 30);
    }

    private void createEmailLbl() {
        emailLbl = new JLabel();
        emailLbl.setText("Email");
        emailLbl.setBounds(20, 140, 100, 30);
        emailLbl.setFont(Auxiliary.panelFont);
    }

    private void createEmailTxt() {
        emailTxt = new JTextField();
        emailTxt.setBounds(150, 140, fieldLength, 30);
    }

    private void createLastnameLbl() {
        lastNamelbl = new JLabel();
        lastNamelbl.setText("Nazwisko");
        lastNamelbl.setBounds(20, 100, 100, 30);
        lastNamelbl.setFont(Auxiliary.panelFont);
    }

    private void createLastNameTxt() {
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(150, 100, fieldLength, 30);
    }

    private void createFirstnameLbl() {
        firstNameLbl = new JLabel();
        firstNameLbl.setText("Imię");
        firstNameLbl.setBounds(20, 60, 100, 30);
        firstNameLbl.setFont(Auxiliary.panelFont);
    }

    private void createFirstNameTxt() {
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(150, 60, fieldLength, 30);
    }

    private void createCardidLbl() {
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(20, 20, 100, 30);
        cardIdLbl.setFont(Auxiliary.panelFont);
    }

    private void createCardIdTxt() {
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(150, 20, fieldLength, 30);
    }

    private void setCompVisibility(boolean visibility) {
        firstNameLbl.setVisible(visibility);
        lastNamelbl.setVisible(visibility);
        emailLbl.setVisible(visibility);
        postalCodeLbl.setVisible(visibility);
        cityNameLbl.setVisible(visibility);
        streetAndBuildingLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        //passTxt.setVisible(visibility);
        //cardIdTxt.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {

        firstNameTxt.setEditable(editability);
        lastNameTxt.setEditable(editability);
        emailTxt.setEditable(editability);
        // passTxt.setEnabled(editability);
        //cardIdTxt.setEnabled(editability);
        postalCodeTxt.setEditable(editability);
        cityNameTxt.setEditable(editability);
        streetAndBuildingTxt.setEditable(editability);
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}
