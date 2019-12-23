package gui.reader;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class ReaderUpdatePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JPasswordField passField;
    private CustButton searchReaderBtn, updateReaderBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel rectLabel;
    private List<Component> componentList = new ArrayList<>();

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public ReaderUpdatePanel() {
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createCompList();
        setCompVisibility(false);
        createSearchBtn();
        add(searchReaderBtn);
        actionSearchUserBtn();
        createReturnBtn();
        add(returnBtn);
        createUpdateBtn();
        add(updateReaderBtn);
        setPostalCodeKL();
        actionUpdateReaderBtn();
        searchReaderBtn.setVisible(false);
        createRectLabel();
        add(rectLabel);
        setFontForAllElements();
        Auxiliary.setImageAsBackground(this);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(180,20,340,400);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void createCompList(){
        componentList.add(firstNameTxt);
        componentList.add(lastNameTxt);
        componentList.add(emailTxt);
        componentList.add(cardIdTxt);
        componentList.add(postalCodeTxt);
        componentList.add(cityNameTxt);
        componentList.add(streetAndBuildingTxt);
        componentList.add(firstNameLbl);
        componentList.add(lastNamelbl);
        componentList.add(emailLbl);
        componentList.add(passLbl);
        componentList.add(passField);
        componentList.add(cardIdLbl);
        componentList.add(postalCodeLbl);
        componentList.add(cityNameLbl);
        componentList.add(streetAndBuildingLbl);
    }

    private void setFontForAllElements(){
        for (Component c: componentList) {
            c.setFont(Auxiliary.panelFont);
        }
    }

    private void setPostalCodeKL() {
        postalCodeTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            @Override
            public void keyPressed(KeyEvent e) {}
            @Override
            public void keyReleased(KeyEvent e) {cityNameTxt.setText(cityDBService.getCityName(postalCodeTxt.getText())); }
        });
    }

    private void actionUpdateReaderBtn() {
        updateReaderBtn.addActionListener(e -> {
            if(Validation.checkIfEmailOK(emailTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny email");
            else if(Validation.checkIfPostalCodeOK(cityNameTxt.getText())==false)
                JOptionPane.showMessageDialog(this, "Niepoprawny kod pocztowy");
            else if(Validation.checkIfInteger(cardIdTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny numer karty użytkownika");
            else if(firstNameTxt.getText().equals("") || lastNameTxt.getText().equals("")|| emailTxt.getText().equals("")||postalCodeTxt.getText().equals("")||streetAndBuildingTxt.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Proszę wypełnić wszystkie pola");
            else {
                int cardId = Integer.parseInt(cardIdTxt.getText());
                User user = userDBService.readUserFromDB(cardId);
                int userId = user.getIdUser();
                    String userFirstName = firstNameTxt.getText();
                    String userLastName = lastNameTxt.getText();
                    String userEmail = emailTxt.getText();
                    String userSteetBuilding = streetAndBuildingTxt.getText();
                    String userPostalCode = postalCodeTxt.getText();
                    StringBuilder pass = new StringBuilder();
                    for (char c : passField.getPassword())
                        pass.append(c);
                    String userPass;
                    if (pass.toString().equals("------"))
                        userPass = user.getPassword();
                    else
                        userPass = pass.toString();

                    userDBService.updateUserInDB(userId, userFirstName, userLastName, userEmail, userPass, userSteetBuilding, userPostalCode, cardId);
                    JOptionPane.showMessageDialog(this, "Dane użytkownika zaktualizowane poprawnie");


            }

        });

    }

    private void actionSearchUserBtn() {
        searchReaderBtn.addActionListener(e -> {
            if (Validation.checkIfInteger(cardIdTxt.getText())) {
                int cardId = Integer.parseInt(cardIdTxt.getText());
                System.out.println(cardId);
                User user = userDBService.readUserFromDB(cardId);
                Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
                System.out.println(user);
                if (user.getIdUser() != 0 && reader.getIdReader()!=0) {
                    updateReaderBtn.setVisible(true);
                    setCompVisibility(true);
                    cardIdTxt.setEditable(false);
                    firstNameTxt.setText(user.getFirstName());
                    lastNameTxt.setText(user.getLastName());
                    emailTxt.setText(user.getEmail());
                    postalCodeTxt.setText(user.getPostalCode());
                    cityNameTxt.setText(cityDBService.getCityName(user.getPostalCode()));
                    streetAndBuildingTxt.setText(user.getStreetBuilding());
                } else {
                    cardIdTxt.setText("");
                    JOptionPane.showMessageDialog(this, "Brak karty o tym numerze w systemie");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Wpisz poprawny numer karty");
                cardIdTxt.setText("");
            }
        });
    }

    private void createSearchBtn() {
        searchReaderBtn = new CustButton();
        searchReaderBtn.setText("Wyszukaj");
        searchReaderBtn.setBounds(400, 20, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(350, 380, 150, 30);
    }

    private void createUpdateBtn() {
        updateReaderBtn = new CustButton();
        updateReaderBtn.setText("Aktualizuj dane");
        updateReaderBtn.setVisible(false);
        updateReaderBtn.setBounds(200, 380, 150, 30);
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
        add(passLbl);
        add(passField);
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
        createPassLbl();
        createPassTxt();
    }
    private void createPassLbl() {
        passLbl = new JLabel();
        passLbl.setText("Hasło");
        passLbl.setBounds(200, 340, 100, 30);
    }
    private void createPassTxt() {
        passField = new JPasswordField();
        passField.setText("------");
        passField.setBounds(300, 340, fieldLength, 30);
        passField.setBorder(Auxiliary.blackBorder());
    }
    private void createStreetAndBuildingLbl() {
        streetAndBuildingLbl = new JLabel();
        streetAndBuildingLbl.setText("Ulica/nr");
        streetAndBuildingLbl.setBounds(200, 300, 100, 30);
    }
    private void createStreetAndBuildingTxt() {
        streetAndBuildingTxt = new JTextField();
        streetAndBuildingTxt.setBounds(300, 300, fieldLength, 30);
        streetAndBuildingTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createCityNameLbl() {
        cityNameLbl = new JLabel();
        cityNameLbl.setText("Miasto");
        cityNameLbl.setBounds(200, 260, 100, 30);
    }
    private void createCityNameTxt() {
        cityNameTxt = new JTextField();
        cityNameTxt.setBounds(300, 260, fieldLength, 30);
        cityNameTxt.setEditable(false);
        cityNameTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createPostalCodeLbl() {
        postalCodeLbl = new JLabel();
        postalCodeLbl.setText("Kod pocztowy");
        postalCodeLbl.setBounds(200, 220, 100, 30);
    }
    private void createPostalCodeTxt() {
        postalCodeTxt = new JTextField();
        postalCodeTxt.setBounds(300, 220, fieldLength, 30);
        postalCodeTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createEmailLbl() {
        emailLbl = new JLabel();
        emailLbl.setText("Email");
        emailLbl.setBounds(200, 180, 100, 30);
    }
    private void createEmailTxt() {
        emailTxt = new JTextField();
        emailTxt.setBounds(300, 180, fieldLength, 30);
        emailTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createLastnameLbl() {
        lastNamelbl = new JLabel();
        lastNamelbl.setText("Nazwisko");
        lastNamelbl.setBounds(200, 140, 100, 30);
    }
    private void createLastNameTxt() {
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(300, 140, fieldLength, 30);
        lastNameTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createFirstnameLbl() {
        firstNameLbl = new JLabel();
        firstNameLbl.setText("Imię");
        firstNameLbl.setBounds(200, 100, 100, 30);
    }
    private void createFirstNameTxt() {
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(300, 100, fieldLength, 30);
        firstNameTxt.setBorder(Auxiliary.blackBorder());
    }
    private void createCardidLbl() {
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(200, 60, 100, 30);
    }
    private void createCardIdTxt() {
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(300, 60, fieldLength, 30);
        cardIdTxt.setBorder(Auxiliary.blackBorder());
    }
    private void setCompVisibility(boolean visibility) {
        for(Component c: componentList)
            c.setVisible(visibility);}
    public CustButton getReturnBtn() {return returnBtn;}
    public CustButton getSearchReaderBtn(){return searchReaderBtn;}
    public JTextField getCardIdTxt(){return cardIdTxt;}

}
