package br.upf.patrimony.entities;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Audit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "date_register", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateRegister;
	
	@Column(name = "date_last_edition", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
	private Instant dateLastEdition;
	
	public Audit() {
		
	}

	public Instant getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Instant dateRegister) {
		this.dateRegister = dateRegister;
	}

	public Instant getDateLastEdition() {
		return dateLastEdition;
	}

	public void setDateLastEdition(Instant dateLastEdition) {
		this.dateLastEdition = dateLastEdition;
	}
	
	
	
}
