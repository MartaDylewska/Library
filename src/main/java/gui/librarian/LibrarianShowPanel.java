package gui.librarian;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import config.Validation;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;
import librarian.ILibrarianDBService;
import librarian.Librarian;
import librarian.LibrarianDBServiceImpl;
import user.IUserDBService;
import user.UserDBServiceImpl;

import javax.swing.*;

public class LibrarianShowPanel extends JPanel {

    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JLabel salaryLbl, dateEmploymentLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, passTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JTextField salaryTxt, dateEmploymentTxt;
    private JButton searchLibrarianBtn, returnBtn;
    private int fieldLength = 200;
    private JLabel imageLbl;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();
    private ILibrarianDBService librarianDBService = new LibrarianDBServiceImpl();

    public LibrarianShowPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        setCompVisibility(false);
        createSearchBtn();
        add(searchLibrarianBtn);
        actionSearchLibrarianBtn();
        createReturnBtn();
        add(returnBtn);
        setComponentsEditability(false);
    }

    private void actionSearchLibrarianBtn(){
        searchLibrarianBtn.addActionListener(e -> {
            if (Validation.checkIfInteger(cardIdTxt.getText())) {
                //setComponentsEditability(false);
                int cardId = Integer.parseInt(cardIdTxt.getText());
                System.out.println(cardId);
                Librarian librarian = librarianDBService.readLibrarianFromDB(cardId);
                System.out.println(librarian);
                if (librarian.getIdLibrarian() != 0) {
                    setCompVisibility(true);
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

    private void createSearchBtn(){
        searchLibrarianBtn = new JButton();
        searchLibrarianBtn.setText("Wyszukaj");
        searchLibrarianBtn.setBounds(400,20,200,50);
    }

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(400,300,200,50);
    }

    private void addAllLabels(){
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
        add(dateEmploymentLbl);
        add(dateEmploymentTxt);
    }

    private void createAllLabels(){
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
        createDateEmplLbl();
        createDateEmplTxt();
        createSalaryLbl();
        createSalaryTxt();
    }

    private void createDateEmplLbl(){
        dateEmploymentLbl = new JLabel();
        dateEmploymentLbl.setText("Data zatrudnienia");
        dateEmploymentLbl.setBounds(20, 340, 100, 30);
    }

    private void createDateEmplTxt(){
        dateEmploymentTxt = new JTextField();
        dateEmploymentTxt.setBounds(150, 340, fieldLength, 30);
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


    private void createImgLabel(){
        imageLbl = new JLabel();
        IPosterDBService posterDBService = new PosterDBServiceImpl();
        Poster poster = posterDBService.readImage("poster2.png");
        ImageIcon icon = new ImageIcon(poster.getImgBytes());
        imageLbl.setIcon(icon);
        imageLbl.setBounds(200,150,200,200);
    }

    private void createStreetAndBuildingLbl(){
        streetAndBuildingLbl = new JLabel();
        streetAndBuildingLbl.setText("Ulica/nr");
        streetAndBuildingLbl.setBounds(20,260,100,30);
    }

    private void createStreetAndBuildingTxt(){
        streetAndBuildingTxt = new JTextField();
        streetAndBuildingTxt.setBounds(150,260,fieldLength,30);
    }

    private void createCityNameLbl(){
        cityNameLbl = new JLabel();
        cityNameLbl.setText("Miasto");
        cityNameLbl.setBounds(20,220,100,30);
    }

    private void createCityNameTxt(){
        cityNameTxt = new JTextField();
        cityNameTxt.setBounds(150,220,fieldLength,30);
        cityNameTxt.setEditable(false);
    }

    private void createPostalCodeLbl(){
        postalCodeLbl = new JLabel();
        postalCodeLbl.setText("Kod pocztowy");
        postalCodeLbl.setBounds(20,180,100,30);
    }

    private void createPostalCodeTxt(){
        postalCodeTxt = new JTextField();
        postalCodeTxt.setBounds(150,180,fieldLength,30);
    }
    private void createEmailLbl(){
        emailLbl = new JLabel();
        emailLbl.setText("Email");
        emailLbl.setBounds(20,140,100,30);
    }

    private void createEmailTxt(){
        emailTxt = new JTextField();
        emailTxt.setBounds(150,140,fieldLength,30);
    }

    private void createLastnameLbl(){
        lastNamelbl = new JLabel();
        lastNamelbl.setText("Nazwisko");
        lastNamelbl.setBounds(20,100,100,30);
    }

    private void createLastNameTxt(){
        lastNameTxt = new JTextField();
        lastNameTxt.setBounds(150,100,fieldLength,30);
    }

    private void createFirstnameLbl(){
        firstNameLbl = new JLabel();
        firstNameLbl.setText("Imię");
        firstNameLbl.setBounds(20,60,100,30);
    }

    private void createFirstNameTxt(){
        firstNameTxt = new JTextField();
        firstNameTxt.setBounds(150,60,fieldLength,30);
    }

    private void createCardidLbl(){
        cardIdLbl = new JLabel();
        cardIdLbl.setText("Numer karty");
        cardIdLbl.setBounds(20,20,100,30);
    }

    private void createCardIdTxt(){
        cardIdTxt = new JTextField();
        cardIdTxt.setBounds(150,20,fieldLength,30);
    }

    private void setCompVisibility(boolean visibility){
        firstNameLbl.setVisible(visibility);
        lastNamelbl.setVisible(visibility);
        emailLbl.setVisible(visibility);
        postalCodeLbl.setVisible(visibility);
        cityNameLbl.setVisible(visibility);
        streetAndBuildingLbl.setVisible(visibility);
        salaryLbl.setVisible(visibility);
        dateEmploymentLbl.setVisible(visibility);
        firstNameTxt.setVisible(visibility);
        lastNameTxt.setVisible(visibility);
        emailTxt.setVisible(visibility);
        //passTxt.setVisible(visibility);
        //cardIdTxt.setVisible(visibility);
        postalCodeTxt.setVisible(visibility);
        cityNameTxt.setVisible(visibility);
        streetAndBuildingTxt.setVisible(visibility);
        salaryTxt.setVisible(visibility);
        dateEmploymentTxt.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability){

        firstNameTxt.setEditable(editability);
        lastNameTxt.setEditable(editability);
        emailTxt.setEditable(editability);
        // passTxt.setEnabled(editability);
        //cardIdTxt.setEnabled(editability);
        postalCodeTxt.setEditable(editability);
        cityNameTxt.setEditable(editability);
        streetAndBuildingTxt.setEditable(editability);
        salaryTxt.setEditable(editability);
        dateEmploymentTxt.setEditable(editability);
    }

    public JButton getReturnBtn(){
        return returnBtn;
    }
}
