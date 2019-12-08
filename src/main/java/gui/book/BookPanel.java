package gui.book;

import javax.swing.*;

public class BookPanel extends JPanel{

    private JButton addBook, findBook, lendBook, acceptReturn, back;

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

        lendBook = new JButton("Wypożycz książkę");
        lendBook.setBounds(200,220,200,50);

        acceptReturn = new JButton("Przyjmij zwrot książki");
        acceptReturn.setBounds(200,320,200,50);

        back = new JButton("Wróć");
        back.setBounds(200,420,200,50);
    }

    private void addComps(){
        add(addBook);
        add(findBook);
        add(lendBook);
        add(acceptReturn);
//        add(showAllBooks);
        add(back);
    }

    public JButton getAddBook() {
        return addBook;
    }

    public JButton getFindBook() {
        return findBook;
    }

    public JButton getLendBook() {
        return lendBook;
    }

    public JButton getAcceptReturn() {
        return acceptReturn;
    }

    public JButton getBack() {
        return back;
    }
}
