package gui.reader;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import librarian.Librarian;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ReaderUpdatePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JPasswordField passField;
    private JButton searchReaderBtn, updateReaderBtn, returnBtn;
    private int fieldLength = 200;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public ReaderUpdatePanel() {
        setLayout(null);
        createAllLabels();
        addAllLabels();
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
        searchReaderBtn = new JButton();
        searchReaderBtn.setText("Wyszukaj");
        searchReaderBtn.setBounds(400, 20, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 50);
    }

    private void createUpdateBtn() {
        updateReaderBtn = new JButton();
        updateReaderBtn.setText("Aktualizuj dane");
        updateReaderBtn.setVisible(false);
        updateReaderBtn.setBounds(400, 150, 200, 50);
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
        passLbl.setBounds(20, 300, 100, 30);
    }

    private void createPassTxt() {
        passField = new JPasswordField();
        passField.setText("------");
        passField.setBounds(150, 300, fieldLength, 30);
    }

    private void createStreetAndBuildingLbl() {
        streetAndBuildingLbl = new JLabel();
        streetAndBuildingLbl.setText("Ulica/nr");
        streetAndBuildingLbl.setBounds(20, 260, 100, 30);
    }

    private void createStreetAndBuildingTxt() {
        streetAndBuildingTxt = new JTextField();
        streetAndBuildingTxt.setBounds(150, 260, fieldLength, 30);
    }

    private void createCityNameLbl() {
        cityNameLbl = new JLabel();
        cityNameLbl.setText("Miasto");
        cityNameLbl.setBounds(20, 220, 100, 30);
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
    }

    private void createPostalCodeTxt() {
        postalCodeTxt = new JTextField();
        postalCodeTxt.setBounds(150, 180, fieldLength, 30);
    }

    private void createEmailLbl() {
        emailLbl = new JLabel();
        emailLbl.setText("Email");
        emailLbl.setBounds(20, 140, 100, 30);
    }

    private void createEmailTxt() {
        emailTxt = new JTextField();
        emailTxt.setBounds(150, 140, fieldLength, 30);
    }

    private void createLastnameLbl() {
        lastNamelbl = new JLabel();
        lastNamelbl.setText("Nazwisko");
        lastNamelbl.setBounds(20, 100, 100, 30);
    }

    private void createLastNameTxt() {
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(150, 100, fieldLength, 30);
    }

    private void createFirstnameLbl() {
        firstNameLbl = new JLabel();
        firstNameLbl.setText("Imię");
        firstNameLbl.setBounds(20, 60, 100, 30);
    }

    private void createFirstNameTxt() {
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(150, 60, fieldLength, 30);
    }

    private void createCardidLbl() {
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(20, 20, 100, 30);
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
        passField.setVisible(visibility);
        passLbl.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        firstNameLbl.setEnabled(editability);
        lastNamelbl.setEnabled(editability);
        emailLbl.setEnabled(editability);
        postalCodeLbl.setEnabled(editability);
        cardIdLbl.setEnabled(editability);
        cityNameLbl.setEnabled(editability);
        streetAndBuildingLbl.setEnabled(editability);
        firstNameTxt.setEnabled(editability);
        lastNameTxt.setEnabled(editability);
        emailTxt.setEnabled(editability);
        //passTxt.setEnabled(editability);
        cardIdTxt.setEnabled(editability);
        postalCodeTxt.setEnabled(editability);
        cityNameTxt.setEnabled(editability);
        streetAndBuildingTxt.setEnabled(editability);
    }

    public JButton getReturnBtn() {
        return returnBtn;
    }
    public JButton getSearchReaderBtn(){return searchReaderBtn;}
    public JTextField getCardIdTxt(){return cardIdTxt;}

}
