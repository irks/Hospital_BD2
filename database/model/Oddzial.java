package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Oddzial {
	private LongProperty oddzialId;
	private StringProperty nazwa;
	private IntegerProperty liczbaLozek;

	public Oddzial(long oddzialId, String nazwa, int liczbaLozek) {
		this.oddzialId = new SimpleLongProperty(oddzialId);
		this.nazwa = new SimpleStringProperty(nazwa); 
		this.liczbaLozek = new SimpleIntegerProperty(liczbaLozek);
	}

	public LongProperty getOddzialId() {
		return oddzialId;
	}

	public void setOddzialId(LongProperty oddzialId) {
		this.oddzialId = oddzialId;
	}

	public StringProperty getNazwa() {
		return nazwa;
	}

	public void setNazwa(StringProperty nazwa) {
		this.nazwa = nazwa;
	}

	public IntegerProperty getLiczbaLozek() {
		return liczbaLozek;
	}

	public void setLiczbaLozek(IntegerProperty liczbaLozek) {
		this.liczbaLozek = liczbaLozek;
	}



}
