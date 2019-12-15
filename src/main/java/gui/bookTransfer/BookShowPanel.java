package gui.bookTransfer;

import book.AuthorBookService;
import book.AuthorService;
import book.IAuthor;
import book.IAuthorBook;
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

public class BookShowPanel extends JPanel {

    private IBookTransfer bookTransfer = new BookTransferService();

    private JLabel lentBooksLabel, reservedBooksLabel, cardIdTxt;
    private JButton resignBook, back;
    private JList<BookTransfer> lentBooks, reservedBooks;

    private IUserDBService userDBService = new UserDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();

    public BookShowPanel(){

        setLayout(null);
        createComps();
        createLentBooks();
        createLentBooks();
        addComps();
        actions();

//        User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
//        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
//        int readerId = reader.getIdReader();
//        System.out.println(readerId);

    }

    private void createReservedBooks(){

//        User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
//        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
//        int readerId = Integer.parseInt(cardIdTxt.getText());
//        System.out.println(readerId);
//
//        List<BookTransfer> reservedUserBooks = bookTransfer.getReservedUserBooks(readerId);
//
//        DefaultListModel listModel = new DefaultListModel();
//        for (BookTransfer aBookList : reservedUserBooks) {
//            listModel.addElement(aBookList);
//        }
//        reservedBooks.setModel(listModel);
//        JScrollPane listScroller = new JScrollPane(reservedBooks);
//        listScroller.setPreferredSize(new Dimension(250, 80));
    }

    private void createLentBooks() {

//        User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
//        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
//        int readerId = Integer.parseInt(cardIdTxt.getText());
//
//        List<BookTransfer> lentUserBooks = bookTransfer.getLentUserBooks(readerId);
//
//        DefaultListModel listModel = new DefaultListModel();
//        for (BookTransfer aBookList : lentUserBooks) {
//            listModel.addElement(aBookList);
//        }
//        lentBooks.setModel(listModel);
//        JScrollPane listScroller = new JScrollPane(lentBooks);
//        listScroller.setPreferredSize(new Dimension(250, 80));
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

        back = new JButton("cofnij");
        back.setBounds(400,390,200,30);

        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
//        cardIdTxt.setText(readerEntryPanel.getCardNrLbl().getText());
        cardIdTxt.setVisible(false);

    }

    private void actions(){

        resignBook.addActionListener(e ->{

            List<BookTransfer> book = reservedBooks.getSelectedValuesList();

            for (BookTransfer aBook : book) {
                bookTransfer.unReserveBook(aBook.getBook_id());
                JOptionPane.showMessageDialog(this, bookTransfer.getMessage());
                repaint();
                revalidate();
            }

            if(book.size() == 0)
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
        });
    }

    private void addComps(){

        add(reservedBooksLabel);
        add(resignBook);
        add(reservedBooks);
        add(lentBooksLabel);
        add(lentBooks);
        add(back);
        add(cardIdTxt);
    }

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }

    public JButton getBack() {
        return back;
    }
}
