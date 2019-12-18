package gui.reader;

import gui.general.MyButton;

import javax.swing.*;

public class ReaderEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private MyButton updateBtn, doBookingBtn, lendingsBtn,joinEventBtn, checkEventBtn;
    private MyButton returnBtn;
    private int firstBtnY = 120;
    private int distanceY = 40;

    public ReaderEntryPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
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
        updateBtn = new MyButton(true);
        updateBtn.setText("Aktualizuj profil");
        updateBtn.setBounds(250,firstBtnY,200,30);
    }

    private void createDoBookingBtn(){
        doBookingBtn = new MyButton(true);
        doBookingBtn.setText("Zarezerwuj książkę");
        doBookingBtn.setBounds(250,firstBtnY + distanceY,200,30);
    }

    private void createLendingsBtn(){
        lendingsBtn = new MyButton(true);
        lendingsBtn.setText("Sprawdź wypożyczenia");
        lendingsBtn.setBounds(250,firstBtnY + 2*distanceY,200,30);
    }
    private void createJoinEventBtn(){
        joinEventBtn = new MyButton(true);
        joinEventBtn.setText("Dołącz do wydarzenia");
        joinEventBtn.setBounds(250, firstBtnY + 3*distanceY, 200, 30);
    }

    private void createCheckEventBtn(){
        checkEventBtn = new MyButton(true);
        checkEventBtn.setText("Sprawdź Twoje wydarzenia");
        checkEventBtn.setBounds(250, firstBtnY + 4*distanceY, 200, 30);
    }

    private void createReturnBtn(){
        returnBtn = new MyButton(false);
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

    private void createNameLbl(){
        nameLbl = new JLabel();
        nameLbl.setText("-------");
        nameLbl.setBounds(250,40,200,30);
    }

    private void createCardNrLbl(){
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250,80,200,30);
        cardNrLbl.setText("----------");
    }
    public MyButton getUpdateBtn(){return updateBtn;}
    public MyButton getDoBookingBtn(){return doBookingBtn;}
    public MyButton getLendingsBtn(){return lendingsBtn;}
    public MyButton getJoinEventBtn(){return joinEventBtn;}
    public MyButton getCheckEventBtn(){return checkEventBtn;}
    public MyButton getReturnBtn(){return returnBtn;}
    public void setNameLbl(String name){this.nameLbl.setText(name);}
    public void setCardNrLbl(String cardNr){this.cardNrLbl.setText(cardNr);}
    public JLabel getCardNrLbl(){return cardNrLbl;}
}
