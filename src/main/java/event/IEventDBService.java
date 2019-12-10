package event;

import java.time.LocalDate;
import java.util.List;

public interface IEventDBService {
    void addEvent(Event event);
    void deleteEvent(int idEvent);
    void joinEvent(int idEvent,int idReader);
    int ifReaderJoined(int idEvent, int idReader);
    Event readEvent(int idEvent);
    Event readLastEventFromDB();
    List<Event> getAllEventsFromDB();
    List<Event> getAllEventsForUser(int readerId);
    void updateEventInDB(int idEvent, String title, LocalDate dateEvent, int imgId, String shortDescription);
}
