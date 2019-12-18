package gui.bookTransfer;

import bookTransfer.BookTransfer;
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
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class BookTransferPanel extends JPanel {

    private IBookTransfer bookTransfer = new BookTransferService();

    private JLabel userIdLabel, lentBooksLabel, reservedBooksLabel, lendBookLabel, returnBookLabel;
    private JTextField userId;
    private MyButton showBooks, lendBook, returnBooks, back;
    private JList<BookTransfer> lentBooks, reservedBooks;

    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();

    private int readerId;

    public BookTransferPanel(){

        setLayout(null);
        createComps();
        addComps();
        actions();

    }

    private void createReservedBooks(List<BookTransfer> usersReservedBooks){

        DefaultListModel listModel = new DefaultListModel();
        for (BookTransfer aBookList : usersReservedBooks) {
            listModel.addElement(aBookList);
        }
        reservedBooks.setModel(listModel);
    }

    private void createLentBooks(List<BookTransfer> usersLentBooks) {

        DefaultListModel listModel = new DefaultListModel();
        for (BookTransfer aBookList : usersLentBooks) {
            listModel.addElement(aBookList);
        }
        lentBooks.setModel(listModel);
    }

    private void createComps(){

        userIdLabel = new JLabel("Numer karty:");
        userIdLabel.setBounds(50,40,100,30);

        userId = new JTextField();
        userId.setBounds(200,40,200, 30);

        showBooks = new MyButton(true);
        showBooks.setText("Pokaż");
        showBooks.setBounds(450,40,200,30);

        lendBookLabel = new JLabel("Zarezerowane książki:");
        lendBookLabel.setBounds(50,100,200,30);

        reservedBooksLabel = new JLabel();
        reservedBooksLabel.setBounds(50,140,600,100);
        reservedBooksLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        reservedBooksLabel.setBackground(Color.white);
        reservedBooksLabel.setOpaque(true);
        reservedBooksLabel.setVerticalAlignment(1);

        reservedBooks = new JList<>();
        reservedBooks.setBounds(50,140,600,100);
        reservedBooks.setBorder(BorderFactory.createLineBorder(Color.black));

        lendBook = new MyButton(true);
        lendBook.setText("Wypożycz");
        lendBook.setBounds(450,100,200,30);

        returnBookLabel = new JLabel("Wypożyczone książki:");
        returnBookLabel.setBounds(50,270,200,30);

        lentBooksLabel = new JLabel();
        lentBooksLabel.setBounds(50,310,600,100);
        lentBooksLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        lentBooksLabel.setBackground(Color.white);
        lentBooksLabel.setOpaque(true);
        lentBooksLabel.setVerticalAlignment(1);

        lentBooks = new JList<>();
        lentBooks.setBounds(50,310,600,100);
        lentBooks.setBorder(BorderFactory.createLineBorder(Color.black));

        returnBooks = new MyButton(true);
        returnBooks.setText("Przyjmij zwrot");
        returnBooks.setBounds(450, 270, 200,30);

        back = new MyButton(false);
        back.setText("Cofnij");
        back.setBounds(450,430,200,30);
    }

    private void actions(){

        showBooks.addActionListener(e -> {
            if(check()) {

                User user = userDBService.readUserFromDB(Integer.parseInt(userId.getText()));
                Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
                readerId = reader.getIdReader();

                List<BookTransfer> reservedUserBooks = bookTransfer.getReservedUserBooks(readerId);
                List<BookTransfer> lentUserBooks = bookTransfer.getLentUserBooks(readerId);

                if (reservedUserBooks.size() > 0) {
                    createReservedBooks(reservedUserBooks);
                    add(reservedBooks);
                } else {
                    reservedBooksLabel.setText("Brak zarezerowanych książek.");
                }

                if (lentUserBooks.size() > 0) {
                    createLentBooks(lentUserBooks);
                    add(lentBooks);
                } else {
                    lentBooksLabel.setText("Brak wypożyczonych książek.");
                }

                if(lentUserBooks.size() >= 3){
                    lendBook.setEnabled(false);
                } else {
                    lendBook.setEnabled(true);
                }

                for (BookTransfer books : reservedUserBooks) {
                    if(books.getDuedate().before(new Date()))
                        bookTransfer.unReserveBook(books.getAuthorBook().getBook().getBookId());
                }
            }
        });

        lendBook.addActionListener(e ->{

            List<BookTransfer> book = reservedBooks.getSelectedValuesList();

            User user = userDBService.readUserFromDB(Integer.parseInt(userId.getText()));
            Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
            readerId = reader.getIdReader();

            for (BookTransfer aBook : book) {
                bookTransfer.lendBook(readerId, aBook.getAuthorBook().getBook().getBookId());
                bookTransfer.unReserveBook(aBook.getAuthorBook().getBook().getBookId());
                JOptionPane.showMessageDialog(this, bookTransfer.getMessage());
                repaint();
                revalidate();
            }

            if(book.size() == 0)
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
        });

        returnBooks.addActionListener(e ->{

            List<BookTransfer> book = lentBooks.getSelectedValuesList();

            for (BookTransfer aBook : book) {
                bookTransfer.acceptReturnBook(Integer.parseInt(userId.getText()), aBook.getAuthorBook().getBook().getBookId());
                JOptionPane.showMessageDialog(this, bookTransfer.getMessage());
                repaint();
                revalidate();
            }

            if(book.size() == 0)
                JOptionPane.showMessageDialog(this, "Żadna książka nie została wybrana.");
        });
    }

    private void addComps(){

        add(userIdLabel);
        add(userId);
        add(showBooks);
        add(lendBookLabel);
        add(reservedBooksLabel);
        add(lendBook);
        add(returnBookLabel);
        add(lentBooksLabel);
        add(returnBooks);
        add(back);
    }

    private boolean check(){

        Pattern pattern = Pattern.compile("[0-9]+");
        boolean isCorrect = pattern.matcher(userId.getText()).matches();

        if(!isCorrect)
            JOptionPane.showMessageDialog(this, "Nieprawidłowe ID.");

        return isCorrect && !(userId.getText().isEmpty());
    }

    public MyButton getBack() {
        return back;
    }
}
