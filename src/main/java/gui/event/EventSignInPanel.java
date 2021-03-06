package gui.event;

import event.Event;
import event.EventDBServiceImpl;
import event.IEventDBService;
import gui.Auxiliary;
import gui.general.CustButton;
import gui.general.MyButton;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;
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

public class EventSignInPanel extends JPanel {
    private JComboBox eventChooserBx;
    private CustButton viewEventBtn, signInEventBtn, returnBtn;
    private JLabel titleLbl, dateLbl, posterLbl, shortDescLbl, posterShowLbl;
    private JTextField titleTxt, dateTxt;
    private JTextArea shortDescTxt;
    private JLabel cardIdTxt;
    int fieldLength = 200;
    int deltaY = 20;
    Event selectedEvent = null;
    private JLabel rectLabel;
    private List<Component> componentList = new ArrayList<>();

    private IPosterDBService posterDBService = new PosterDBServiceImpl();
    private IEventDBService eventDBService = new EventDBServiceImpl();
    private IReaderDBService readerDBService = new ReaderDBServiceImpl();
    private IUserDBService userDBService = new UserDBServiceImpl();

    public EventSignInPanel() {
        setLayout(null);
        createItems();
        addItems();
        setComponentsEditability(false);
        addActionViewEventBtn();
        addActionSingInEventBtn();
        createRectLabel();
        add(rectLabel);
        setCompVisibility(false);
        createCompList();
        setFont();
        Auxiliary.setImageAsBackground(this);
    }

    private void createCompList(){
        componentList.add(titleLbl);
        componentList.add(dateLbl);
        componentList.add(posterLbl);
        componentList.add(shortDescLbl);
        componentList.add(titleTxt);
        componentList.add(dateTxt);
        componentList.add(shortDescTxt);
        componentList.add(eventChooserBx);
    }

    private void setFont(){
        for (Component c: componentList)
            c.setFont(Auxiliary.panelPlainFont);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(30,50,350,510);
        rectLabel.setBackground(new Color(215,204,200,200));
        rectLabel.setVisible(false);
        rectLabel.setBorder(Auxiliary.blackBorder());
        rectLabel.setOpaque(true);
    }

    private void addItems() {
        add(eventChooserBx);
        add(dateLbl);
        add(dateTxt);
        add(posterLbl);
        add(posterShowLbl);
        add(shortDescLbl);
        add(shortDescTxt);
        add(titleLbl);
        add(titleTxt);
        add(viewEventBtn);
        add(returnBtn);
        add(signInEventBtn);
        add(cardIdTxt);
    }

    private void createItems() {
        createEventChooserBx();
        createDateLbl();
        createDateTxt();
        createPosterLbl();
        createPosterShowLbl();
        createShortDescLbl();
        createShortDescTxt();
        createTitleLbl();
        createTitleTxt();
        createViewEventBtn();
        createReturnBtn();
        createSignInEventBtn();
        createCardIdTxt();
    }

    private void addActionSingInEventBtn() {
        signInEventBtn.addActionListener(e -> {
            List<Event> eventList = eventDBService.getAllEventsFromDB();
            if (eventChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                signInEventBtn.setVisible(true);
                for (Event event : eventList) {
                    if ((event.getDateEvent().toString() + " " + event.getTitle()).equals(eventChooserBx.getSelectedItem())) {
                        selectedEvent = event;
                    }
                }}
            int idEvent = selectedEvent.getIdEvent();
            User user = userDBService.readUserFromDB(Integer.parseInt(cardIdTxt.getText()));
            Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
            if(eventDBService.ifReaderJoined(idEvent,reader.getIdReader())!=0){
                JOptionPane.showMessageDialog(this,"Już jesteś zapisany na to wydarzenie");
                setCompVisibility(false);
            }
            else {
                eventDBService.joinEvent(idEvent, reader.getIdReader());
                JOptionPane.showMessageDialog(this, "Dołączyłeś do wybranego wydarzenia");
                setCompVisibility(false);
                createEventChooserBx();
            }
        });
    }

