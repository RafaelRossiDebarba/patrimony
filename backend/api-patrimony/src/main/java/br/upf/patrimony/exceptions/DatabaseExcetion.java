package br.upf.patrimony.exceptions;

public class DatabaseExcetion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseExcetion(String message) {
		super(message);
	}
}
