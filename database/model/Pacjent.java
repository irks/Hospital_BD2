package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pacjent {
	private LongProperty pacjent_id;
    private StringProperty name;
    private StringProperty surname;
    private LongProperty pesel;
    
    
	public Pacjent(long pacjent_id, String name, String surname, long pesel) {
		this.pacjent_id = new SimpleLongProperty(pacjent_id);
		this.name = new SimpleStringProperty(name); 
		this.surname = new SimpleStringProperty(surname);
		this.pesel = new SimpleLongProperty(pesel);
	}
	public LongProperty getPacjent_id() {
		return pacjent_id;
	}
	public void setPacjent_id(LongProperty pacjent_id) {
		this.pacjent_id = pacjent_id;
	}
	public StringProperty getName() {
		return name;
	}
	public void setName(StringProperty name) {
		this.name = name;
	}
	public StringProperty getSurname() {
		return surname;
	}
	public void setSurname(StringProperty surname) {
		this.surname = surname;
	}
	public LongProperty getPesel() {
		return pesel;
	}
	public void setPesel(LongProperty pesel) {
		this.pesel = pesel;
	}
 
    
}
