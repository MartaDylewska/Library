package event;


import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class EventDBServiceImpl implements IEventDBService {

    @Override
    public void addEvent(Event event) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertEvent = "INSERT INTO event (title, dateevent, imgid, shortdesc) " + "VALUES (?,?,?,?) ";
            preparedStatement = connection.prepareStatement(queryInsertEvent);
            preparedStatement.setString(1,event.getTitle());
            Date sqlFormatDate = java.sql.Date.valueOf(event.getDateEvent());
            preparedStatement.setDate(2,sqlFormatDate);
            preparedStatement.setInt(3,event.getImgId());
            preparedStatement.setString(4,event.getShortDescription());
            preparedStatement.executeUpdate();
            System.out.println("New event was added to database");
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void deleteEvent(int idEvent) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryDeleteEvent = "DELETE FROM event WHERE idevent = ? ";
            preparedStatement = connection.prepareStatement(queryDeleteEvent);
            preparedStatement.setInt(1,idEvent);
            preparedStatement.execute();
            System.out.println("Event was deleted");
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void joinEvent(int idEvent, int idReader) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryInsertEvent = "INSERT INTO event_reader (eventid,readerid) " + "VALUES (?,?) ";
            preparedStatement = connection.prepareStatement(queryInsertEvent);
            preparedStatement.setInt(1,idEvent);
            preparedStatement.setInt(2,idReader);
            preparedStatement.executeUpdate();
            System.out.println("Reader joined new event");
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public int ifReaderJoined(int idEvent, int idReader) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryCheckIfUserJoinedAlready = "SELECT COUNT(eventid) FROM event_reader WHERE (readerid,eventid) = (?,?) ";
            preparedStatement = connection.prepareStatement(queryCheckIfUserJoinedAlready);
            preparedStatement.setInt(1,idReader);
            preparedStatement.setInt(2,idEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            int count1 = 0;
            while (resultSet.next())
                count1 = resultSet.getInt(1);
            return count1;
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public Event readEvent(int idEvent) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            String queryReadEvent = "SELECT * FROM event WHERE (idevent) = (?) ";
            preparedStatement = connection.prepareStatement(queryReadEvent);
            preparedStatement.setInt(1,idEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            Event event = new Event();
            while (resultSet.next()){
                event.setIdEvent(resultSet.getInt("idevent"));
                event.setDateEvent(resultSet.getDate("dateevent").toLocalDate());
                event.setTitle(resultSet.getString("title"));
                event.setImgId(resultSet.getInt("imgid"));
                event.setShortDescription(resultSet.getString("shortdesc"));
            }
            return event;
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public Event readLastEventFromDB() {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            String queryReadEvent = "SELECT * FROM event order by idevent desc limit 1;";
            preparedStatement = connection.prepareStatement(queryReadEvent);
            ResultSet resultSet = preparedStatement.executeQuery();
            Event event = new Event();
            while (resultSet.next()){
                event.setIdEvent(resultSet.getInt("idevent"));
                event.setDateEvent(resultSet.getDate("dateevent").toLocalDate());
                event.setTitle(resultSet.getString("title"));
                event.setImgId(resultSet.getInt("imgid"));
                event.setShortDescription(resultSet.getString("shortdesc"));
            }
            return event;
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<Event> getAllEventsFromDB() {
        List<Event> eventList = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryReadEvents = "SELECT * FROM event;" ;
            preparedStatement = connection.prepareStatement(queryReadEvents);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
               Event event = new Event();
                event.setIdEvent(resultSet.getInt("idevent"));
                event.setDateEvent(resultSet.getDate("dateevent").toLocalDate());
                event.setTitle(resultSet.getString("title"));
                event.setImgId(resultSet.getInt("imgid"));
                event.setShortDescription(resultSet.getString("shortdesc"));

                eventList.add(event);
            }
            return eventList;
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public List<Event> getAllEventsForUser(int readerId) {
        List<Event> eventList = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryReadEvents = "Select event.idevent as idevent, event.title as title,event.dateevent as dateevent, event.imgid as imgid, event.shortdesc as shortdesc FROM event_reader \n" +
                    "inner join event \n" +
                    "on event.idevent = event_reader.eventid \n" +
                    "WHERE (readerid) = (?);" ;
            preparedStatement = connection.prepareStatement(queryReadEvents);
            preparedStatement.setInt(1,readerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Event event = new Event();
                event.setIdEvent(resultSet.getInt("idevent"));
                event.setDateEvent(resultSet.getDate("dateevent").toLocalDate());
                event.setTitle(resultSet.getString("title"));
                event.setImgId(resultSet.getInt("imgid"));
                event.setShortDescription(resultSet.getString("shortdesc"));

                eventList.add(event);
            }
            return eventList;
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }

    @Override
    public void updateEventInDB(int idEvent, String title, LocalDate dateEvent, int imgId, String shortDescription) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryUpdateEvent = "UPDATE event SET (title,dateevent,imgid,shortdesc) = (?,?,?,?) " + "where idevent = ?";
            preparedStatement = connection.prepareStatement(queryUpdateEvent);
            preparedStatement.setString(1,title);
            Date sqlDate = java.sql.Date.valueOf(dateEvent);
            preparedStatement.setDate(2,sqlDate);
            preparedStatement.setInt(3,imgId);
            preparedStatement.setString(4,shortDescription);
            preparedStatement.setInt(5,idEvent);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
            closeDBResources(connection,preparedStatement);
        }
    }
}
