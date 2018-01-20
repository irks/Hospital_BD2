package model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Konsultacja {

	private LongProperty konsultacjaId;
    private StringProperty data;
    private LongProperty pacjentPesel;
    private LongProperty lekarzPesel;
    
    
	public Konsultacja(long konsultacjaId, String data, long pacjentPesel, long lekarzPesel) {
		this.konsultacjaId = new SimpleLongProperty(konsultacjaId);
		this.data = new SimpleStringProperty(data); 
		this.pacjentPesel = new SimpleLongProperty(pacjentPesel);
		this.lekarzPesel = new SimpleLongProperty(lekarzPesel);
	}


	public LongProperty getKonsultacjaId() {
		return konsultacjaId;
	}


	public void setKonsultacjaId(LongProperty konsultacjaId) {
		this.konsultacjaId = konsultacjaId;
	}


	public StringProperty getData() {
		return data;
	}


	public void setData(StringProperty data) {
		this.data = data;
	}


	public LongProperty getPacjentPesel() {
		return pacjentPesel;
	}


	public void setPacjentPesel(LongProperty pacjentPesel) {
		this.pacjentPesel = pacjentPesel;
	}


	public LongProperty getLekarzPesel() {
		return lekarzPesel;
	}


	public void setLekarzPesel(LongProperty lekarzPesel) {
		this.lekarzPesel = lekarzPesel;
	}
 
	
    
}
