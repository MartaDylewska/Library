package gui.admin;

import javax.swing.*;

public class AdminEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private JButton addLibrarianBtn, deleteLibrarianBtn, updateLibrarianBtn;
    private JButton addAdminBtn, deleteAdminBtn, updateAdminBtn;
    private JButton returnBtn;
    private int firstBtnY = 10;
    private int distanceY = 50;
    private int buttonHeight = 40;

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
        addLibrarianBtn = new JButton();
        addLibrarianBtn.setText("Dodaj profil Bibiliotekarza");
        addLibrarianBtn.setBounds(300, firstBtnY, 250, buttonHeight);
    }
    private void createDeleteLibrarianBtn() {
        deleteLibrarianBtn = new JButton();
        deleteLibrarianBtn.setText("Usuń profil Bibiliotekarza");
        deleteLibrarianBtn.setBounds(300, firstBtnY + distanceY, 250, buttonHeight);
    }
    private void createUpdateLibrarianBtn() {
        updateLibrarianBtn = new JButton();
        updateLibrarianBtn.setText("Aktualizuj profil Bibiliotekarza");
        updateLibrarianBtn.setBounds(300, firstBtnY + 2 * distanceY, 250, buttonHeight);
    }
    private void createAddAdminBtn() {
        addAdminBtn = new JButton();
        addAdminBtn.setText("Dodaj profil Administratora");
        addAdminBtn.setBounds(300, firstBtnY + 3 * distanceY, 250, buttonHeight);
    }
    private void createDeleteAdminBtn() {
        deleteAdminBtn = new JButton();
        deleteAdminBtn.setText("Usuń profil Administratora");
        deleteAdminBtn.setBounds(300, firstBtnY + 4 * distanceY, 250, buttonHeight);
    }
    private void createUpdateAdminBtn(){
        updateAdminBtn = new JButton();
        updateAdminBtn.setText("Aktualizuj profil Administratora");
        updateAdminBtn.setBounds(300, firstBtnY + 5*distanceY,250,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new JButton();
        returnBtn.setText("Wyloguj");
        returnBtn.setBounds(300, firstBtnY + 9 * distanceY, 250, buttonHeight);
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
        nameLbl.setBounds(50, 50, 200, 50);
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(50, 150, 200, 50);
        cardNrLbl.setText("----------");
    }

    public JButton getAddLibrarianBtn() {
        return addLibrarianBtn;
    }

    public JButton getDeleteLibrarianBtn() {
        return deleteLibrarianBtn;
    }

    public JButton getUpdateLibrarianBtn() {
        return updateLibrarianBtn;
    }

    public JButton getAddAdminBtn() {
        return addAdminBtn;
    }
    public JButton getDeleteAdminBtn() {
        return deleteAdminBtn;
    }

    public JButton getUpdateAdminBtn() {
        return updateAdminBtn;
    }

    public JButton getReturnBtn() {
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
