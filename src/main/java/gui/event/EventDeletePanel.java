package gui.event;


import event.Event;
import event.EventDBServiceImpl;
import event.IEventDBService;
import gui.Auxiliary;
import gui.general.CustButton;
import images.IPosterDBService;
import images.Poster;
import images.PosterDBServiceImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class EventDeletePanel extends JPanel {
    private JComboBox eventChooserBx;
    private CustButton viewEventBtn, deleteEventBtn, returnBtn;
    private JLabel titleLbl, dateLbl, posterLbl, shortDescLbl, posterShowLbl;
    private JTextField titleTxt, dateTxt;
    private JTextArea shortDescTxt;
    int fieldLength = 200;
    int deltaY = 20;
    private JLabel rectLabel;
    private List<Component> componentBoldList = new ArrayList<>();
    private List<Component> componentPlainList = new ArrayList<>();

    private IPosterDBService posterDBService = new PosterDBServiceImpl();
    private IEventDBService eventDBService = new EventDBServiceImpl();

    public EventDeletePanel() {
        setLayout(null);
        createItems();
        addItems();

        setComponentsEditability(false);
        addActionViewEventBtn();
        addActionDeleteEventBtn();
        createRectLabel();
        add(rectLabel);
        setCompVisibility(false);
        createCompList();
        setFont();
        Auxiliary.setImageAsBackground(this);
    }

    private void createCompList(){
        componentBoldList.add(titleLbl);
        componentBoldList.add(dateLbl);
        componentBoldList.add(posterLbl);
        componentBoldList.add(shortDescLbl);
        componentPlainList.add(titleTxt);
        componentPlainList.add(dateTxt);
        componentPlainList.add(shortDescTxt);
        componentPlainList.add(eventChooserBx);
    }

    private void setFont(){
        for (Component c: componentBoldList)
            c.setFont(Auxiliary.panelFont);
        for (Component c: componentPlainList)
            c.setFont(Auxiliary.panelPlainFont);
    }

    private void createRectLabel(){
        rectLabel = new JLabel();
        rectLabel.setBounds(30,50,350,510);
        rectLabel.setBackground(new Color(215,204,200,200));
        //rectLabel.setVisible(false);
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
        add(deleteEventBtn);
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
        createDeleteEventBtn();
    }

    private void addActionDeleteEventBtn() {
        deleteEventBtn.addActionListener(e -> {
            List<Event> eventList = eventDBService.getAllEventsFromDB();
            Event selectedEvent = null;
            for (Event event : eventList) {
                if ((event.getIdEvent() + ". " + event.getTitle()).equals(eventChooserBx.getSelectedItem()))
                    selectedEvent = event;
            }
            eventDBService.deleteEvent(selectedEvent.getIdEvent());
            setCompVisibility(false);
            createEventChooserBx();
        });
    }

    private void addActionViewEventBtn() {
        viewEventBtn.addActionListener(e -> {
            List<Event> eventList = eventDBService.getAllEventsFromDB();
            Event selectedEvent = null;
            if (eventChooserBx.getSelectedIndex() != 0) {
                setCompVisibility(true);
                deleteEventBtn.setVisible(true);
                for (Event event : eventList) {
                    if ((event.getIdEvent() + ". " + event.getTitle()).equals(eventChooserBx.getSelectedItem())) {
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

    private void createDeleteEventBtn() {
        deleteEventBtn = new CustButton();
        deleteEventBtn.setText("Usuń");
        deleteEventBtn.setBounds(450, 150, 200, 30);
        deleteEventBtn.setVisible(false);
    }

    private void createViewEventBtn() {
        viewEventBtn = new CustButton();
        viewEventBtn.setText("Podgląd");
        viewEventBtn.setBounds(450, 10, 200, 30);
    }

    private void createReturnBtn() {
        returnBtn = new CustButton();
        returnBtn.setText("Powrót");
        returnBtn.setBounds(450, 300, 200, 30);
    }

    private void createEventChooserBx() {
        eventChooserBx = new JComboBox();
        List<Event> eventList = eventDBService.getAllEventsFromDB();
        eventChooserBx.addItem("---wybierz---");
        for (Event e : eventList) {
            eventChooserBx.addItem(e.getIdEvent() + ". " + e.getTitle());
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

    public CustButton getReturnBtn() {
        return returnBtn;
    }

}
