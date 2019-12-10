package event;

import java.time.LocalDate;
import java.util.List;

public interface IEventDBService {
    void addEvent(Event event);
    void deleteEvent(int idEvent);
    Event readEvent(int idEvent);
    List<Event> getAllEventsFromDB();
    void updateEventInDB(int idEvent, String title, LocalDate dateEvent, int imgId, String shortDescription);
}
