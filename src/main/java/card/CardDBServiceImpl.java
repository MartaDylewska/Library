package card;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CardDBServiceImpl implements ICardDBService {

    private static final String dbHost = "packy.db.elephantsql.com";
    private static final String dbPort = "5432";
    private static final String dbUser = "dxdqdjgq";
    private static final String dbPass = "k4T24isJOkQ8D4Kndq3yr8am_GjQd3RJ";
    private static final String dbName = "dxdqdjgq";

    public CardDBServiceImpl (){
    }

    @Override
    public void addCardInDB(){
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            Card card = new Card(LocalDate.now());
            String queryInsertCard = "INSERT INTO card(issuedate)" + "VALUES(?)";

            preparedStatement = connection.prepareStatement(queryInsertCard);

            //data musi być podana w formacie sql
            Date sqlFormatDate = java.sql.Date.valueOf(card.getIssueDate());

            //ustawiamy tylko datę, id ustawia się automatycznie
            preparedStatement.setDate(1, sqlFormatDate);
            preparedStatement.executeUpdate();
            System.out.println("New card was added to database");
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
    public void deleteCardFromDB(int idCard) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            String queryDeleteCard = "DELETE FROM card WHERE idcard = ? ";

            preparedStatement = connection.prepareStatement(queryDeleteCard);
            preparedStatement.setInt(1,idCard);
            preparedStatement.execute();
            System.out.println("Card with id " + idCard + " was deleted from database");
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
    public Card readCardFromDB(int idCard) {
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;

        try {
            String queryReadCard = "SELECT * FROM card WHERE idcard = ? ";
            Card card = new Card();
            preparedStatement = connection.prepareStatement(queryReadCard);
            preparedStatement.setInt(1,idCard);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()){
                card.setIdCard(resultSet.getInt("idcard"));
                card.setIssueDate(resultSet.getDate(2).toLocalDate());
            }
            return card;
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
    public List<Card> getAllCardsFromDB()  {
        List<Card> cardList = new ArrayList<>();
        String queryAllCards = "SELECT * FROM CARD";
        Connection connection = initializeDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(queryAllCards);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                int cardId = resultSet.getInt(1);
                LocalDate cardIssueDate = resultSet.getDate(2).toLocalDate();
                cardList.add(new Card(cardId,cardIssueDate));
            }
        }
        catch (SQLException e){
            System.err.println("Error during invoke SQL query: \n" + e.getMessage());
            throw  new RuntimeException("Error during invoke SQL query");
        }
        finally {
                closeDBResources(connection,preparedStatement);
        }
        return cardList;
    }

    private Connection initializeDataBaseConnection(){
        try{
            return DriverManager.getConnection("jdbc:postgresql://"+dbHost+":"+dbPort+"/"+dbName,dbUser,dbPass);
        }
        catch (SQLException e){
            System.err.println("Server can't initialize database connection: \n" + e);
            throw  new RuntimeException("Server can't initialize database connection");
        }
    }

    private void closeDBResources(Connection connection, Statement statement){
        try {
            if(statement != null)
                statement.close();

            if(connection != null)
                connection.close();
        }
        catch (SQLException e){
            System.err.println("Error while closing database resources: \n" + e);
            throw new RuntimeException("Error while closing database resources");
        }
    }
}
