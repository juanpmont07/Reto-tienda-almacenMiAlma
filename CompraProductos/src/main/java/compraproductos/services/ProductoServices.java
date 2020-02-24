package compraproductos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import compraproductos.dao.ProductoDao;
import compraproductos.entity.Producto;

@Service
public class ProductoServices {
	@Autowired
	private ProductoDao daoProducto;

	// Crear un producto
	public void crearProducto(Producto producto) {
		daoProducto.registrarProducto(producto.getNombreProducto(), producto.getDescripcionProducto(), producto.getImagen(),producto.getPrecio());
	}
	
	//Actualizar producto
	public void actualizarProducto(Producto producto) {
		daoProducto.editarproducto(producto.getIdProducto(), producto.getNombreProducto(), producto.getDescripcionProducto(), producto.getImagen(),producto.getPrecio());
	}

	// Consultar todos los productos
	public List<Producto> consultarProductos() {
		return (List<Producto>) daoProducto.findAll();
	}

	// Consultar un producto especifico
	public Producto consultarProducto(int idProducto) {
		Optional<Producto> cliente = daoProducto.findById(idProducto);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}

	// Eliminar un producto
	public void borrarProducto(Producto producto) {
		 daoProducto.eliminarproducto(producto.getIdProducto());
	}

}
