package entidades;

import java.time.LocalDate;

public class Factura {

	private LocalDate fechaEmision;
	private int	nroFactura;
	private int codigoEmision;
	private Cliente cliente;
	private String letra; 
	private Producto producto;
	private double total;
	private Pedido pedido;
	

	public Factura(int nroFactura, int codigoEmision, Cliente cliente, Pedido pedido) {
		this.fechaEmision = LocalDate.now();
		this.nroFactura = nroFactura;
		this.codigoEmision = codigoEmision;
		this.cliente = cliente;
		this.pedido=pedido;
	}


	public LocalDate getFechaEmision() {
		return fechaEmision;
	}


	public int getNroFactura() {
		return nroFactura;
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

	
	public int getnroCliente () {
		return cliente.getNroDeCliente();
	}
	
	public String getCondicionImpositivaDeCliente() {
		return cliente.getCondicionImpositiva();
	}
	
	
	public Producto getProducto() {
		return producto;
	}


	public double getTotal() {
		return this.calcularTotalFactura();
	}


	public void setFechaEmision(LocalDate fechaEmision) {
		this.fechaEmision = fechaEmision;
	}


	public void setNroFactura(int nroFactura) {
		this.nroFactura = nroFactura;
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


	public void setProducto(Producto producto) {
		this.producto = producto;
	}


	
	private double calcularTotalFactura() {
		
		double precioNeto=this.pedido.totalBruto();
		double montoIva=0;
		String letra= this.pedido.getCliente().getCondicionImpositiva();
		
		
		switch (letra) {
		case "A":
			montoIva=10.05;
			break;
		case "B":
			montoIva=21;
			break;
		case "X":
			montoIva=70;
			break;
		default:
			break;
		}
		
		double precioDeVenta= precioNeto+ (precioNeto*(montoIva)/100);
		return precioDeVenta;
	}
	


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	


	
	private double montoIva(String letra) {
		double porcentajeIva=0;
		
		switch (letra) {
		case "A":
			porcentajeIva=10.05;
			break;
		case "B":
			porcentajeIva=21;
			break;
		case "X":
			porcentajeIva=70;
			break;
		default:
			break;
		}
		return porcentajeIva;
		
	}
	
	
	public void mostrarFactura() {
		System.out.println("============ CABECERA================================");
		System.out.println("Fecha de emisión: " + this.fechaEmision);
		System.out.println("NroFactura : " + this.nroFactura);
		System.out.println("Código De Emision : " + this.codigoEmision);
		System.out.println("Letra :"  + this.cliente.getCondicionImpositiva());
		System.out.println("Cliente NRO:"  + this.cliente.getNroDeCliente());

		System.out.println("============ DETALLE================================");
		for (Item item : this.pedido.getItemsDelPedido()) {
			System.out.println("============ ITEM ================================");
			System.out.println("Producto  :" + item.getProducto().getNombre());
			System.out.println("Precio : " + item.getProducto().getPrecio());
			double porcentajeIva= this.montoIva(this.cliente.getCondicionImpositiva());
			System.out.println("Iva : " + porcentajeIva );
			System.out.println("Cantidad :" +item.getCantidad());
			double precioNeto= item.getProducto().getPrecio()*item.getCantidad();
			//precio venta= Precio Neto + Iva
			// precio neto= Precio * Cantidad
			System.out.println("Precio De Venta : " + (precioNeto + (precioNeto*porcentajeIva)/100));
			System.out.println("Precio Neto :" + precioNeto);
			double montoIva=(precioNeto*porcentajeIva)/100;
			System.out.println("Monto de IVA : " + montoIva);
			
		}
		System.out.println("============PIE DE FACTURA================================");
		System.out.println("TOTAL : " + this.getTotal());
		
	}
	
	


	
	
}
