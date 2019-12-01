package book;

import javax.swing.*;

class MyFrame extends JFrame {

    MyFrame(){

        setSize(500, 500);

        AddBookPanel addBookPanel = new AddBookPanel();
        add(addBookPanel);

    }
}
