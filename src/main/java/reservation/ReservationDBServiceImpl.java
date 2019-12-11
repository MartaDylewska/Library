package reservation;

import reader.IReaderDBService;
import reader.Reader;
import reader.ReaderDBServiceImpl;
import user.IUserDBService;
import user.User;
import user.UserDBServiceImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static config.DBConfig.closeDBResources;
import static config.DBConfig.initializeDataBaseConnection;

public class ReservationDBServiceImpl implements IReservationDBService {


    @Override
    public void addReservation(Reservation reservation) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO reservation (book_id, datecreate, reader_id) " + "VALUES (?,?,?) ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,reservation.getBook_id());
            Date sqlFormatDate = java.sql.Date.valueOf(reservation.getDateCreation());
            preparedStatement.setDate(2,sqlFormatDate);
            preparedStatement.setInt(3,reservation.getReader_id());
            preparedStatement.executeUpdate();
            System.out.println("New reservation was added to database");
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
    public void deleteReservation(int idReservation) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "DELETE FROM reservation WHERE idreservation = ? ";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,idReservation);
            preparedStatement.execute();
            System.out.println("Reservation was deleted");
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
    public List<Reservation> showAllReservations() {
        List<Reservation> reservationList = new ArrayList<>();
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT * FROM reservation;" ;
            preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("idreservation"));
                reservation.setBook_id(resultSet.getInt("book_id"));
                reservation.setReader_id(resultSet.getInt("reader_id"));
                reservation.setDateCreation(resultSet.getDate("datecreate").toLocalDate());

                reservationList.add(reservation);
            }
            return reservationList;
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
    public List<Reservation> showReservationForUser(int cardNr) {
        List<Reservation> reservationList = new ArrayList<>();

        IUserDBService userDBService = new UserDBServiceImpl();
        User user = userDBService.readUserFromDB(cardNr);
        IReaderDBService readerDBService = new ReaderDBServiceImpl();
        Reader reader = readerDBService.readReaderFromDB(user.getIdUser());
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String query = "SELECT * FROM reservation WHERE reader_id = (?);" ;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,reader.getIdReader());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Reservation reservation = new Reservation();
                reservation.setIdReservation(resultSet.getInt("idreservation"));
                reservation.setBook_id(resultSet.getInt("book_id"));
                reservation.setReader_id(resultSet.getInt("reader_id"));
                reservation.setDateCreation(resultSet.getDate("datecreate").toLocalDate());

                reservationList.add(reservation);
            }
            return reservationList;
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
