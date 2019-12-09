package gui.book;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import book.Book;
import book.BookService;
import book.IBook;

public class BookGetPanel extends JPanel {

    private JLabel searchByLabel, keyWordLabel, resultListLabel, result;
    private JComboBox searchBy;
    private JTextField keyWord;
    private JList resultList;
    private JButton search, remove, edit, back;

    private IBook iBook = new BookService();
    private int bookIdToEdit;


    public BookGetPanel() {

        setLayout(null);

        createComps();
        addComps();
        actions();
    }

    private void createBookJList(List<Book> bookList){

        DefaultListModel listModel = new DefaultListModel();
        for (Book aBookList : bookList) {
            listModel.addElement(aBookList);
        }
        resultList.setModel(listModel);
        resultList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane listScroller = new JScrollPane(resultList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void createComps() {

        searchByLabel = new JLabel("Wyszukaj po:");
        searchByLabel.setBounds(20,20,100,30);

        searchBy = new JComboBox(new String[]{"tytule", "autorze (imię, nazwisko)", "wydawcy", "gatunku", "języku"});
        searchBy.setBounds(150,20,200,30);

        keyWordLabel = new JLabel("Słowo kluczowe:");
        keyWordLabel.setBounds(20,60,100,30);

        keyWord = new JTextField();
        keyWord.setBounds(150,60,200,30);

        resultListLabel = new JLabel("Wyniki wyszukiwania:");
        resultListLabel.setBounds(20,100,200,30);

        resultList = new JList();
        resultList.setBounds(20,140,580,320);
        resultList.setBorder(BorderFactory.createLineBorder(Color.black));

        result = new JLabel();
        result.setBounds(20,140,580,320);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setBackground(Color.white);
        result.setOpaque(true);

        search = new JButton("szukaj");
        search.setBounds(400,20,200,30);

        remove = new JButton("usuń");
        remove.setBounds(400,60,200,30);

        edit = new JButton("edytuj");
        edit.setBounds(400,100,200,30);

        back = new JButton("cofnij");
        back.setBounds(400,470,200,30);
    }

    private void actions(){

        search.addActionListener(e -> {
            List<Book> bookList;

            if(keyWord.getText().length() == 0){
                bookList = iBook.getAllBooks();
            } else if(searchBy.getSelectedIndex() == 1){
                int coma = keyWord.getText().indexOf(",");
                String firstName = keyWord.getText().substring(0, coma);
                String lastName = keyWord.getText().substring(coma + 2);
                bookList = iBook.getBooks(firstName, lastName);
            } else {
                bookList = iBook.getBooks(keyWord.getText());
            }

            if(bookList.size() > 0) {
                createBookJList(bookList);
                add(resultList);
            } else {
                result.setText("Nie ma takich książek.");
            }
        });

        remove.addActionListener(e ->{
            Book book = (Book) resultList.getSelectedValue();

            if(book== null) {
                JOptionPane.showMessageDialog(null, "Żadna książka nie została wybrana.");
            } else {

                if (JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć książkę?", "UWAGA!",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    iBook.removeBook(book.getBookId());
                    List<Book> bookList = iBook.getAllBooks();
                    createBookJList(bookList);
                    add(resultList);
                }
            }
        });
    }

    private void addComps(){

        add(searchByLabel);
        add(searchBy);
        add(keyWordLabel);
        add(keyWord);
        add(resultListLabel);
        add(result);
        add(search);
        add(remove);
        add(edit);
        add(back);
    }

    public JButton getBack() {
        return back;
    }

    public JButton getEdit() {
        return edit;
    }

    public int getBookIdToEdit() {

        bookIdToEdit = 0;

        Book book = (Book) resultList.getSelectedValue();
        if(book != null)
            bookIdToEdit = book.getBookId();

        return bookIdToEdit;
    }
}