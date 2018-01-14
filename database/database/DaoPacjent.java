package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pacjent;

public class DaoPacjent {

	public static Pacjent searchEmployee (String pacjId) throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pacjent WHERE id="+pacjId;
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPacj = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Pacjent pacjent = getPacjentFromResultSet(rsPacj);
 
            //Return employee object
            return pacjent;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + pacjId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Pacjent getPacjentFromResultSet(ResultSet rs) throws SQLException
    {
        Pacjent pacjent = null;
        if (rs.next()) {
        	long id = rs.getLong("ID");
        	String name = rs.getString("NAME");
        	String surname = rs.getString("SURNAME");
        	long pesel = rs.getLong("PESEL");
        	pacjent = new Pacjent(id, name, surname, pesel);
        }
        return pacjent;
    }
 
    public static ObservableList<Pacjent> searchPacjencis () throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM pacjent";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPacjs = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Pacjent> pacjList = getPacjentList(rsPacjs);
 
            //Return employee object
            return pacjList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Select * from employees operation
    private static ObservableList<Pacjent> getPacjentList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Pacjent> pacjList = FXCollections.observableArrayList();
 
        while (rs.next()) {
        	long id = rs.getLong("ID");
        	String name = rs.getString("NAME");
        	String surname = rs.getString("SURNAME");
        	long pesel = rs.getLong("PESEL");
        	Pacjent pacjent = new Pacjent(id, name, surname, pesel);
            //Add employee to the ObservableList
        	pacjList.add(pacjent);
        }
        //return empList (ObservableList of Employees)
        return pacjList;
    }
 
    public static void updateSurname (String pacjId, String surname) throws Exception {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE pacjent\n" +
                        "      SET SURNAME = '" + surname + "'\n" +
                        "    WHERE ID = " + pacjId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
 
    public static void deletePacjWithId (String pacjId) throws Exception {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM pacjent\n" +
                        "         WHERE id ="+ pacjId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
 
    public static void insertPacjent (String name, String lastname, long pesel) throws Exception {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO pacjent\n" +
                        "(NAME, SURNAME, PESEL)\n" +
                        "VALUES\n" +
                        "("+name+"', '"+lastname+"','"+pesel+"', SYSDATE, 'IT_PROG');\n" +
                        "END;";
 
        //Execute DELETE operation
        try {
            DAOManager.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}

