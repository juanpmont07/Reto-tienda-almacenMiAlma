package compraproductos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "pedido")
public class Pedido {
	
	@Id
	@Column(name="id")
	private int idPedido;
	
	@Column(name="idCliente")
	private String cedulaCliente;
	
	@Column(name="idProducto")
	private int idProducto;
	
	@Column(name="fechaCompra")
	private Date fechaCompraPedido;
	
	@Column(name="detalle")
	private String detallePedido;
		
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public String getCedulaCliente() {
		return cedulaCliente;
	}
	public void setCedulaCliente(String cedulaCliente) {
		this.cedulaCliente = cedulaCliente;
	}
	public int getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}
	public Date getFechaCompraPedido() {
		return fechaCompraPedido;
	}
	public void setFechaCompraPedido(Date fechaCompraPedido) {
		this.fechaCompraPedido = fechaCompraPedido;
	}
	public String getDetallePedido() {
		return detallePedido;
	}
	public void setDetallePedido(String detallePedido) {
		this.detallePedido = detallePedido;
	} 
}
