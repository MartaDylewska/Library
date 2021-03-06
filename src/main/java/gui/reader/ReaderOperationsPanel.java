package gui.reader;

import javax.swing.*;

public class ReaderOperationsPanel extends JPanel {

    private JButton searchBtn, updateBtn, deleteBtn, showAllBtn, addBtn;

    public ReaderOperationsPanel(){
        setLayout(null);
        createAllButtons();
        addAllButtons();
    }

    private void createAllButtons(){
        createSearchBtn();
        createUpdateBtn();
        createDeleteBtn();
        createShowAllBtn();
        createAddBtn();
    }

    private void addAllButtons(){
        add(searchBtn);
        add(updateBtn);
        add(deleteBtn);
        add(showAllBtn);
        add(addBtn);
    }

    private void createAddBtn(){
        addBtn = new JButton();
        addBtn.setText("Dodaj użytkownikaR");
        addBtn.setBounds(200,420,200,50);
    }

    private void createSearchBtn(){
        searchBtn = new JButton();
        searchBtn.setText("Wyszukaj użytkownikaR");
        searchBtn.setBounds(200,20,200,50);
    }

    private void createUpdateBtn(){
        updateBtn = new JButton();
        updateBtn.setText("Aktualizuj użytkownikaR");
        updateBtn.setBounds(200,120,200,50);
    }

    private void createDeleteBtn(){
        deleteBtn = new JButton();
        deleteBtn.setText("Usuń użytkownikaR");
        deleteBtn.setBounds(200,220,200,50);
    }

    private void createShowAllBtn(){
        showAllBtn = new JButton();
        showAllBtn.setText("Pokaż wszystkichR");
        showAllBtn.setBounds(200,320,200,50);
    }

    public JButton getSearchBtn(){
        return searchBtn;
    }

    public JButton getUpdateBtn(){
        return updateBtn;
    }
    public JButton getDeleteBtn(){
        return deleteBtn;
    }

    public JButton getAddBtn(){
        return addBtn;
    }

    public JButton getShowAllBtn() { return showAllBtn;}
}
