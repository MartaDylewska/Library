package gui.admin;

import admin.Admin;
import admin.AdminDBServiceImpl;
import admin.IAdminDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.Auxiliary;
import gui.general.CustButton;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class AdminUpdatePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JLabel salaryLbl, isFullTimeLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JTextField salaryTxt;
    private JCheckBox isFullTimeChbx;
    private JPasswordField passField;
    private CustButton searchAdminBtn, updateAdminBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel rectLabel;
    private List<Component> componentPlainList = new ArrayList<>();
    private List<Component> componentBoldList = new ArrayList<>();

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IAdminDBService adminDBService = new AdminDBServiceImpl();


    public AdminUpdatePanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(false);
        createSearchBtn();
        add(searchAdminBtn);
        actionSearchAdminBtn();
        createReturnBtn();
        add(returnBtn);
        createUpdateBtn();
        add(updateAdminBtn);
        setPostalCodeKL();
        actionUpdateLibrarianBtn();
        createRectLabel();
        add(rectLabel);
        setCompVisibility(false);
        Auxiliary.setImageAsBackground(this);
        setFontForAllElements();
    }

    private void setFontForAllElements(){
        componentPlainList.add(firstNameTxt);
        componentPlainList.add(lastNameTxt);
        componentPlainList.add(emailTxt);
        componentPlainList.add(cardIdTxt);
        componentPlainList.add(postalCodeTxt);
        componentPlainList.add(cityNameTxt);
        componentPlainList.add(streetAndBuildingTxt);
        componentPlainList.add(salaryTxt);

        componentBoldList.add(firstNameLbl);
        componentBoldList.add(lastNamelbl);
        componentBoldList.add(emailLbl);
        componentBoldList.add(cardIdLbl);
        componentBoldList.add(postalCodeLbl);
        componentBoldList.add(cityNameLbl);
        componentBoldList.add(streetAndBuildingLbl);
        componentBoldList.add(salaryLbl);
        componentBoldList.add(isFullTimeLbl);
        componentBoldList.add(passLbl);

        for (Component c: componentPlainList) {c.setFont(Auxiliary.panelPlainFont);}
        for (Component c: componentBoldList) {c.setFont(Auxiliary.panelFont); }
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(10,10,350,50);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
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
        updateAdminBtn.addActionListener(e -> {
            if(Validation.checkIfEmailOK(emailTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny email");
            else if(Validation.checkIfPostalCodeOK(cityNameTxt.getText())==false)
                JOptionPane.showMessageDialog(this, "Niepoprawny kod pocztowy");
            else if(Validation.checkIfInteger(cardIdTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny numer karty użytkownika");
            else if(firstNameTxt.getText().equals("") || lastNameTxt.getText().equals("")|| emailTxt.getText().equals("")||postalCodeTxt.getText().equals("")||streetAndBuildingTxt.getText().equals("")||salaryTxt.getText().equals(""))
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
                String adminSalary = salaryTxt.getText();
                boolean isFullTimeAdmin = isFullTimeChbx.isSelected();
                adminDBService.updateAdminInDB(userId,adminSalary,isFullTimeAdmin);
                JOptionPane.showMessageDialog(this, "Dane administratora zaktualizowane poprawnie");
            }
        });

    }

    private void actionSearchAdminBtn() {
        searchAdminBtn.addActionListener(e -> {
            if (Validation.checkIfInteger(cardIdTxt.getText())) {
                //setComponentsEditability(false);
                int cardId = Integer.parseInt(cardIdTxt.getText());
                System.out.println(cardId);
                Admin admin = adminDBService.readAdminFromDB(cardId);
                System.out.println(admin);
                if (admin.getIdAdmin() != 0) {
                    updateAdminBtn.setVisible(true);
                    setCompVisibility(true);
                    rectLabel.setBounds(10,10,350,420);
                    firstNameTxt.setText(admin.getFirstName());
                    lastNameTxt.setText(admin.getLastName());
                    emailTxt.setText(admin.getEmail());
                    postalCodeTxt.setText(admin.getPostalCode());
                    cityNameTxt.setText(cityDBService.getCityName(admin.getPostalCode()));
                    streetAndBuildingTxt.setText(admin.getStreetBuilding());
                    salaryTxt.setText(admin.getSalary());
                    isFullTimeChbx.setSelected(admin.isFullTime());
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
        searchAdminBtn = new CustButton();
        searchAdminBtn.setText("Wyszukaj");
        searchAdminBtn.setBounds(400, 20, 200, 30);
    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 30);
    }

    private void createUpdateBtn() {
        updateAdminBtn = new CustButton();
        updateAdminBtn.setText("Aktualizuj dane");
        updateAdminBtn.setVisible(false);
        updateAdminBtn.setBounds(400, 150, 200, 30);
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
        add(isFullTimeLbl);
        add(isFullTimeChbx);
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
        createIsFullTimeLbl();
        createIsFullTimeChbx();
        createSalaryLbl();
        createSalaryTxt();
    }

    private void createIsFullTimeLbl(){
        isFullTimeLbl = new JLabel();
        isFullTimeLbl.setText("Pełny etat");
        isFullTimeLbl.setBounds(20, 380, 100, 30);
    }

    private void createIsFullTimeChbx(){
        isFullTimeChbx = new JCheckBox();
        isFullTimeChbx.setSelected(false);
        isFullTimeChbx.setBounds(150, 380, 30, 30);
        isFullTimeChbx.setOpaque(false);
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
        isFullTimeLbl.setVisible(visibility);
        salaryLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        passField.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
        isFullTimeChbx.setVisible(visibility);
        salaryTxt.setVisible(visibility);
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }


}
