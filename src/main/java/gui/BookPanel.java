package gui;

import javax.swing.*;

public class BookPanel extends JPanel{

    private JButton addBook, findBook, editBook, removeBook, showAllBooks, back;

    BookPanel(){

        setLayout(null);

        createComp();
        addComps();
    }

    private void createComp(){

        addBook = new JButton("Dodaj książkę");
        addBook.setBounds(200,20,200,50);

        findBook = new JButton("Znajdź książkę");
        findBook.setBounds(200,120,200,50);

        editBook = new JButton("Edytuj książkę");
        editBook.setBounds(200,220,200,50);

        removeBook = new JButton("Usuń książkę");
        removeBook.setBounds(200,320,200,50);

        showAllBooks = new JButton("Pokaż wszystkie książki");
        showAllBooks.setBounds(200,420,200,50);

        back = new JButton("Wróć");
        back.setBounds(200,520,200,50);
    }

    private void addComps(){
        add(addBook);
        add(findBook);
        add(editBook);
        add(removeBook);
        add(showAllBooks);
        add(back);
    }
}
