package entidades;


public abstract class Estado {
	

	private Pedido pedido;
	

	public abstract void cancelar();


	public Pedido getPedido() {
		return pedido;
	}


	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}


	
	

}
