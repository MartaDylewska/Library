package gui.librarian;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.general.MyButton;
import librarian.ILibrarianDBService;
import librarian.Librarian;
import librarian.LibrarianDBServiceImpl;
import reader.Reader;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDate;

public class LibrarianUpdatePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JLabel salaryLbl, dateEmploymentLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JTextField salaryTxt, dateEmploymentTxt;
    private JPasswordField passField;
    private MyButton searchLibrarianBtn, updateLibrarianBtn, returnBtn;
    private int fieldLength = 200;

    private IUserDBService userDBService = new UserDBServiceImpl();
//    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private ILibrarianDBService librarianDBService = new LibrarianDBServiceImpl();

    public LibrarianUpdatePanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(false);
        createSearchBtn();
        add(searchLibrarianBtn);
        actionSearchLibrarianBtn();
        createReturnBtn();
        add(returnBtn);
        createUpdateBtn();
        add(updateLibrarianBtn);
        setPostalCodeKL();
        actionUpdateLibrarianBtn();
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

    private void actionUpdateLibrarianBtn() {
        updateLibrarianBtn.addActionListener(e -> {
            if(Validation.checkIfDateOk(dateEmploymentTxt.getText())== false)
                JOptionPane.showMessageDialog(this, "Niepoprawna data");
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
                String librarianSalary = salaryTxt.getText();
                LocalDate librarianEmploymentDate = LocalDate.parse(dateEmploymentTxt.getText());
                librarianDBService.updateLibrarianInDB(userId,librarianSalary,librarianEmploymentDate);
                JOptionPane.showMessageDialog(this, "Dane bibliotekarza zaktualizowane poprawnie");
            }
        });

    }

    private void actionSearchLibrarianBtn() {
        searchLibrarianBtn.addActionListener(e -> {
            if (Validation.checkIfInteger(cardIdTxt.getText())) {
                //setComponentsEditability(false);
                int cardId = Integer.parseInt(cardIdTxt.getText());
                System.out.println(cardId);
                Librarian librarian = librarianDBService.readLibrarianFromDB(cardId);
                System.out.println(librarian);
                if (librarian.getIdLibrarian() != 0) {
                    setCompVisibility(true);
                    updateLibrarianBtn.setVisible(true);
                    firstNameTxt.setText(librarian.getFirstName());
                    lastNameTxt.setText(librarian.getLastName());
                    emailTxt.setText(librarian.getEmail());
                    postalCodeTxt.setText(librarian.getPostalCode());
                    cityNameTxt.setText(cityDBService.getCityName(librarian.getPostalCode()));
                    streetAndBuildingTxt.setText(librarian.getStreetBuilding());
                    salaryTxt.setText(librarian.getSalary());
                    dateEmploymentTxt.setText(librarian.getEmploymentDate().toString());
                } else {
                    cardIdTxt.setText("");
                    setCompVisibility(false);
                    JOptionPane.showMessageDialog(this, "Brak karty o tym numerze w systemie");
                }
            } else{
                JOptionPane.showMessageDialog(this, "Wpisz poprawny numer karty");
                cardIdTxt.setText("");
            }
        });
    }

    private void createSearchBtn() {
        searchLibrarianBtn = new MyButton(true);
        searchLibrarianBtn.setText("Wyszukaj");
        searchLibrarianBtn.setBounds(400, 20, 200, 30);
    }

    private void createReturnBtn() {
        returnBtn = new MyButton(false);
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 30);
    }

    private void createUpdateBtn() {
        updateLibrarianBtn = new MyButton(true);
        updateLibrarianBtn.setText("Aktualizuj dane");
        updateLibrarianBtn.setVisible(false);
        updateLibrarianBtn.setBounds(400, 150, 200, 30);
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
        add(salaryLbl);
        add(salaryTxt);
        add(dateEmploymentLbl);
        add(dateEmploymentTxt);
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
        createDateEmplLbl();
        createDateEmplTxt();
        createSalaryLbl();
        createSalaryTxt();
    }

    private void createDateEmplLbl(){
        dateEmploymentLbl = new JLabel();
        dateEmploymentLbl.setText("Data zatrudnienia");
        dateEmploymentLbl.setBounds(20, 380, 100, 30);
    }

    private void createDateEmplTxt(){
        dateEmploymentTxt = new JTextField();
        dateEmploymentTxt.setBounds(150, 380, fieldLength, 30);
        dateEmploymentTxt.setToolTipText("Data w formacie YYYY-MM-DD");
    }

    private void createSalaryLbl(){
        salaryLbl = new JLabel();
        salaryLbl.setText("Pensja");
        salaryLbl.setBounds(20, 340, 100, 30);
    }

    private void createSalaryTxt() {
        salaryTxt = new JTextField();
        salaryTxt.setBounds(150, 340, fieldLength, 30);
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
        passLbl.setVisible(visibility);
        dateEmploymentLbl.setVisible(visibility);
        salaryLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        passField.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
        dateEmploymentTxt.setVisible(visibility);
        salaryTxt.setVisible(visibility);
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

    public MyButton getReturnBtn() {
        return returnBtn;
    }


}
