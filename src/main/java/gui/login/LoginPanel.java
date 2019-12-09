package gui.login;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {

    private JLabel cardNrLbl, passLbl;
    private JTextField cardNrTxt;
    private JPasswordField passTxt;
    private JButton libInfoBtn, loginBtn, registerBtn;

    public LoginPanel(){
        setLayout(null);
        createAllButtons();
        createAllLabels();
        addAllButtons();
        addAllLabels();
    }

    private void addAllButtons(){
        add(libInfoBtn);
        add(loginBtn);
        add(registerBtn);
    }

    private void createAllButtons(){
        createLibInfoBtn();
        createLoginBtn();
        createRegisterBtn();
    }
    private void addAllLabels(){
        add(cardNrLbl);
        add(cardNrTxt);
        add(passLbl);
        add(passTxt);
    };
    private void createAllLabels(){
        createCardNrLbl();
        createCardNrTxt();
        createPassLbl();
        createPassTxt();
    }

    private void createCardNrLbl(){
        cardNrLbl = new JLabel();
        cardNrLbl.setText("Numer karty");
        cardNrLbl.setBounds(250,100,200,50);
    }

    private void createCardNrTxt(){
        cardNrTxt = new JTextField();
        cardNrTxt.setBounds(250,200,200,50);
    }

    private void createPassLbl(){
        passLbl = new JLabel();
        passLbl.setText("Hasło");
        passLbl.setBounds(250,300,200,50);
    }

    private void createPassTxt(){
        passTxt = new JPasswordField();
        passTxt.setBounds(250,400,200,50);
    }

    private void createLibInfoBtn(){
        libInfoBtn = new JButton();
        libInfoBtn.setText("<html><center>"+"INFO"+"<br>"+"O"+"<br>"+"BIBLIOTECE"+"</center></html>");
        libInfoBtn.setBounds(500,70,150,150);
        libInfoBtn.setFocusPainted(false);
    }
    private void createLoginBtn(){
        loginBtn = new JButton();
        loginBtn.setText("ZALOGUJ");
        loginBtn.setBounds(150,500,150,50);
    }
    private void createRegisterBtn(){
        registerBtn = new JButton();
        registerBtn.setText("ZAREJESTRUJ");
        registerBtn.setBounds(400,500,150,50);
    }

    public void setCardNrTxt(String cardNr){this.cardNrTxt.setText(cardNr);}
    public void setPassTxt(String pass){this.passTxt.setText(pass);}
    public JButton getLibInfoBtn(){return libInfoBtn;}
    public JButton getLoginBtn(){return loginBtn;}
    public JButton getRegisterBtn(){return registerBtn;}
}
