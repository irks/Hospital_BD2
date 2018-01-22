package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pacjent;
import model.Rejestracja;
import oracle.jdbc.OracleTypes;

public class DaoRejestracja {

//	public static Rejestracja searchRejestracja (String rejestrId) throws Exception {
//        //Declare a SELECT statement
//        String selectStmt = "SELECT * FROM rejestracja WHERE id="+rejestrId;
// 
//        //Execute SELECT statement
//        try {
//            //Get ResultSet from dbExecuteQuery method
//            ResultSet rsRejestr = DAOManager.dbExecuteQuery(selectStmt);
// 
//            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
//            Rejestracja rejestracja = getRejestracjaFromResultSet(rsRejestr);
// 
//            //Return employee object
//            return rejestracja;
//        } catch (SQLException e) {
//            System.out.println("While searching an rejestracja with " + rejestrId + " id, an error occurred: " + e);
//            //Return exception
//            throw e;
//        }
//    }
 
    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Rejestracja getRejestracjaFromResultSet(ResultSet rs) throws SQLException
    {
        Rejestracja rejestracja = null;
        if (rs.next()) {
        	long idOddzialu = rs.getLong("ID");
        	Date dataOd = rs.getDate("START_DATE");
        	Date dataDo = rs.getDate("END_DATE");
        	long czyPrzyjety = rs.getLong("CZY_PRZYJETY");
        	long numerRejestracji = rs.getLong("NUMER_REJESTARCJI");
        	long idPacjenta = rs.getLong("ID1");
        	rejestracja = new Rejestracja(dataOd, dataDo, czyPrzyjety, numerRejestracji, idOddzialu, idPacjenta);
        }
        return rejestracja;
    }
 
    public static ObservableList<Rejestracja> searchRejestracje () throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rejestracja";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPacjs = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Rejestracja> rejestrList = getRejestracjeList(rsPacjs);
 
            //Return employee object
            return rejestrList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from employees operation
    private static ObservableList<Rejestracja> getRejestracjeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Rejestracja> rejestrList = FXCollections.observableArrayList();
        Rejestracja rejestracja;
        
        while (rs.next()) {
        	long idOddzialu = rs.getLong("ID");
        	Date dataOd = rs.getDate("START_DATE");
        	Date dataDo = rs.getDate("END_DATE");
        	long czyPrzyjety = rs.getLong("CZY_PRZYJETY");
        	long numerRejestracji = rs.getLong("NUMER_REJESTARCJI");
        	long idPacjenta = rs.getLong("ID1");
        	rejestracja = new Rejestracja(dataOd, dataDo, czyPrzyjety, numerRejestracji, idOddzialu, idPacjenta);
        	rejestrList.add(rejestracja);
        }
        //return empList (ObservableList of Employees)
        return rejestrList;
    }
    
    public static ObservableList<Rejestracja> searchRejestracjePacjenta (LongProperty longProperty, boolean czyPrzyjety) throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rejestracja WHERE ID1 = "+ longProperty.longValue() + " and CZY_PRZYJETY = " + (czyPrzyjety ? 1 : 0);
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPacjs = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Rejestracja> rejestrList = getRejestracjeList(rsPacjs);
 
            //Return employee object
            return rejestrList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
    
    public static boolean updateRejestracja (Date dataOd, Date dataDo, boolean czyPrzyjety, long idOddzialu, long idPacjenta, long numerRejestracji ) throws Exception {
        //Declare a UPDATE statement
    	
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE rejestracja\n" +
                        "      SET START_DATE = '" + dataOd + "', END_DATE = '"+ dataDo + "', CZY_PRZYJETY= '" + (czyPrzyjety ? 1 : 0) + 
                        "', ID= '" + idOddzialu  +  "'\n" +
                        "    WHERE ID = " + idOddzialu + "and ID1= " + idPacjenta  + "and NUMER_REJESTARCJI= " + numerRejestracji + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
        return true;
    }
    

    public static void insertRejestracja (Date dataOd, Date dataDo, boolean czyPrzyjety, long idPacjenta, long idOddzialu ) throws Exception {
		String insertStmt = "INSERT INTO rejestracja"
				+ "(START_DATE, END_DATE, CZY_PRZYJETY, ID, ID1) VALUES (?,?,?,?,?)";
		PreparedStatement preparedStatement;
		try{
			DAOManager.connect();
			preparedStatement = DAOManager.getConn().prepareStatement(insertStmt);
//			Date dataOdLocal = Date.valueOf(data)
			preparedStatement.setDate(1, dataOd);
			preparedStatement.setDate(2, dataDo);
			preparedStatement.setObject(3, new oracle.sql.NUMBER((czyPrzyjety ? 1 : 0)), OracleTypes.NUMBER);
			preparedStatement.setObject(4, new oracle.sql.NUMBER(idOddzialu), OracleTypes.NUMBER);
			preparedStatement.setObject(5, new oracle.sql.NUMBER(idPacjenta), OracleTypes.NUMBER);
			preparedStatement.executeUpdate();
			DAOManager.close();
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
    	
        //Declare a DELETE statement
//        String updateStmt =
//                "BEGIN\n" +
//                        "INSERT INTO rejestracja\n" +
//                        "(START_DATE, END_DATE, CZY_PRZYJETY)\n" +
//                        "VALUES\n" +
//                        "('"+dataOd+"', '"+dataDo+"','"++"');\n" +
//                        "END;";
        //Execute DELETE operation
//        try {
//            DAOManager.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while INSERT Operation: " + e);
//            throw e;
//        }
    }
    
    public static boolean deleteRejestracjaWithId (Long rejestrId) throws Exception {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM rejestracja\n" +
                        "         WHERE id ="+ rejestrId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
        
        return true;
    }
    

}
