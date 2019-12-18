package gui.login;

import gui.Auxiliary;
import gui.MFrame;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginPanel extends JPanel {

    private JLabel cardNrLbl, passLbl;
    private JTextField cardNrTxt;
    private JPasswordField passTxt;
    private JButton libInfoBtn, loginBtn, registerBtn;
    private ImageIcon libIcon;
    private JLabel imageLabel;
    private static final String libIconName = "lib.png";

    public LoginPanel(){
        setLayout(null);
        createAllButtons();
        createAllLabels();
        addAllButtons();
        addAllLabels();
        setFont();
        setBorder();
        createImageLabel();
        add(imageLabel);
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

    private void createImageLabel(){
        imageLabel = new JLabel();
        setImage();
        imageLabel.setBounds(0,0,700,600);
    }

    private void addAllButtons(){
        //add(libInfoBtn);
        add(loginBtn);
        add(registerBtn);
    }

    private void createAllButtons(){
        //createLibInfoBtn();
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
        loginBtn.setBounds(100,500,200,50);
    }
    private void createRegisterBtn(){
        registerBtn = new JButton();
        registerBtn.setText("ZAREJESTRUJ");
        registerBtn.setBounds(400,500,200,50);
    }

    public void setCardNrTxt(String cardNr){this.cardNrTxt.setText(cardNr);}
    public void setPassTxt(String pass){this.passTxt.setText(pass);}
    public JButton getLibInfoBtn(){return libInfoBtn;}
    public JButton getLoginBtn(){return loginBtn;}
    public JButton getRegisterBtn(){return registerBtn;}
    public JTextField getCardNrTxt(){return cardNrTxt;}
    public JPasswordField getPassTxt(){return passTxt;}
    public String getPasswordToString(JPasswordField passTxt){
        StringBuilder sb = new StringBuilder();
        for (Character c: passTxt.getPassword())
            sb.append(c);
        return sb.toString();
    }
    private void setImage(){
        try {
            Class<MFrame> mFrameClass = MFrame.class;
            //libIcon = new ImageIcon(mFrameClass.getResource("resources/lib.png"));
            libIcon = new ImageIcon(this.getClass().getClassLoader().getResource("lib.png"));
        } catch (Exception e) {
            System.out.println("Problem with picture: lib.png");
            e.printStackTrace();
        }
        imageLabel.setIcon(libIcon);
    }
}
