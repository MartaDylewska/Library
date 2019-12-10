package event;

import java.time.LocalDate;

public class Event {
    private int idEvent;
    private String title;
    private LocalDate dateEvent;
    private int imgId;
    private String shortDescription;

    public Event(String title, LocalDate dateEvent, int imgId, String shortDescription) {
        this.title = title;
        this.dateEvent = dateEvent;
        this.imgId = imgId;
        this.shortDescription = shortDescription;
    }

    public Event(){}

    public Event(int idEvent, String title, LocalDate dateEvent, int imgId, String shortDescription) {
        this.idEvent = idEvent;
        this.title = title;
        this.dateEvent = dateEvent;
        this.imgId = imgId;
        this.shortDescription = shortDescription;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(LocalDate dateEvent) {
        this.dateEvent = dateEvent;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Override
    public String toString() {
        return "Wydarzenie "+title;
    }
}
