package gui;

import card.CardDBServiceImpl;
import card.ICardDBService;
import city.CityDBServiceImpl;
import city.ICityDBService;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;

public class UserPanel extends JPanel {
    private JLabel firstNameLbl, lastNamelbl, emailLbl, passLbl, cardIdLbl, postalCodeLbl, cityNameLbl, streetAndBuildingLbl;
    private JTextField firstNameTxt, lastNameTxt, emailTxt, passTxt, cardIdTxt, postalCodeTxt, cityNameTxt, streetAndBuildingTxt;
    private JButton searchBtn, updateBtn, deleteBtn, showAllBtn, addBtn;
    private int fieldLength = 200;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private ICardDBService cardDBService = new CardDBServiceImpl();
    private ICityDBService cityDBService = new CityDBServiceImpl();

    UserPanel(){

        setLayout(null);

        createAllLabels();
        addAllLabels();
        createAllButtons();
        addAllButtons();
        actionSearchBtn();
    }

    private void actionSearchBtn(){
        searchBtn.addActionListener(e -> {
            int cardId = Integer.parseInt(cardIdTxt.getText());
            System.out.println(cardId);
            User user = userDBService.readUserFromDB(cardId);
            System.out.println(user);
            firstNameTxt.setText(user.getFirstName());
            lastNameTxt.setText(user.getLastName());
            emailTxt.setText(user.getEmail());
            postalCodeTxt.setText(user.getPostalCode());
            cityNameTxt.setText(cityDBService.getCityName(user.getPostalCode()));
            streetAndBuildingTxt.setText(user.getStreetBuilding());
        });
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
    }

    private void createAllButtons(){
        createSearchBtn();
        createUpdateBtn();
        createDeleteBtn();
        createShowAllBtn();
        createAddBtn();
    }

    private void addAllButtons(){
        add(searchBtn);
        add(updateBtn);
        add(deleteBtn);
        add(showAllBtn);
        add(addBtn);
    }

    private void createAddBtn(){
        addBtn = new JButton();
        addBtn.setText("Dodaj użytkownika");
        addBtn.setBounds(400,420,200,50);
    }

    private void createSearchBtn(){
        searchBtn = new JButton();
        searchBtn.setText("Wyszukaj użytkownika");
        searchBtn.setBounds(400,20,200,50);
    }

    private void createUpdateBtn(){
        updateBtn = new JButton();
        updateBtn.setText("Aktualizuj użytkownika");
        updateBtn.setBounds(400,120,200,50);
    }

    private void createDeleteBtn(){
        deleteBtn = new JButton();
        deleteBtn.setText("Usuń użytkownika");
        deleteBtn.setBounds(400,220,200,50);
    }

    private void createShowAllBtn(){
        showAllBtn = new JButton();
        showAllBtn.setText("Pokaż wszystkich");
        showAllBtn.setBounds(400,320,200,50);
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


}
