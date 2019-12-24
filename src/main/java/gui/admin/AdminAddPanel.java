package gui.admin;

import admin.Admin;
import admin.AdminDBServiceImpl;
import admin.IAdminDBService;
import card.Card;
import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;
import librarian.ILibrarianDBService;
import librarian.LibrarianDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class AdminAddPanel extends JPanel {
    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JLabel salaryLbl, isFullTimeLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JTextField salaryTxt;
    private JCheckBox isFullTimeChbx;
    private JPasswordField passField;
    private CustButton addAdminBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel rectLabel;
    private List<Component> componentPlainList = new ArrayList<>();
    private List<Component> componentBoldList = new ArrayList<>();

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IAdminDBService adminDBService = new AdminDBServiceImpl();

    public AdminAddPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(true);
        cardIdTxt.setVisible(false);
        cardIdLbl.setVisible(false);
        createReturnBtn();
        add(returnBtn);
        createAddBtn();
        add(addAdminBtn);
        setPostalCodeKL();
        actionAddAdminBtn();
        createRectLabel();
        add(rectLabel);
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
        //firstNameLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(lastNamelbl);
        //lastNamelbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(emailLbl);
        //emailLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(passLbl);
        componentBoldList.add(cardIdLbl);
        // cardIdLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(postalCodeLbl);
        //postalCodeLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(cityNameLbl);
        //cityNameLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(streetAndBuildingLbl);
        // streetAndBuildingLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(salaryLbl);
        //salaryLbl.setFont(Auxiliary.panelFont);
        componentBoldList.add(isFullTimeLbl);
        //dateEmploymentLbl.setFont(Auxiliary.panelFont);

        for (Component c: componentPlainList) {
            c.setFont(Auxiliary.panelPlainFont);
        }
        for (Component c: componentBoldList) {
            c.setFont(Auxiliary.panelFont);
        }

    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(10,10,350,420);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }
    private void setPostalCodeKL() {
        postalCodeTxt.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) { }
            @Override
            public void keyPressed(KeyEvent e) { }
            @Override
            public void keyReleased(KeyEvent e) { cityNameTxt.setText(cityDBService.getCityName(postalCodeTxt.getText())); }
        });
    }
