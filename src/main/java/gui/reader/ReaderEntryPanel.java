package gui.reader;

import javax.swing.*;

public class ReaderEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private JButton updateBtn, doBookingBtn, lendingsBtn,joinEventBtn, checkEventBtn;
    private JButton returnBtn;
    private int firstBtnY = 10;
    private int distanceY = 80;

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
        updateBtn = new JButton();
        updateBtn.setText("Aktualizuj profil");
        updateBtn.setBounds(300,firstBtnY,250,50);
    }

    private void createDoBookingBtn(){
        doBookingBtn = new JButton();
        doBookingBtn.setText("Zarezerwuj książkę");
        doBookingBtn.setBounds(300,firstBtnY + distanceY,250,50);
    }

    private void createLendingsBtn(){
        lendingsBtn = new JButton();
        lendingsBtn.setText("Sprawdź Twoje wypożyczenia");
        lendingsBtn.setBounds(300,firstBtnY + 2*distanceY,250,50);
    }
    private void createJoinEventBtn(){
        joinEventBtn = new JButton();
        joinEventBtn.setText("Dołącz do wydarzenia");
        joinEventBtn.setBounds(300, firstBtnY + 3*distanceY, 250, 50);
    }

    private void createCheckEventBtn(){
        checkEventBtn = new JButton();
        checkEventBtn.setText("Sprawdź Twoje wydarzenia");
        checkEventBtn.setBounds(300, firstBtnY + 4*distanceY, 250, 50);
    }

    private void createReturnBtn(){
        returnBtn = new JButton();
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(300,firstBtnY+5*distanceY,250,50);
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
        nameLbl.setBounds(50,50,200,50);
    }

    private void createCardNrLbl(){
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(50,150,200,50);
        cardNrLbl.setText("----------");
    }
    public JButton getUpdateBtn(){return updateBtn;}
    public JButton getDoBookingBtn(){return doBookingBtn;}
    public JButton getLendingsBtn(){return lendingsBtn;}
    public JButton getJoinEventBtn(){return joinEventBtn;}
    public JButton getCheckEventBtn(){return checkEventBtn;}
    public JButton getReturnBtn(){return returnBtn;}
    public void setNameLbl(String name){this.nameLbl.setText(name);}
    public void setCardNrLbl(String cardNr){this.cardNrLbl.setText(cardNr);}
    public JLabel getCardNrLbl(){return cardNrLbl;}
}
