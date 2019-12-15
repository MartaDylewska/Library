package gui.lending;

import bookTransfer.BookTransfer;
import bookTransfer.BookTransferService;
import bookTransfer.IBookTransfer;
import gui.reader.ReaderEntryPanel;
import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LendingShowPanel extends JPanel {

    private ReaderEntryPanel readerEntryPanel;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();
    private IBookTransfer bookTransfer = new BookTransferService();

    private JLabel lentBooksLabel, reservedBooksLabel, cardIdTxt;
    private JButton resignBook, returnBtn;
    private JList<BookTransfer> lentBooks, reservedBooks;

    public LendingShowPanel(ReaderEntryPanel readerEntryPanel) {

        setLayout(null);
        this.readerEntryPanel = readerEntryPanel;
        createCardIdTxt();
        add(cardIdTxt);
        cardIdTxt.setText(readerEntryPanel.getCardNrLbl().getText());
        cardIdTxt.setVisible(false);

        User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        int readerId = reader.getIdReader();

        List<BookTransfer> reservedUserBooks = bookTransfer.getReservedUserBooks(readerId);
        List<BookTransfer> lentUserBooks = bookTransfer.getLentUserBooks(readerId);

        createComps();

        createLentBooks(lentUserBooks);
        createReservedBooks(reservedUserBooks);

        addItems();
        action();

    }

    private void createReservedBooks(List<BookTransfer> bookList){

        DefaultListModel<BookTransfer> listModel = new DefaultListModel<>();
        for (BookTransfer aBookList : bookList) {
            listModel.addElement(aBookList);
        }
        reservedBooks.setModel(listModel);
        JScrollPane listScroller = new JScrollPane(reservedBooks);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void createLentBooks(List<BookTransfer> bookList) {

        DefaultListModel<BookTransfer> listModel = new DefaultListModel<>();
        for (BookTransfer aBookList : bookList) {
            listModel.addElement(aBookList);
        }
        lentBooks.setModel(listModel);
        JScrollPane listScroller = new JScrollPane(lentBooks);
        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void addItems() {

        add(reservedBooksLabel);
        add(resignBook);
        add(reservedBooks);
        add(lentBooksLabel);
        add(lentBooks);
        add(returnBtn);

    }

    private void createComps(){

        reservedBooksLabel = new JLabel("zarezerowane książki:");
        reservedBooksLabel.setBounds(20,70,200,30);

        resignBook = new JButton("rezygnuj");
        resignBook.setBounds(400,70,200,30);

        reservedBooks = new JList<>();
        reservedBooks.setBounds(20,110,580,100);
        reservedBooks.setBorder(BorderFactory.createLineBorder(Color.black));

        lentBooksLabel = new JLabel("wypożyczone książki:");
        lentBooksLabel.setBounds(20,230,200,30);

        lentBooks = new JList<>();
        lentBooks.setBounds(20,270,580,100);
        lentBooks.setBorder(BorderFactory.createLineBorder(Color.black));

        returnBtn = new JButton("cofnij");
        returnBtn.setBounds(400,390,200,30);

        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
        cardIdTxt.setVisible(false);

    }

    private void action() {

        resignBook.addActionListener(e ->{

            List<BookTransfer> book = reservedBooks.getSelectedValuesList();

            for (BookTransfer aBook : book) {
                bookTransfer.unReserveBook(aBook.getAuthorBook().getBook().getBookId());
                JOptionPane.showMessageDialog(this, "Zrezygnowano z książki.");
            }

            if(book.size() == 0)
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
        });
    }

    private void createCardIdTxt(){
        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
    }

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }

    public JButton getReturnBtn() {
        return returnBtn;
    }
}