//poprawić to
    private void actionAddAdminBtn() {
        addAdminBtn.addActionListener(e -> {
            if (Validation.checkIfEmailOK(emailTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny email");
            else if (Validation.checkIfPostalCodeOK(cityNameTxt.getText()) == false)
                JOptionPane.showMessageDialog(this, "Niepoprawny kod pocztowy");
            else if (firstNameTxt.getText().equals("") || lastNameTxt.getText().equals("") || emailTxt.getText().equals("") || postalCodeTxt.getText().equals("") || streetAndBuildingTxt.getText().equals("") || passField.getPassword().length == 0 || salaryTxt.getText().equals(""))
                JOptionPane.showMessageDialog(this, "Proszę wypełnić wszystkie pola");
            else {
                cardDBService.addCardInDB();
                Card cardForNewUser = cardDBService.readLastCardFromDB();
                cardIdTxt.setText(String.valueOf(cardForNewUser.getIdCard()));
                cardIdTxt.setVisible(true);
                cardIdLbl.setVisible(true);

                User user = new User();
                user.setCardNumber(cardForNewUser.getIdCard());
                user.setFirstName(firstNameTxt.getText());
                user.setLastName(lastNameTxt.getText());
                StringBuilder pass = new StringBuilder();
                for (char c : passField.getPassword())
                    pass.append(c);
                String userPass;
                if (pass.toString().equals("------"))
                    userPass = user.getPassword();
                else
                    userPass = pass.toString();
                user.setPassword(userPass);
                user.setEmail(emailTxt.getText());
                user.setStreetBuilding(streetAndBuildingTxt.getText());
                user.setPostalCode(postalCodeTxt.getText());
                userDBService.addUserInDB(user);

                int idLastUser = userDBService.readLastUserIdFromDB();
                Admin admin = new Admin();
                admin.setUserId(idLastUser);
                admin.setFullTime(isFullTimeChbx.isSelected());
                admin.setSalary(salaryTxt.getText());
                adminDBService.addAdmin(admin);

                JOptionPane.showMessageDialog(this, "Nowy administrator został dodany do bazy \n Numer karty: " + cardForNewUser.getIdCard());
                setComponentsEditability(false);
                cardIdTxt.setEditable(false);
            }
        });

    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400, 300, 200, 30);
    }

    private void createAddBtn() {
        addAdminBtn = new CustButton();
        addAdminBtn.setText("Wprowadź dane");
        addAdminBtn.setBounds(400, 150, 200, 30);
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
        salaryTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createPassLbl() {
        passLbl = new JLabel();
        passLbl.setText("Hasło");
        passLbl.setBounds(20, 300, 100, 30);
    }

    private void createPassTxt() {
        passField = new JPasswordField();
        passField.setBounds(150, 300, fieldLength, 30);
        passField.setBorder(Auxiliary.blackBorder());
    }

    private void createStreetAndBuildingLbl() {
        streetAndBuildingLbl = new JLabel();
        streetAndBuildingLbl.setText("Ulica/nr");
        streetAndBuildingLbl.setBounds(20, 260, 100, 30);
    }

    private void createStreetAndBuildingTxt() {
        streetAndBuildingTxt = new JTextField();
        streetAndBuildingTxt.setBounds(150, 260, fieldLength, 30);
        streetAndBuildingTxt.setBorder(Auxiliary.blackBorder());
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
        cityNameTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createPostalCodeLbl() {
        postalCodeLbl = new JLabel();
        postalCodeLbl.setText("Kod pocztowy");
        postalCodeLbl.setBounds(20, 180, 100, 30);
    }

    private void createPostalCodeTxt() {
        postalCodeTxt = new JTextField();
        postalCodeTxt.setBounds(150, 180, fieldLength, 30);
        postalCodeTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createEmailLbl() {
        emailLbl = new JLabel();
        emailLbl.setText("Email");
        emailLbl.setBounds(20, 140, 100, 30);
    }

    private void createEmailTxt() {
        emailTxt = new JTextField();
        emailTxt.setBounds(150, 140, fieldLength, 30);
        emailTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createLastnameLbl() {
        lastNamelbl = new JLabel();
        lastNamelbl.setText("Nazwisko");
        lastNamelbl.setBounds(20, 100, 100, 30);
    }

    private void createLastNameTxt() {
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(150, 100, fieldLength, 30);
        lastNameTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createFirstnameLbl() {
        firstNameLbl = new JLabel();
        firstNameLbl.setText("Imię");
        firstNameLbl.setBounds(20, 60, 100, 30);

    }

    private void createFirstNameTxt() {
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(150, 60, fieldLength, 30);
        firstNameTxt.setBorder(Auxiliary.blackBorder());
    }

    private void createCardidLbl() {
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(20, 20, 100, 30);
    }

    private void createCardIdTxt() {
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(150, 20, fieldLength, 30);
        cardIdTxt.setBorder(Auxiliary.blackBorder());
    }

    private void setCompVisibility(boolean visibility) {
        firstNameLbl.setVisible(visibility);
        lastNamelbl.setVisible(visibility);
        emailLbl.setVisible(visibility);
        postalCodeLbl.setVisible(visibility);
        cityNameLbl.setVisible(visibility);
        streetAndBuildingLbl.setVisible(visibility);
        salaryLbl.setVisible(visibility);
        isFullTimeLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        passField.setVisible(visibility);
        passLbl.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
        salaryTxt.setVisible(visibility);
        isFullTimeChbx.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        firstNameTxt.setEditable(editability);
        lastNameTxt.setEditable(editability);
        emailTxt.setEditable(editability);
        passField.setEditable(editability);
        cardIdTxt.setEditable(editability);
        postalCodeTxt.setEditable(editability);
        cityNameTxt.setEditable(editability);
        streetAndBuildingTxt.setEditable(editability);
        salaryTxt.setEditable(editability);
        isFullTimeChbx.setEnabled(editability);
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}
