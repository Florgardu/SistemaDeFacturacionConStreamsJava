package entidades;

import java.time.LocalDate;
import java.util.Random;

public class NotaDeCredito {
	
	//Cabecera
	private LocalDate fechaEmision;
	private int nroNotaCredito;	
	private int codigoEmision;
	private String letra;
	private Cliente cliente;
	private double total;
	
	public NotaDeCredito(Factura facturaACancelar) {
		Random r = new Random();
		this.fechaEmision= LocalDate.now();
		this.nroNotaCredito=r.nextInt(1);;
		this.codigoEmision=r.nextInt(2);;
		this.cliente= facturaACancelar.getCliente();
		this.letra= cliente.getCondicionImpositiva();
		 this.total= facturaACancelar.getTotal();
	}

	public LocalDate getFechaEmision() {
		return fechaEmision;
	}

	public int getNroNotaCredito() {
		return nroNotaCredito;
	}

	public int getCodigoEmision() {
		return codigoEmision;
	}

	public String getLetra() {
		return letra;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	
	public int getNroCliente() {
		return cliente.getNroDeCliente();
	}
	
	public String getCondicionImpositivaCliente() {
		return cliente.getCondicionImpositiva();
	}

	public double getTotal() {
		return total;
	}

	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public void setNroNotaCredito(int nroNotaCredito) {
		this.nroNotaCredito = nroNotaCredito;
	}

	public void setCodigoEmision(int codigoEmision) {
		this.codigoEmision = codigoEmision;
	}

	public void setLetra(String letra) {
		this.letra = letra;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
	
}
