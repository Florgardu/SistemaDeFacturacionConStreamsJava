package entidades;

import sistemaFacturacion.SistemaFacturacion;

public class EstadoPendiente extends Estado {
	

	@Override
	public void cancelar() {
		// TODO Auto-generated method stub
		System.out.println("cancelado");
		// eliminar el pedido
		SistemaFacturacion s = SistemaFacturacion.getInstance();
		s.eliminarPedido(getPedido());
	}

}
