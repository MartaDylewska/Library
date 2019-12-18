package gui.admin;

import gui.general.MyButton;

import javax.swing.*;

public class AdminEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private MyButton addLibrarianBtn, deleteLibrarianBtn, updateLibrarianBtn;
    private MyButton addAdminBtn, deleteAdminBtn, updateAdminBtn;
    private MyButton returnBtn;
    private int firstBtnY = 120;
    private int distanceY = 40;
    private int buttonHeight = 30;

    public AdminEntryPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
    }

    private void addAllBtns() {
        add(addLibrarianBtn);
        add(addAdminBtn);
        add(deleteLibrarianBtn);
        add(deleteAdminBtn);
        add(updateLibrarianBtn);
        add(updateAdminBtn);
        add(returnBtn);
    }

    private void createAllBtns() {
        createDeleteLibrarianBtn();
        createUpdateLibrarianBtn();
        createAddLibrarianBtn();
        createAddAdminBtn();
        createDeleteAdminBtn();
        createUpdateAdminBtn();
        createReturnBtn();
    }

    private void createAddLibrarianBtn() {
        addLibrarianBtn = new MyButton(true);
        addLibrarianBtn.setText("Dodaj profil Bibiliotekarza");
        addLibrarianBtn.setBounds(250, firstBtnY, 200, buttonHeight);
    }
    private void createDeleteLibrarianBtn() {
        deleteLibrarianBtn = new MyButton(true);
        deleteLibrarianBtn.setText("Usuń profil Bibiliotekarza");
        deleteLibrarianBtn.setBounds(250, firstBtnY + distanceY, 200, buttonHeight);
    }
    private void createUpdateLibrarianBtn() {
        updateLibrarianBtn = new MyButton(true);
        updateLibrarianBtn.setText("Aktualizuj Bibiliotekarza");
        updateLibrarianBtn.setBounds(250, firstBtnY + 2 * distanceY, 200, buttonHeight);
    }
    private void createAddAdminBtn() {
        addAdminBtn = new MyButton(true);
        addAdminBtn.setText("Dodaj profil Administratora");
        addAdminBtn.setBounds(250, firstBtnY + 3 * distanceY, 200, buttonHeight);
    }
    private void createDeleteAdminBtn() {
        deleteAdminBtn = new MyButton(true);
        deleteAdminBtn.setText("Usuń profil Administratora");
        deleteAdminBtn.setBounds(250, firstBtnY + 4 * distanceY, 200, buttonHeight);
    }
    private void createUpdateAdminBtn(){
        updateAdminBtn = new MyButton(true);
        updateAdminBtn.setText("Aktualizuj Administratora");
        updateAdminBtn.setBounds(250, firstBtnY + 5*distanceY,200,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new MyButton(false);
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(250, firstBtnY + 6 * distanceY, 200, buttonHeight);
    }

    private void addAllLabels() {
        add(cardNrLbl);
        add(nameLbl);
    }

    private void createAllLabels() {
        createCardNrLbl();
        createNameLbl();
    }

    private void createNameLbl() {
        nameLbl = new JLabel();
        nameLbl.setText("-------");
        nameLbl.setBounds(250, 40, 200, 30);
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250, 80, 200, 30);
        cardNrLbl.setText("----------");
    }

    public MyButton getAddLibrarianBtn() {
        return addLibrarianBtn;
    }

    public MyButton getDeleteLibrarianBtn() {
        return deleteLibrarianBtn;
    }

    public MyButton getUpdateLibrarianBtn() {
        return updateLibrarianBtn;
    }

    public MyButton getAddAdminBtn() {
        return addAdminBtn;
    }
    public MyButton getDeleteAdminBtn() {
        return deleteAdminBtn;
    }

    public MyButton getUpdateAdminBtn() {
        return updateAdminBtn;
    }

    public MyButton getReturnBtn() {
        return returnBtn;
    }

    public void setNameLbl(String name) {
        this.nameLbl.setText(name);
    }

    public void setCardNrLbl(String cardNr) {
        this.cardNrLbl.setText(cardNr);
    }

    public JLabel getCardNrLbl() {
        return cardNrLbl;
    }
}
