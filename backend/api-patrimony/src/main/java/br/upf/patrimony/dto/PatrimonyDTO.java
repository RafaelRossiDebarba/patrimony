package br.upf.patrimony.dto;

import java.io.Serializable;

import br.upf.patrimony.entities.Patrimony;

public class PatrimonyDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private Boolean status;
	private String description;
	private String code;
	private String imgUrl;
	
	public PatrimonyDTO() {
		
	}

	public PatrimonyDTO(long id, String name, Boolean status, String description, String code, String imgUrl) {
		super();
		this.id = id;
		this.name = name;
		this.status = status;
		this.description = description;
		this.code = code;
		this.imgUrl = imgUrl;
	}
	
	public PatrimonyDTO(Patrimony entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.status = entity.getStatus();
		this.description = entity.getDescription();
		this.code = entity.getCode();
		this.imgUrl = entity.getImgUrl();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String nome) {
		this.name = nome;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
