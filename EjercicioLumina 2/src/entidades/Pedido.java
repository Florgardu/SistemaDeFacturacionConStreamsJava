package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;



public class Pedido {
	
	private int nroPedido;
	private Cliente cliente;
	private ArrayList<Item> itemsDelPedido;
	private Estado estado;
	
	public Pedido(int nroPedido, Cliente cliente) {
		this.nroPedido = nroPedido;
		this.cliente = cliente;
		this.itemsDelPedido = new ArrayList();
	}
	
	public Factura generarFactura() {
//		try {
//			Thread.sleep(10);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		int nroFactura = (int)(Math.random()*10+1);
		int codEmision = (int)(Math.random()*10+1);
		Cliente cliente = this.getCliente();
		Factura factura = new Factura(nroFactura, codEmision, cliente, this);
		EstadoFacturado facturado = new EstadoFacturado(this, factura);
		this.setEstado(facturado);
		return factura;
	}



	public int getNroPedido() {
		return nroPedido;
	}

	public void agregarProductoAPedido(Producto producto, int cantidad ) {
		Item item = new Item(producto, cantidad);
		this.itemsDelPedido.add(item);
	}

	public Cliente getCliente() {
		return cliente;
	}


	public void setNroPedido(int nroPedido) {
		this.nroPedido = nroPedido;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public void setProductosDelPedido(ArrayList<Item> itemsDelPedido) {
		this.itemsDelPedido = itemsDelPedido;
	}


	public ArrayList<Item> getItemsDelPedido() {
		return itemsDelPedido;
	}


	public Estado getEstado() {
		return estado;
	}


	public void setItemsDelPedido(ArrayList<Item> itemsDelPedido) {
		this.itemsDelPedido = itemsDelPedido;
	}


	public void setEstado(Estado estado) {
		this.estado = estado;
		this.estado.setPedido(this);
	}
	
	
	public double totalBruto() {
		double totalBruto=0;
		
		for (Item item : itemsDelPedido) {
		totalBruto=  totalBruto + item.calcularTotal();
		}
		
		return totalBruto;
	}
	
	
	public void confirmar() {
		EstadoPendiente estadoPendiente = new EstadoPendiente();
		this.setEstado(estadoPendiente);
	}
	
	
	public void cancelar() {
		this.estado.cancelar();
	}


	public boolean isPendiente() {
		return this.estado instanceof EstadoPendiente;
	}

}
