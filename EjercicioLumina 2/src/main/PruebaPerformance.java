package main;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import entidades.Cliente;
import entidades.Pedido;
import entidades.Producto;
import sistemaFacturacion.SistemaFacturacion;

public class PruebaPerformance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SistemaFacturacion sistema = SistemaFacturacion.getInstance();
		sistema.limpiarListas();

		// creo un clientes con diferentes categorias impositivas

		Cliente cliente1 = new Cliente("Acevedo 652", "A", 32828999);


		// creo productos

		Producto producto1 = new Producto(22, "Heladera", 55000);
		Producto producto2 = new Producto(23, "Lavarropas", 75000);
		Producto producto4 = new Producto(25, "Microondas", 20000);

		// Un cliente genera un Pedido
		ArrayList<Pedido> nuevosPedidos = new ArrayList<Pedido>();
		
		for (int i = 0; i < 1000; i++) {
			Pedido pedido1 = new Pedido(1, cliente1);
			pedido1.agregarProductoAPedido(producto1, 2);
			pedido1.agregarProductoAPedido(producto2, 1);
			pedido1.agregarProductoAPedido(producto4, 1);
			
			pedido1.confirmar();
			nuevosPedidos.add(pedido1);
		}

		//CREAR 4 PEDIDOS Y AGREGARLOS AL SISTEMA
		sistema.recibirPedidos(nuevosPedidos);
		


		System.out.println("facturar");
		// FACTURAR
		
		long startTime2 = System.nanoTime();
		// sistema.realizarFacturacionLenta();
		sistema.realizarFacturacion();
		System.out.println("Time: " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime()- startTime2));

			
		
	}

}
