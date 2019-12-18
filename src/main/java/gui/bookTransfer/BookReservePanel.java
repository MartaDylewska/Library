package gui.bookTransfer;

import book.*;
import bookTransfer.BookTransferService;
import bookTransfer.IBookTransfer;
import gui.general.MyButton;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BookReservePanel extends JPanel {

    private JLabel searchByLabel, keyWordLabel, resultListLabel, result;
    private JComboBox<String> searchBy;
    private JTextField keyWord;
    private JList<AuthorBook> resultList;
    private MyButton search, reserveBtn, returnBtn;

    private IAuthor iAuthor = new AuthorService();
    private IAuthorBook iAuthorBook = new AuthorBookService();
    private IBookTransfer iBookTransfer = new BookTransferService();
    private JLabel cardIdTxt;
    private IUserDBService userDBService = new UserDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public BookReservePanel() {

        setLayout(null);

        createComps();
        addComps();
        actions();
        cardIdTxt.setVisible(false);
    }

    private void createBookJList(List<AuthorBook> bookList){

        DefaultListModel<AuthorBook> listModel = new DefaultListModel<>();
        for (AuthorBook aBookList : bookList) {
            listModel.addElement(aBookList);
        }
        resultList.setModel(listModel);
        JScrollPane listScroller = new JScrollPane(resultList);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void createComps() {

        searchByLabel = new JLabel("Wyszukaj po:");
        searchByLabel.setBounds(50,60,100,30);

        searchBy = new JComboBox<>(new String[]{"tytule", "autorze (imię, nazwisko)", "wydawcy", "gatunku", "języku"});
        searchBy.setBounds(200,60,200,30);

        keyWordLabel = new JLabel("Słowo kluczowe:");
        keyWordLabel.setBounds(50,100,100,30);

        keyWord = new JTextField();
        keyWord.setBounds(200,100,200,30);

        resultListLabel = new JLabel("Wyniki wyszukiwania:");
        resultListLabel.setBounds(50,140,200,30);

        resultList = new JList<>();
        resultList.setBounds(50,180,600,300);
        resultList.setBorder(BorderFactory.createLineBorder(Color.black));

        result = new JLabel();
        result.setBounds(50,180,600,300);
        result.setBorder(BorderFactory.createLineBorder(Color.black));
        result.setBackground(Color.white);
        result.setOpaque(true);
        result.setVerticalAlignment(1);

        search = new MyButton(true);
        search.setText("Szukaj");
        search.setBounds(450,60,200,30);

        reserveBtn = new MyButton(true);
        reserveBtn.setText("Rezerwuj");
        reserveBtn.setBounds(450,100,200,30);

        returnBtn = new MyButton(false);
        returnBtn.setText("Cofnij");
        returnBtn.setBounds(450,490,200,30);

        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);

    }

    private void actions(){

        search.addActionListener(e -> {
            List<AuthorBook> bookList = new ArrayList<>();

            if(keyWord.getText().length() == 0){
                bookList = iAuthorBook.getAllBooks(2);
            } else if(searchBy.getSelectedIndex() == 1){
                if(keyWord.getText().contains(", ")) {
                    int coma = keyWord.getText().indexOf(",");
                    String firstName = keyWord.getText().substring(0, coma);
                    String lastName = keyWord.getText().substring(coma + 2);
                    int authorId = iAuthor.getAuthorId(firstName, lastName);
                    bookList = iAuthorBook.getBooksOfAuthor(authorId, 2);
                } else {
                    JOptionPane.showMessageDialog(this, "Nieprawidlowy format.");
                }
            } else if(searchBy.getSelectedIndex() == 0) {
                bookList = iAuthorBook.getBooksByTitle(keyWord.getText(), 2);
            } else {
                bookList = iAuthorBook.getBooksBySearch(keyWord.getText(), 2);
            }

            if(bookList.size() > 0) {
                createBookJList(bookList);
                add(resultList);
            } else {
                remove(resultList);
                result.setText("Nie ma takich książek.");
            }
        });

        reserveBtn.addActionListener(e -> {

            List<AuthorBook> book = resultList.getSelectedValuesList();

            User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
            Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
            int readerId = reader.getIdReader();

            for (AuthorBook aBook : book) {
                iBookTransfer.reserveBook(readerId, aBook.getBook().getBookId());
                JOptionPane.showMessageDialog(this, iBookTransfer.getMessage());
                repaint();
                revalidate();
            }

            if(book.size() == 0)
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
        });
    }

    private void addComps(){
        add(cardIdTxt);
        add(searchByLabel);
        add(searchBy);
        add(keyWordLabel);
        add(keyWord);
        add(resultListLabel);
        add(result);
        add(search);
        add(reserveBtn);
        add(returnBtn);
    }

    public MyButton getReturnBtn() {
        return returnBtn;
    }

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }
}