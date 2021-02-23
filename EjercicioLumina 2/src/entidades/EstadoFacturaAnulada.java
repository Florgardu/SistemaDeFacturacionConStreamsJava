package entidades;

public class EstadoFacturaAnulada extends Estado {


	@Override
	public void cancelar() {
		// TODO Auto-generated method stub
		System.out.println("Esta factura ya fue anulada");
	}

}
