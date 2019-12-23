package gui.admin;

import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;

import javax.swing.*;
import java.awt.*;

public class AdminEntryPanel extends JPanel {
    private JLabel nameLbl, cardNrLbl;
    private CustButton addLibrarianBtn, deleteLibrarianBtn, updateLibrarianBtn;
    private CustButton addAdminBtn, deleteAdminBtn, updateAdminBtn;
    private CustButton returnBtn;
    private int firstBtnY = 120;
    private int distanceY = 40;
    private int buttonHeight = 30;
    private JLabel rectLabel;

    public AdminEntryPanel(){
        setLayout(null);
        createAllLabels();
        addAllLabels();
        createAllBtns();
        addAllBtns();
        createRectLabel();
        add(rectLabel);
        Auxiliary.setImageAsBackground(this);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        //rectLabel.setText("aa");
        rectLabel.setBounds(200,20,300,410);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
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
        addLibrarianBtn = new CustButton();
        addLibrarianBtn.setText("Dodaj Bibiliotekarza");
        addLibrarianBtn.setBounds(250, firstBtnY, 200, buttonHeight);
    }
    private void createDeleteLibrarianBtn() {
        deleteLibrarianBtn = new CustButton();
        deleteLibrarianBtn.setText("Usuń Bibiliotekarza");
        deleteLibrarianBtn.setBounds(250, firstBtnY + distanceY, 200, buttonHeight);
    }
    private void createUpdateLibrarianBtn() {
        updateLibrarianBtn = new CustButton();
        updateLibrarianBtn.setText("Aktualizuj Bibiliotekarza");
        updateLibrarianBtn.setBounds(250, firstBtnY + 2 * distanceY, 200, buttonHeight);
    }
    private void createAddAdminBtn() {
        addAdminBtn = new CustButton();
        addAdminBtn.setText("Dodaj Administratora");
        addAdminBtn.setBounds(250, firstBtnY + 3 * distanceY, 200, buttonHeight);
    }
    private void createDeleteAdminBtn() {
        deleteAdminBtn = new CustButton();
        deleteAdminBtn.setText("Usuń Administratora");
        deleteAdminBtn.setBounds(250, firstBtnY + 4 * distanceY, 200, buttonHeight);
    }
    private void createUpdateAdminBtn(){
        updateAdminBtn = new CustButton();
        updateAdminBtn.setText("Aktualizuj Administratora");
        updateAdminBtn.setBounds(250, firstBtnY + 5*distanceY,200,buttonHeight);
    }
    private void createReturnBtn() {
        returnBtn = new CustButton();
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
        nameLbl.setBounds(250,40,200,30);
        nameLbl.setFont(new Font("Arial Narrow", Font.BOLD, 20));
        nameLbl.setForeground(new Color(78,52,46,255));
    }

    private void createCardNrLbl() {
        cardNrLbl = new JLabel();
        cardNrLbl.setBounds(250,60,200,30);
        cardNrLbl.setText("----------");
        cardNrLbl.setFont(new Font("Arial Narrow", Font.BOLD, 16));
        cardNrLbl.setForeground(new Color(78,52,46,255));
    }

    public CustButton getAddLibrarianBtn() {
        return addLibrarianBtn;
    }

    public CustButton getDeleteLibrarianBtn() {
        return deleteLibrarianBtn;
    }

    public CustButton getUpdateLibrarianBtn() {
        return updateLibrarianBtn;
    }

    public CustButton getAddAdminBtn() {
        return addAdminBtn;
    }
    public CustButton getDeleteAdminBtn() {
        return deleteAdminBtn;
    }

    public CustButton getUpdateAdminBtn() {
        return updateAdminBtn;
    }

    public CustButton getReturnBtn() {
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
