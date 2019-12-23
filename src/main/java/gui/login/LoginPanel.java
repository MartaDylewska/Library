package gui.login;
import gui.Auxiliary;
import gui.general.CustButton;
import javax.swing.*;
import java.awt.*;


public class LoginPanel extends JPanel {

    private JLabel cardNrLbl, passLbl, imageLabel;
    private JTextField cardNrTxt;
    private ImageIcon image;
    private JPasswordField passTxt;
    private JLabel rectLabel;

    private CustButton loginBtn, registerBtn;


    public LoginPanel(){

        setLayout(null);
        createAllButtons();
        createAllLabels();
        addAllButtons();
        addAllLabels();
        createImage();
        setFont();
        setBorder();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(200,20,300,380);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void setBorder(){
        cardNrTxt.setBorder(Auxiliary.blackBorder());
        passTxt.setBorder(Auxiliary.blackBorder());
        loginBtn.setBorder(Auxiliary.blackBorder());
        registerBtn.setBorder(Auxiliary.blackBorder());
    }

    private void setFont(){
        cardNrLbl.setFont(Auxiliary.panelFont);
        passLbl.setFont(Auxiliary.panelFont);
        cardNrTxt.setFont(Auxiliary.panelPlainFont);
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
        cardNrLbl.setText("Numer karty");
        cardNrLbl.setBounds(250,150,200,30);
    }

    private void createCardNrTxt(){
        cardNrTxt = new JTextField();
        cardNrTxt.setBounds(250,180,200,30);
    }

    private void createPassLbl(){
        passLbl = new JLabel();
        passLbl.setText("Hasło");
        passLbl.setBounds(250,230,200,30);
    }
    private void createPassTxt(){
        passTxt = new JPasswordField();
        passTxt.setBounds(250,260,200,30);
    }
    private void createImage(){
        imageLabel = new JLabel();
        imageLabel.setBounds(250,40,200,100);
        try {
            image = new ImageIcon("src/main/resources/logo.png");
        } catch (Exception e) {
            System.out.println("Problem with picture logo.png.");
        }
        imageLabel.setIcon(image);
        add(imageLabel);
    }

    private void createLoginBtn(){

        loginBtn = new CustButton();
        loginBtn.setText("Zaloguj się");
        loginBtn.setBounds(250,300,200,30);
    }
    private void createRegisterBtn(){
        registerBtn = new CustButton();
        registerBtn.setText("Załóż konto");
        registerBtn.setBounds(250,340,200,30);
    }
    public void setCardNrTxt(String cardNr){this.cardNrTxt.setText(cardNr);}
    public void setPassTxt(String pass){this.passTxt.setText(pass);}
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


}
