package model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rejestracja {
	private LongProperty rejestracjaId;
    private StringProperty dataOd;
    private StringProperty dataDo;
    private LongProperty czyPrzyjety;
    
    
	public Rejestracja(long rejestracjaId, String dataOd, String dataDo, long czyPrzyjety) {
		this.rejestracjaId= new SimpleLongProperty(rejestracjaId);
		this.dataOd = new SimpleStringProperty(dataOd); 
		this.dataDo = new SimpleStringProperty(dataDo);
		this.czyPrzyjety = new SimpleLongProperty(czyPrzyjety);
	}


	public LongProperty getRejestracjaId() {
		return rejestracjaId;
	}


	public void setRejestracjaId(LongProperty rejestracjaId) {
		this.rejestracjaId = rejestracjaId;
	}


	public StringProperty getDataOd() {
		return dataOd;
	}


	public void setDataOd(StringProperty dataOd) {
		this.dataOd = dataOd;
	}


	public StringProperty getDataDo() {
		return dataDo;
	}


	public void setDataDo(StringProperty dataDo) {
		this.dataDo = dataDo;
	}


	public LongProperty getCzyPrzyjety() {
		return czyPrzyjety;
	}


	public void setCzyPrzyjety(LongProperty czyPrzyjety) {
		this.czyPrzyjety = czyPrzyjety;
	}
	
	
}
