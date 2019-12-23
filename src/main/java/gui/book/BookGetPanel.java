package gui.book;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import book.*;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;

public class BookGetPanel extends JPanel {

    private JLabel searchByLabel, keyWordLabel, resultListLabel, result;
    private JComboBox<String> searchBy;
    private JTextField keyWord;
    private JList<AuthorBook> resultList;
    private CustButton search, remove, edit, location, unavailable, returnBtn;
    private JScrollPane scrollpane;
    private List<Component> componentBoldList = new ArrayList<>();
    private List<Component> componentPlainList = new ArrayList<>();

    private IBook iBook = new BookService();
    private IAuthor iAuthor = new AuthorService();
    private IAuthorBook iAuthorBook = new AuthorBookService();
    private int bookIdToEdit, authorIdToEdit;
    private JLabel rectLabel;

    public BookGetPanel() {
        setLayout(null);
        createComps();
        addComps();
        createScroll();
        actions();
        createRectLabel();
        add(rectLabel);
        createCompBoldList();
        createCompPlainList();
        setFont();
        Auxiliary.setImageAsBackground(this);
    }

    private void setFont(){
        for (Component c: componentPlainList)
            c.setFont(Auxiliary.panelPlainFont);
        for (Component c: componentBoldList)
            c.setFont(Auxiliary.panelFont);
    }

    private void createCompBoldList(){
        componentBoldList.add(searchByLabel);
        componentBoldList.add(keyWordLabel);
        componentBoldList.add(resultListLabel);
        componentBoldList.add(result);
        //componentBoldList.add(searchBy);
    }

    private void createCompPlainList(){
        componentPlainList.add(keyWord);
        componentPlainList.add(resultList);
        componentPlainList.add(searchBy);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        //rectLabel.setText("aa");
        rectLabel.setBounds(20,10,660,530);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(true);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void createBookJList(List<AuthorBook> bookList){
        DefaultListModel<AuthorBook> listModel = new DefaultListModel<>();
        for (AuthorBook aBookList : bookList) {
            listModel.addElement(aBookList);
        }
        resultList.setModel(listModel);
        resultList.setLayoutOrientation(JList.VERTICAL);
    }

    private void createScroll(){
        scrollpane = new JScrollPane();
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollpane.setPreferredSize(new Dimension(100,50));
        scrollpane.setBounds(50,160,600,320);
        scrollpane.setViewportView(resultList);
        scrollpane.setBorder(Auxiliary.blackBorder());
        add(scrollpane);
        repaint();
    }

    private void createComps() {
        searchByLabel = new JLabel("Wyszukaj po:");
        searchByLabel.setBounds(50,40,100,30);

        searchBy = new JComboBox<>(new String[]{"tytule", "autorze (imię, nazwisko)", "wydawcy", "gatunku", "języku"});
        searchBy.setBounds(180,40,200,30);

        keyWordLabel = new JLabel("Słowo kluczowe:");
        keyWordLabel.setBounds(50,80,130,30);

        keyWord = new JTextField();
        keyWord.setBounds(180,80,200,30);

        resultListLabel = new JLabel("Wyniki wyszukiwania:");
        resultListLabel.setBounds(50,120,200,30);

        resultList = new JList<>();
        resultList.setBounds(50,160,600,320);
        resultList.setBorder(BorderFactory.createLineBorder(Color.black));

        result = new JLabel();
        result.setBounds(50,160,600,320);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setBackground(Color.white);
        result.setOpaque(true);
        result.setVerticalAlignment(1);

        search = new CustButton();
        search.setText("Szukaj");
        search.setBounds(450,40,200,30);

        remove = new CustButton();
        remove.setText("Usuń");
        remove.setBounds(250,490,200,30);

        edit = new CustButton();
        edit.setText("Edytuj");
        edit.setBounds(50,490,200,30);

        location = new CustButton();
        location.setText("Pokaż bez lokalizacji");
        location.setBounds(450, 80,200,30);

        unavailable = new CustButton();
        unavailable.setText("Pokaż niedostępne");
        unavailable.setBounds(450, 120, 200,30);

        returnBtn = new CustButton();
        returnBtn.setText("Anuluj");
        returnBtn.setBounds(450,490,200,30);
    }

    private void actions(){

        search.addActionListener(e -> {
            List<AuthorBook> bookList = new ArrayList<>();

            if(keyWord.getText().length() == 0){
                bookList = iAuthorBook.getAllBooks(1);
            } else if(searchBy.getSelectedIndex() == 1){
                if(keyWord.getText().contains(",")) {
                    int coma = keyWord.getText().indexOf(",");
                    String firstName = keyWord.getText().substring(0, coma);
                    String lastName = keyWord.getText().substring(coma + 2);
                    int authorId = iAuthor.getAuthorId(firstName, lastName);
                    bookList = iAuthorBook.getBooksOfAuthor(authorId, 1);
                } else {
                    JOptionPane.showMessageDialog(this, "Nieprawidlowy format");
                }
            } else if(searchBy.getSelectedIndex() == 0) {
                bookList = iAuthorBook.getBooksByTitle(keyWord.getText(), 1);
            } else {
                bookList = iAuthorBook.getBooksBySearch(keyWord.getText(), 1);
            }

            if(bookList.size() > 0) {
                createBookJList(bookList);
                //add(resultList);
                add(scrollpane);
            } else {
                remove(scrollpane);
                //remove(resultList);
                result.setText("Nie ma takich książek.");
            }

        });

        remove.addActionListener(e ->{
            AuthorBook book = resultList.getSelectedValue();

            if(book== null) {
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
            } else {

                if (JOptionPane.showConfirmDialog(this, "Czy na pewno usunąć książkę?", "UWAGA!",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    iAuthorBook.removeBook(book.getBook().getTitle());
                    iBook.removeBook(book.getBook().getBookId());
                    repaint();
                    revalidate();
                }
            }
        });

        location.addActionListener(e ->{

            List<AuthorBook> bookList = iAuthorBook.getNotLocatedBooks();

            if(bookList.size() > 0) {
                createBookJList(bookList);
                add(scrollpane);
                //add(resultList);
            } else {
                result.setText("Nie ma takich książek.");
            }
        });

        unavailable.addActionListener(e ->{
            List<AuthorBook> bookList = iAuthorBook.getAllBooks(3);

            if(bookList.size() > 0) {
                createBookJList(bookList);
                add(scrollpane);
                //add(resultList);
            } else {
                result.setText("Nie ma takich książek.");
            }
        });
    }

    private void addComps(){

        add(searchByLabel);
        add(searchBy);
        add(keyWordLabel);
        add(keyWord);
        add(resultListLabel);
        //add(result);
        add(search);
        add(remove);
        add(edit);
        //add(location);
        add(unavailable);
        add(returnBtn);
//        add(scrollpane);
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }

    public CustButton getEdit() {
        return edit;
    }

    int getBookIdToEdit() {

        bookIdToEdit = 0;

        AuthorBook authorBook = resultList.getSelectedValue();
        if(authorBook != null){
            bookIdToEdit = authorBook.getBook().getBookId();
        }

        return bookIdToEdit;
    }

    int getAuthorIdToEdit(){

        authorIdToEdit = 0;

        AuthorBook authorBook = resultList.getSelectedValue();
        if(authorBook != null) {
            authorIdToEdit = authorBook.getAuthor().getAuthorId();
        }

        return authorIdToEdit;
    }
    public JList<AuthorBook> getResultList(){return resultList;}
}