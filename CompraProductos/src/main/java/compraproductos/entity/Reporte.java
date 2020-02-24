package compraproductos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

@Table (name = "reporte")
public class Reporte {
	@Column(name="p.nombre")
	private String nombreProducto;
	
	@Column(name="p.imagen")
	private String imagen;
	
	@Column(name="c.cedula")
	private String cedula;
	
	@Column(name="c.nombre")
	private String nombreCliente;
	
	@Column(name="pe.fechaCompra")
	private Date fechaCompra;
	
	public String getNombreProducto() {
		return nombreProducto;
	}
	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public Date getFechaCompra() {
		return fechaCompra;
	}
	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
}
