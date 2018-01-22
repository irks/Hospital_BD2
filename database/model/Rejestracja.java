package model;

import java.sql.Date;
import java.time.LocalDate;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Rejestracja {
	private LongProperty numerRejestracji;
    private Date dataOd;
    private Date dataDo;
    private LongProperty czyPrzyjety;
    private LongProperty idOddzialu;
    private LongProperty idPacjenta;
    
    
	public Rejestracja(Date dataOd, Date dataDo, long czyPrzyjety, long numerRejestracji, long idOddzialu, long idPacjenta) {
		this.numerRejestracji = new SimpleLongProperty(numerRejestracji);
		this.dataOd = dataOd;//new SimpleStringProperty(dataOd); 
		this.dataDo = dataDo;//new SimpleStringProperty(dataDo);
		this.czyPrzyjety = new SimpleLongProperty(czyPrzyjety);
		this.idOddzialu = new SimpleLongProperty(idOddzialu);
		this.idPacjenta = new SimpleLongProperty(idPacjenta);
	}








	public Date getDataOd() {
		return dataOd;
	}








	public void setDataOd(Date dataOd) {
		this.dataOd = dataOd;
	}








	public Date getDataDo() {
		return dataDo;
	}








	public void setDataDo(Date dataDo) {
		this.dataDo = dataDo;
	}








	public LongProperty getCzyPrzyjety() {
		return czyPrzyjety;
	}


	public void setCzyPrzyjety(LongProperty czyPrzyjety) {
		this.czyPrzyjety = czyPrzyjety;
	}


	public LongProperty getNumerRejestracji() {
		return numerRejestracji;
	}


	public void setNumerRejestracji(LongProperty numerRejestracji) {
		this.numerRejestracji = numerRejestracji;
	}


	public LongProperty getIdOddzialu() {
		return idOddzialu;
	}


	public void setIdOddzialu(LongProperty idOddzialu) {
		this.idOddzialu = idOddzialu;
	}


	public LongProperty getIdPacjenta() {
		return idPacjenta;
	}


	public void setIdPacjenta(LongProperty idPacjenta) {
		this.idPacjenta = idPacjenta;
	}
	
	
}
