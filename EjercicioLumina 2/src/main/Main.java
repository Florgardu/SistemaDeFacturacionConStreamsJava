package main;

import java.util.ArrayList;

import entidades.Cliente;
import entidades.Pedido;
import entidades.Producto;
import sistemaFacturacion.SistemaFacturacion;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SistemaFacturacion sistema = SistemaFacturacion.getInstance();
		sistema.limpiarListas();

		// creo un clientes con diferentes categorias impositivas

		Cliente cliente1 = new Cliente("Acevedo 652", "A", 32828999);
		Cliente cliente2 = new Cliente("Acevedo 652", "B", 35805837);
		Cliente cliente3 = new Cliente( "Acevedo 652", "X", 92081299);
		Cliente cliente4 = new Cliente( "Acevedo 111", "A", 82093845);

		// creo productos

		Producto producto1 = new Producto(22, "Heladera", 55000);
		Producto producto2 = new Producto(23, "Lavarropas", 75000);
		Producto producto3 = new Producto(24, "Horno", 30000);
		Producto producto4 = new Producto(25, "Microondas", 20000);
		Producto producto5 = new Producto(26, "Televisor", 100000);

		// Un cliente genera un Pedido

		Pedido pedido1 = new Pedido(1, cliente1);
		Pedido pedido2 = new Pedido(2, cliente2);
		Pedido pedido3 = new Pedido(3, cliente3);
		Pedido pedido4 = new Pedido(4, cliente4);


		// Agrega productos a sus pedidos

		pedido1.agregarProductoAPedido(producto1, 2);
		pedido1.agregarProductoAPedido(producto2, 1);
		pedido1.agregarProductoAPedido(producto4, 1);

		pedido2.agregarProductoAPedido(producto4, 1);
		pedido2.agregarProductoAPedido(producto3, 2);

		pedido3.agregarProductoAPedido(producto1, 2);
		pedido3.agregarProductoAPedido(producto5, 1);
		
		pedido4.agregarProductoAPedido(producto1, 3);

		// Una vez finalizada la carga el sistema le indicará el total bruto del pedido

		System.out.println("El total Bruto del pedido 1 es $" + pedido1.totalBruto());
		System.out.println("El total Bruto del pedido 2 es $" + pedido2.totalBruto());
		System.out.println("El total Bruto del pedido 3 es $" + pedido3.totalBruto());
		System.out.println("El total Bruto del pedido 4 es $" + pedido4.totalBruto());


		// y pedirá confirmación para ingresarlo, una vez confirmado el pedido quedará
		// en estado pendiente hasta que el proceso nocturno de factura-ción lo facture

		// antes de confirmar el estado del pedido es null
		System.out.println(pedido1.getEstado());
		System.out.println(pedido2.getEstado());
		System.out.println(pedido3.getEstado());
		System.out.println(pedido4.getEstado());


		// pedir confirmación

		pedido1.confirmar();
		pedido2.confirmar();
		pedido3.confirmar();
		pedido4.confirmar();

		// cambia el estado a pendiente

		System.out.println(pedido1.getEstado());
		System.out.println(pedido2.getEstado());
		System.out.println(pedido3.getEstado());
		System.out.println(pedido4.getEstado());


		// 1. Se requiere proveer un módulo que realice las siguientes tareas:
		// a. Recibir una colección de pedidos, realizar facturación

		ArrayList<Pedido> nuevosPedidos = new ArrayList<Pedido>();
		nuevosPedidos.add(pedido1);
		nuevosPedidos.add(pedido2);
		nuevosPedidos.add(pedido3);
		nuevosPedidos.add(pedido4);



		//CREAR 4 PEDIDOS Y AGREGARLOS AL SISTEMA
		sistema.recibirPedidos(nuevosPedidos);
		

		// ANULAR EL PRIMER PEDIDO
		ArrayList<Pedido> pedidosParaCancelar = new ArrayList<Pedido>();
		pedidosParaCancelar.add(pedido1);
		sistema.cancelarPedidos(pedidosParaCancelar);
		pedidosParaCancelar.clear();
		System.out.println("facturar");
		// FACTURAR
		
		long startTime2 = System.nanoTime();
		sistema.realizarFacturacion();
		long endTime2 = System.nanoTime();
		System.out.println(endTime2 - startTime2);	
		
				
		// CANCELAR PEDIDO 3 
		pedidosParaCancelar.add(pedido3);
		sistema.cancelarPedidos(pedidosParaCancelar);


		//VER NOTAS DE CRÉDITO
		sistema.verNotaDeCredito();

		//VER FACTURAS 
		
		sistema.verFacturas();

		//VER PEDIDOS

		sistema.verPedidos();
		
		
		sistema.generarTxt();
			
		
	}

}