    private void addActionViewEventBtn() {
        viewEventBtn.addActionListener(e -> {
            List<Event> eventList = eventDBService.getAllEventsFromDB();

            if (eventChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                signInEventBtn.setVisible(true);
                for (Event event : eventList) {
                    if ((event.getDateEvent().toString() + " " + event.getTitle()).equals(eventChooserBx.getSelectedItem())) {
                        selectedEvent = event;
                    }
                }
                titleTxt.setText(selectedEvent.getTitle());
                dateTxt.setText(selectedEvent.getDateEvent().toString());
                shortDescTxt.setText(selectedEvent.getShortDescription());
                posterDBService = new PosterDBServiceImpl();
                Poster poster = posterDBService.readImageById(selectedEvent.getImgId());
                ImageIcon icon = new ImageIcon(poster.getImgBytes());
                posterShowLbl.setIcon(icon);
            } else {
                JOptionPane.showMessageDialog(this, "Proszę wybrać wydarzenie");
            }
        });
    }

    private void createCardIdTxt(){
        cardIdTxt = new JLabel();
        cardIdTxt.setBounds(30,30, 30,20);
    }

    private void createSignInEventBtn() {
        signInEventBtn = new CustButton();
        signInEventBtn.setText("Dołącz");
        signInEventBtn.setBounds(450, 150, 200, 50);
        signInEventBtn.setVisible(false);
    }

    private void createViewEventBtn() {
        viewEventBtn = new CustButton();
        viewEventBtn.setText("Podgląd");
        viewEventBtn.setBounds(450, 10, 200, 50);
    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 50);
    }

    private void createEventChooserBx() {
        eventChooserBx = new JComboBox();
        List<Event> eventList = eventDBService.getAllEventsFromDB();
        eventChooserBx.addItem("---wybierz---");
        for (Event e : eventList) {
            eventChooserBx.addItem(e.getDateEvent().toString() + " " + e.getTitle());
        }
        eventChooserBx.setBounds(30, 10, 350, 30);
    }

    private void createShortDescLbl() {
        shortDescLbl = new JLabel();
        shortDescLbl.setText("Krótki opis");
        shortDescLbl.setBounds(40, 120 + deltaY + 300 + 20, 100, 30);
    }

    private void createShortDescTxt() {
        shortDescTxt = new JTextArea();
        shortDescTxt.setBounds(150, 120 + deltaY + 300 + 20, fieldLength, 80);
        shortDescTxt.setWrapStyleWord(true);
        shortDescTxt.setLineWrap(true);
    }


    private void createPosterLbl() {
        posterLbl = new JLabel();
        posterLbl.setText("Plakat");
        posterLbl.setBounds(40, 120 + deltaY, 100, 30);
    }

    private void createPosterShowLbl() {
        posterShowLbl = new JLabel();
        posterShowLbl.setBounds(150, 120 + deltaY, fieldLength, 300);
    }


    private void createDateLbl() {
        dateLbl = new JLabel();
        dateLbl.setText("Data");
        dateLbl.setBounds(40, 80 + deltaY, 100, 30);
    }

    private void createDateTxt() {
        dateTxt = new JTextField();
        dateTxt.setBounds(150, 80 + deltaY, fieldLength, 30);
    }

    private void createTitleLbl() {
        titleLbl = new JLabel();
        titleLbl.setText("Tytuł");
        titleLbl.setBounds(40, 40 + deltaY, 100, 30);
    }

    private void createTitleTxt() {
        titleTxt = new JTextField();
        titleTxt.setBounds(150, 40 + deltaY, fieldLength, 30);
    }


    private void setCompVisibility(boolean visibility) {
        titleLbl.setVisible(visibility);
        dateLbl.setVisible(visibility);
        posterLbl.setVisible(visibility);
        shortDescLbl.setVisible(visibility);
        titleTxt.setVisible(visibility);
        dateTxt.setVisible(visibility);
        posterShowLbl.setVisible(visibility);
        shortDescTxt.setVisible(visibility);
        rectLabel.setVisible(visibility);
    }

    private void setComponentsEditability(boolean editability) {
        titleTxt.setEditable(editability);
        dateTxt.setEditable(editability);
        shortDescTxt.setEditable(editability);
    }

    public JLabel getPosterShowLbl() {
        return posterShowLbl;
    }

    public void setPosterShowLbl(JLabel posterShowLbl) {
        this.posterShowLbl = posterShowLbl;
    }

    public JLabel getCardIdTxt() {
        return cardIdTxt;
    }

    public void setCardIdTxt(JLabel cardIdTxt) {
        this.cardIdTxt = cardIdTxt;
    }

    public CustButton getReturnBtn() {
        return returnBtn;
    }
}
