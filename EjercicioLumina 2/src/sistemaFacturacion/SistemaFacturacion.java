package sistemaFacturacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import entidades.Cliente;
import entidades.EstadoFacturado;
import entidades.Factura;
import entidades.NotaDeCredito;
import entidades.Pedido;

public class SistemaFacturacion {

	private static SistemaFacturacion instance;

	private List<Factura> facturas = new ArrayList<Factura>();
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<NotaDeCredito> notasDeCreditos = new ArrayList<NotaDeCredito>();

	private SistemaFacturacion() {
	}

	public static SistemaFacturacion getInstance() {
		if (instance == null) {
			instance = new SistemaFacturacion();
		}
		return instance;
	}

	public void agregarNotaDeCredito(NotaDeCredito nota) {
		this.notasDeCreditos.add(nota);
	}

	public void recibirPedidos(ArrayList<Pedido> pedidosNuevos) {
		this.pedidos.addAll(pedidosNuevos);
	}

	// a. Recibir una colección de pedidos, realizar facturación
	// El proceso de facturación obtendrá la categoría de IVA del cliente
	// del pedido para poder calcular el precio final de la factura.
	public void realizarFacturacionLenta() {
		List<Pedido> pedidosAFacturar = obtenerPedidosPendientes();

		for (Pedido pedido : pedidosAFacturar) {
			this.facturas.add(pedido.generarFactura());
		}

	}
	
	public void realizarFacturacion() {
		List<Pedido> pedidosAFacturar = obtenerPedidosPendientes();
		this.facturas.addAll(pedidosAFacturar
				.parallelStream()
				//.peek(x -> System.out.println("processing "+x+" in "+Thread.currentThread()))
				.map(p->  p.generarFactura())
				.collect(Collectors.toList()));

	}	

	public void generarFactura(Pedido pedido) {
		int nroFactura = (int)(Math.random()*10+1);
		int codEmision = (int)(Math.random()*10+1);
		Cliente cliente = pedido.getCliente();
		Factura factura = new Factura(nroFactura, codEmision, cliente, pedido);
		EstadoFacturado facturado = new EstadoFacturado(pedido, factura);
		pedido.setEstado(facturado);
		this.facturas.add(factura);
	}

	private List<NotaDeCredito> obtenerNotasDelDia() {
		return notasDeCreditos.stream()
				.filter(notasDeCreditos -> notasDeCreditos.getFechaEmision().equals(LocalDate.now()))
				.collect(Collectors.toList());

	}

	private List<Factura> obtenerFacturasDelDia() {
		return facturas.stream().filter(facturas -> facturas.getFechaEmision().equals(LocalDate.now()))
				.collect(Collectors.toList());

	}

	private List<Pedido> obtenerPedidosPendientes() {
		return pedidos.stream().filter(pedido -> pedido.isPendiente()).collect(Collectors.toList());
	}

	public void eliminarPedido(Pedido pedido) {

		this.pedidos.remove(pedido);
	}

	// b. Recibir una lista de facturas a anular y Realizar la cancelación de
	// pedidos

	public void cancelarPedidos(ArrayList<Pedido> pedidosACancelar) {
		for (Pedido pedido : pedidosACancelar) {
			pedido.cancelar();

		}
	}

	public void verFacturas() {
		for (Factura factura : facturas) {
			factura.mostrarFactura();
		}
	}

	public void verPedidos() {
		for (Pedido pedido : pedidos) {
			System.out.println(pedido.getNroPedido());
		}
	}

	public void verNotaDeCredito() {
		for (NotaDeCredito nota : notasDeCreditos) {
			System.out.println("Hay una nota de credito de cliente numero : " + nota.getCliente().getNroDeCliente());
		}
	}

	public void limpiarListas() {
		this.facturas.clear();
		this.notasDeCreditos.clear();
		this.pedidos.clear();
	}

//c.	Proveer un método que Genere el txt con la operatoria del día con el siguiente formato:
//•	Cliente-Tipo de Documento-Letra-Nro-Fecha de emisión-Monto

	public void generarTxt() {
		List<Factura> facturasDelDia = this.obtenerFacturasDelDia();
		List<NotaDeCredito> notasDelDia = this.obtenerNotasDelDia();

	//	Cliente-Tipo de Documento-Letra-Nro-Fecha de emisión-Monto
		
		FileWriter flwriter = null;
		try {
			// crea el flujo para escribir en el archivo
			flwriter = new FileWriter("./operatoriaDelDia.txt");
			// crea un buffer o flujo intermedio antes de escribir directamente en el
			// archivo
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
			bfwriter.write("NroCliente-Tipo de Documento-Letra-Fecha de emisión-Monto" + "\n");
					for (Factura factura : facturasDelDia) {
				// escribe los datos en el archivo
				bfwriter.write(
						factura.getnroCliente() + " " + factura.getCliente().getNroDocumento() + " " + factura.getCondicionImpositivaDeCliente() + " " +  
						factura.getFechaEmision() + " " +  factura.getTotal() + "\n");
			}
					
					for (NotaDeCredito nota : notasDelDia) {
						// escribe los datos en el archivo
						bfwriter.write( 
								nota.getNroCliente() + " " + nota.getCliente().getNroDocumento() + " " + nota.getCondicionImpositivaCliente() + " " +
								nota.getFechaEmision() + " " +  nota.getTotal() + "\n");
					}	
			// cierra el buffer intermedio
			bfwriter.close();
			System.out.println("Archivo creado satisfactoriamente..");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {// cierra el flujo principal
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
