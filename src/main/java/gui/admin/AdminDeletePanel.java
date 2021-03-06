package gui.admin;

import admin.Admin;
import admin.AdminDBServiceImpl;
import admin.IAdminDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;
import librarian.ILibrarianDBService;
import librarian.Librarian;
import librarian.LibrarianDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDeletePanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JLabel salaryLbl, isFullTimeLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, passTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JTextField salaryTxt;
    private JCheckBox isFullTimeChbx;
    private CustButton searchAdminBtn, deleteAdminBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel rectLabel;
    private List<Component> componentPlainList = new ArrayList<>();
    private List<Component> componentBoldList = new ArrayList<>();
    private AdminEntryPanel adminEntryPanel;
    private int currentAdminNumber;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private IAdminDBService adminDBService = new AdminDBServiceImpl();

    public AdminDeletePanel(AdminEntryPanel adminEntryPanel){
        this.adminEntryPanel = adminEntryPanel;
        currentAdminNumber = Integer.parseInt(adminEntryPanel.getCardNrLbl().getText());
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createSearchBtn();
        add(searchAdminBtn);
        actionSearchAdminBtn();
        createReturnBtn();
        add(returnBtn);
        setComponentsEditability(false);
        createDeleteBtn();
        add(deleteAdminBtn);
        actionDeleteAdminBtn();
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

    private void actionDeleteAdminBtn() {

        deleteAdminBtn.addActionListener(e -> {
            int cardId = Integer.parseInt(cardIdTxt.getText());
            User user = userDBService.readUserFromDB(cardId);
            if(currentAdminNumber == Integer.parseInt(cardIdTxt.getText()))
                JOptionPane.showMessageDialog(this,"Nie można usunąć obecnie używanego konta");
            else{
            adminDBService.deleteAdminFromDB(user.getIdUser());
            //userDBService.deleteUserFromDB(cardId);
            firstNameTxt.setText("");
            lastNameTxt.setText("");
            emailTxt.setText("");
            postalCodeTxt.setText("");
            cityNameTxt.setText("");
            streetAndBuildingTxt.setText("");
            cardIdTxt.setText("");
            salaryTxt.setText("");
            isFullTimeChbx.setSelected(false);
            setCompVisibility(false);
            JOptionPane.showMessageDialog(this,
                    "Administrator usunięty z systemu");}
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
                    deleteAdminBtn.setVisible(true);
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

    private void createDeleteBtn() {
        deleteAdminBtn = new CustButton();
        deleteAdminBtn.setText("Usuń administratora");
        deleteAdminBtn.setVisible(false);
        deleteAdminBtn.setBounds(400, 150, 200, 30);
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
        createIsFullTimeLbl();
        createIsFullTimeChbx();
        createSalaryLbl();
        createSalaryTxt();
    }

    private void createIsFullTimeLbl(){
        isFullTimeLbl = new JLabel();
        isFullTimeLbl.setText("Pełny etat");
        isFullTimeLbl.setBounds(20, 340, 100, 30);
    }

    private void createIsFullTimeChbx(){
        isFullTimeChbx = new JCheckBox();
        isFullTimeChbx.setBounds(150, 340, 30, 30);
        isFullTimeChbx.setOpaque(false);
    }

    private void createSalaryLbl(){
        salaryLbl = new JLabel();
        salaryLbl.setText("Pensja");
        salaryLbl.setBounds(20, 300, 100, 30);
    }

    private void createSalaryTxt() {
        salaryTxt = new JTextField();
        salaryTxt.setBounds(150, 300, fieldLength, 30);
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
        salaryLbl.setVisible(visibility);
        isFullTimeLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        //passTxt.setVisible(visibility);
        //cardIdTxt.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
        salaryTxt.setVisible(visibility);
        isFullTimeChbx.setVisible(visibility);
        //rectLabel.setVisible(visibility);
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
        salaryTxt.setEditable(editability);
        isFullTimeChbx.setEnabled(editability);
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}
