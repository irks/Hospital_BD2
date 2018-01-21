package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.LongProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pacjent;
import model.Rejestracja;

public class DaoRejestracja {

	public static Rejestracja searchRejestracja (String rejestrId) throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM rejestracja WHERE id="+rejestrId;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRejestr = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Rejestracja rejestracja = getRejestracjaFromResultSet(rsRejestr);
 
            //Return employee object
            return rejestracja;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + rejestrId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Rejestracja getRejestracjaFromResultSet(ResultSet rs) throws SQLException
    {
        Rejestracja rejestracja = null;
        if (rs.next()) {
        	long id = rs.getLong("ID");
        	String dataOd = rs.getString("DATA_OD");
        	String dataDo = rs.getString("DATA_DO");
        	long czyPrzyjety = rs.getLong("CZY_PRZYJETY(bool)");
        	rejestracja = new Rejestracja(id, dataOd, dataDo, czyPrzyjety);
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
 
        while (rs.next()) {
        	long id = rs.getLong("ID");
        	String dataOd = rs.getString("DATA_OD");
        	String dataDo = rs.getString("DATA_DO");
        	long czyPrzyjety = rs.getLong("CZY_PRZYJETY");
        	Rejestracja rejestracja = new Rejestracja(id, dataOd, dataDo, czyPrzyjety);
            //Add employee to the ObservableList
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
    
    public static boolean updateRejestracja (long rejestracjaId, Date dataOd, Date dataDo, boolean czyPrzyjety ) throws Exception {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE rejestracja\n" +
                        "      SET START_DATE = '" + dataOd + "', END_DATE = '"+ dataDo + "', CZY_PRZYJETY= '" + (czyPrzyjety ? 1 : 0) + "'\n" +
                        "    WHERE ID = " + rejestracjaId + ";\n" +
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
    

    public static void insertRejestracja (Date dataOd, Date dataDo, boolean czyPrzyjety ) throws Exception {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO rejestracja\n" +
                        "(START_DATE, END_DATE, CZY_PRZYJETY)\n" +
                        "VALUES\n" +
                        "('"+dataOd+"', '"+dataDo+"','"+(czyPrzyjety ? 1 : 0)+"');\n" +
                        "END;";
        //Execute DELETE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
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
    
    
 
//    public static void updateSurname (String pacjId, String surname) throws Exception {
//        //Declare a UPDATE statement
//        String updateStmt =
//                "BEGIN\n" +
//                        "   UPDATE pacjent\n" +
//                        "      SET SURNAME = '" + surname + "'\n" +
//                        "    WHERE ID = " + pacjId + ";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
// 
//        //Execute UPDATE operation
//        try {
//            DAOManager.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while UPDATE Operation: " + e);
//            throw e;
//        }
//    }
// 
//    public static void deletePacjWithId (String pacjId) throws Exception {
//        //Declare a DELETE statement
//        String updateStmt =
//                "BEGIN\n" +
//                        "   DELETE FROM pacjent\n" +
//                        "         WHERE id ="+ pacjId +";\n" +
//                        "   COMMIT;\n" +
//                        "END;";
// 
//        //Execute UPDATE operation
//        try {
//            DAOManager.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while DELETE Operation: " + e);
//            throw e;
//        }
//    }
// 
//    public static void insertPacjent (String name, String lastname, long pesel) throws Exception {
//        //Declare a DELETE statement
//        String updateStmt =
//                "BEGIN\n" +
//                        "INSERT INTO pacjent\n" +
//                        "(NAME, SURNAME, PESEL)\n" +
//                        "VALUES\n" +
//                        "("+name+"', '"+lastname+"','"+pesel+"', SYSDATE, 'IT_PROG');\n" +
//                        "END;";
// 
//        //Execute DELETE operation
//        try {
//            DAOManager.dbExecuteUpdate(updateStmt);
//        } catch (SQLException e) {
//            System.out.print("Error occurred while DELETE Operation: " + e);
//            throw e;
//        }
//    }

}
