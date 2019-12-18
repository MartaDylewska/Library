package gui.login;


import gui.Auxiliary;
import gui.general.MyButton;

import javax.swing.*;
import java.util.Objects;


public class LoginPanel extends JPanel {

    private JLabel cardNrLbl, passLbl;
    private JTextField cardNrTxt;
    private ImageIcon image;
    private JPasswordField passTxt;

    private MyButton libInfoBtn, loginBtn, registerBtn;


    public LoginPanel(){

        setLayout(null);
        createAllButtons();
        createAllLabels();
        addAllButtons();
        addAllLabels();

        setFont();
        setBorder();
    }

    private void setBorder(){
        cardNrLbl.setBorder(Auxiliary.blackBorder());
        passLbl.setBorder(Auxiliary.blackBorder());
        cardNrTxt.setBorder(Auxiliary.blackBorder());
        passTxt.setBorder(Auxiliary.blackBorder());
        loginBtn.setBorder(Auxiliary.blackBorder());
        registerBtn.setBorder(Auxiliary.blackBorder());
    }

    private void setFont(){
        cardNrLbl.setFont(Auxiliary.panelFont);
        passLbl.setFont(Auxiliary.panelFont);
        cardNrTxt.setFont(Auxiliary.panelFont);
        loginBtn.setFont(Auxiliary.panelFont);
        registerBtn.setFont(Auxiliary.panelFont);
    }


    private void addAllButtons(){
        add(loginBtn);
        add(registerBtn);
    }

    private void createAllButtons(){
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
        cardNrLbl.setText("Numer karty:");
        cardNrLbl.setBounds(250,140,200,30);
    }

    private void createCardNrTxt(){
        cardNrTxt = new JTextField();
        cardNrTxt.setBounds(250,180,200,30);
    }

    private void createPassLbl(){
        passLbl = new JLabel();
        passLbl.setText("Hasło:");
        passLbl.setBounds(250,220,200,30);
    }
    private void createPassTxt(){
        passTxt = new JPasswordField();
        passTxt.setBounds(250,260,200,30);
    }

    /*private void createLibInfoBtn(){
        libInfoBtn = new MyButton();
        libInfoBtn.setText("<html><center>"+"INFO"+"<br>"+"O"+"<br>"+"BIBLIOTECE"+"</center></html>");
        libInfoBtn.setBounds(500,70,150,150);
        libInfoBtn.setFocusPainted(false);
    }*/
    private void createLoginBtn(){

        loginBtn = new MyButton(true);
        loginBtn.setText("Zaloguj się");
        loginBtn.setBounds(250,300,200,30);
    }
    private void createRegisterBtn(){
        registerBtn = new MyButton(false);
        registerBtn.setText("załóż konto");
        registerBtn.setBounds(250,340,200,30);
    }
    public void setCardNrTxt(String cardNr){this.cardNrTxt.setText(cardNr);}
    public void setPassTxt(String pass){this.passTxt.setText(pass);}
//    public MyButton getLibInfoBtn(){return libInfoBtn;}
    public MyButton getLoginBtn(){return loginBtn;}
    public MyButton getRegisterBtn(){return registerBtn;}
    public JTextField getCardNrTxt(){return cardNrTxt;}
    public JPasswordField getPassTxt(){return passTxt;}
    public String getPasswordToString(JPasswordField passTxt){
        StringBuilder sb = new StringBuilder();
        for (Character c: passTxt.getPassword())
            sb.append(c);
        return sb.toString();
    }

}
