package gui.book;

import javax.swing.*;

public class BookFrame extends JFrame {

    private BookPanel bookPanel;
    private BookAddPanel bookAddPanel;
    private BookGetPanel bookGetPanel;
    private AuthorGetPanel authorGetPanel;

    public BookFrame(){

        setSize(700,600);

        bookPanel = new BookPanel();
        add(bookPanel);

        bookPanel.getAddBook().addActionListener(e ->{
            bookAddPanel = new BookAddPanel();
            add(bookAddPanel);
            remove(bookPanel);
            repaint();
            revalidate();

            bookAddPanel.getReturnBtn().addActionListener(e1 ->{
                add(bookPanel);
                remove(bookAddPanel);
                repaint();
                revalidate();
            });
        });

        bookPanel.getFindBook().addActionListener(e ->{
            bookGetPanel = new BookGetPanel();
            add(bookGetPanel);
            remove(bookPanel);
            repaint();
            revalidate();

            bookGetPanel.getReturnBtn().addActionListener(e1 ->{
                add(bookPanel);
                remove(bookGetPanel);
                repaint();
                revalidate();
            });

            bookGetPanel.getEdit().addActionListener(e1 ->{
                if(bookGetPanel.getBookIdToEdit() != 0){
                    BookEditPanel bookEditPanel = new BookEditPanel(bookGetPanel);
                    add(bookEditPanel);
                    remove(bookGetPanel);
                    repaint();
                    revalidate();

                    bookEditPanel.getReturnBtn().addActionListener(e2 ->{
                        add(bookGetPanel);
                        remove(bookEditPanel);
                        repaint();
                        revalidate();
                    });
                } else {
                    JOptionPane.showMessageDialog(null, "Żadna książka nie została wybrana.");
                }
            });
        });

        bookPanel.getFindAuthor().addActionListener(e ->{
            authorGetPanel = new AuthorGetPanel();
            add(authorGetPanel);
            remove(bookPanel);
            repaint();
            revalidate();

            authorGetPanel.getBack().addActionListener(e1 ->{
                add(bookPanel);
                remove(authorGetPanel);
                repaint();
                revalidate();
            });
        });
    }
}
