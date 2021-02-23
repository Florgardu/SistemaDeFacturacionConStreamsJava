package entidades;

import java.util.Random;

public class Cliente {
	

	private int nroDeCliente;
	private String domicilio;
	private String condicionImpositiva;  // letra
	private int nroDocumento;
	
	
	public Cliente(String domicilio, String condicionImpositiva, int nroDocumento) {
		this.nroDeCliente = (int)(Math.random()*10+1);
		this.domicilio = domicilio;
		this.condicionImpositiva = condicionImpositiva;
		this.nroDocumento = nroDocumento;
	}


	public int getNroDeCliente() {
		return nroDeCliente;
	}


	public String getDomicilio() {
		return domicilio;
	}


	public String getCondicionImpositiva() {
		return condicionImpositiva;
	}


	public int getNroDocumento() {
		return nroDocumento;
	}


	public void setNroDeCliente(int nroDeCliente) {
		this.nroDeCliente = nroDeCliente;
	}


	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}


	public void setCondicionImpositiva(String condicionImpositiva) {
		this.condicionImpositiva = condicionImpositiva;
	}


	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	
	


}
