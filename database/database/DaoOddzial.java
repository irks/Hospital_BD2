package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Oddzial;
import model.Pacjent;

public class DaoOddzial {
	//Select * from employees operation
    private static ObservableList<Oddzial> getOddzialList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Oddzial> oddzialList = FXCollections.observableArrayList();
 
        while (rs.next()) {
        	long id = rs.getLong("ID");
        	String nazwa = rs.getString("NAZWA");
        	Integer liczbaLozek = rs.getInt("ILOSC_LOZEK");
        	Oddzial oddzial = new Oddzial(id, nazwa, liczbaLozek);
            //Add employee to the ObservableList
        	oddzialList.add(oddzial);
        }
        //return empList (ObservableList of Employees)
        return oddzialList;
    }
    
    public static ObservableList<Oddzial> searchOddzialy () throws Exception {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM oddzial";
 
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsPacjs = DAOManager.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Oddzial> oddzialList = getOddzialList(rsPacjs);
 
            //Return employee object
            return oddzialList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }
}
