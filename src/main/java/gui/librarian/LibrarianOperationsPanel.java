package gui.librarian;

import javax.swing.*;

public class LibrarianOperationsPanel extends JPanel {

    private JButton searchBtn, updateBtn, deleteBtn, showAllBtn, addBtn;

    public LibrarianOperationsPanel(){
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
        addBtn.setText("Dodaj Bibliotekarza");
        addBtn.setBounds(200,420,200,50);
    }

    private void createSearchBtn(){
        searchBtn = new JButton();
        searchBtn.setText("Wyszukaj Bibliotekarza");
        searchBtn.setBounds(200,20,200,50);
    }

    private void createUpdateBtn(){
        updateBtn = new JButton();
        updateBtn.setText("Aktualizuj Bibliotekarza");
        updateBtn.setBounds(200,120,200,50);
    }

    private void createDeleteBtn(){
        deleteBtn = new JButton();
        deleteBtn.setText("Usuń Bibliotekarza");
        deleteBtn.setBounds(200,220,200,50);
    }

    private void createShowAllBtn(){
        showAllBtn = new JButton();
        showAllBtn.setText("Pokaż Bibliotekarzy");
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
