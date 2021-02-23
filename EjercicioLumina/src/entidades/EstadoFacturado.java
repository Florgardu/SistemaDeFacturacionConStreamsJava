package entidades;

import sistemaFacturacion.SistemaFacturacion;

public class EstadoFacturado extends Estado {
	
	private Factura factura;
	
	
	public EstadoFacturado(Pedido pedido, Factura factura) {
		setPedido(pedido);
		this.factura= factura;
	}

	@Override
	public void cancelar() {
		// TODO Auto-generated method stub
		System.out.println("no se puede, generar nueva nota de credito");
		NotaDeCredito notaCredito = new NotaDeCredito(this.factura);
		EstadoFacturaAnulada anulada= new EstadoFacturaAnulada();
		getPedido().setEstado(anulada);  //cambio a estado factura anulada
		SistemaFacturacion s=	SistemaFacturacion.getInstance();
		s.agregarNotaDeCredito(notaCredito);
	}

	

	
	
}
