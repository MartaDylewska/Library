package gui.book;

import javax.swing.*;

public class BookFrame extends JFrame {

    private BookPanel bookPanel;
    private BookAddPanel bookAddPanel;
    private BookGetPanel bookGetPanel;

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

            bookAddPanel.getBack().addActionListener(e1 ->{
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

            bookGetPanel.getBack().addActionListener(e1 ->{
                add(bookPanel);
                remove(bookGetPanel);
                repaint();
                revalidate();
            });

            bookGetPanel.getEdit().addActionListener(e1 ->{
                BookEditPanel bookEditPanel = new BookEditPanel(bookGetPanel);
                add(bookEditPanel);
                remove(bookGetPanel);
                repaint();
                revalidate();

                bookEditPanel.getBack().addActionListener(e2 ->{
                    add(bookGetPanel);
                    remove(bookEditPanel);
                    repaint();
                    revalidate();
                });
            });
        });

//        bookPanel.getEditBook().addActionListener(e ->{
//            bookGetPanel = new BookGetPanel();
//            add(bookGetPanel);
//            remove(bookPanel);
//            repaint();
//            revalidate();
//
//            bookGetPanel.getBack().addActionListener(e1 ->{
//                add(bookPanel);
//                remove(bookGetPanel);
//                repaint();
//                revalidate();
//            });
//        });
    }
}
