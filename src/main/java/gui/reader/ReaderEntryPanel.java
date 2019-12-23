package gui.reader;

import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;

import javax.swing.*;
import java.awt.*;

public class ReaderEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private CustButton updateBtn, doBookingBtn, lendingsBtn,joinEventBtn, checkEventBtn;
    private CustButton returnBtn;
    private int firstBtnY = 120;
    private int distanceY = 40;
    private JLabel rectLabel;

    public ReaderEntryPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
    }

    private void createNameLbl(){
        nameLbl = new JLabel();
        nameLbl.setText("-------");
        nameLbl.setBounds(250,40,200,30);
        nameLbl.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        nameLbl.setForeground(new Color(78,52,46,255));
    }

    private void createCardNrLbl(){
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250,60,200,30);
        cardNrLbl.setText("----------");
        cardNrLbl.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        cardNrLbl.setForeground(new Color(78,52,46,255));
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        //rectLabel.setText("aa");
        rectLabel.setBounds(200,20,300,380);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void addAllBtns(){
        add(updateBtn);
        add(doBookingBtn);
        add(joinEventBtn);
        add(lendingsBtn);
        add(checkEventBtn);
        add(returnBtn);
    }

    private void createAllBtns(){
        createCheckEventBtn();
        createDoBookingBtn();
        createJoinEventBtn();
        createLendingsBtn();
        createUpdateBtn();
        createReturnBtn();
    }

    private void createUpdateBtn(){
        updateBtn = new CustButton();
        updateBtn.setText("Aktualizuj profil");
        updateBtn.setBounds(250,firstBtnY,200,30);
    }

    private void createDoBookingBtn(){
        doBookingBtn = new CustButton();
        doBookingBtn.setText("Zarezerwuj książkę");
        doBookingBtn.setBounds(250,firstBtnY + distanceY,200,30);
    }

    private void createLendingsBtn(){
        lendingsBtn = new CustButton();
        lendingsBtn.setText("Sprawdź wypożyczenia");
        lendingsBtn.setBounds(250,firstBtnY + 2*distanceY,200,30);
    }
    private void createJoinEventBtn(){
        joinEventBtn = new CustButton();
        joinEventBtn.setText("Dołącz do wydarzenia");
        joinEventBtn.setBounds(250, firstBtnY + 3*distanceY, 200, 30);
    }

    private void createCheckEventBtn(){
        checkEventBtn = new CustButton();
        checkEventBtn.setText("Sprawdź Twoje wydarzenia");
        checkEventBtn.setBounds(250, firstBtnY + 4*distanceY, 200, 30);
    }

    private void createReturnBtn(){
        returnBtn = new CustButton();
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(250,firstBtnY+5*distanceY,200,30);
    }

    private void addAllLabels(){
        add(cardNrLbl);
        add(nameLbl);
    }

    private void createAllLabels(){
        createCardNrLbl();
        createNameLbl();
    }

    public CustButton getUpdateBtn(){return updateBtn;}
    public CustButton getDoBookingBtn(){return doBookingBtn;}
    public CustButton getLendingsBtn(){return lendingsBtn;}
    public CustButton getJoinEventBtn(){return joinEventBtn;}
    public CustButton getCheckEventBtn(){return checkEventBtn;}
    public CustButton getReturnBtn(){return returnBtn;}
    public void setNameLbl(String name){this.nameLbl.setText(name);}
    public void setCardNrLbl(String cardNr){this.cardNrLbl.setText(cardNr);}
    public JLabel getCardNrLbl(){return cardNrLbl;}
}
