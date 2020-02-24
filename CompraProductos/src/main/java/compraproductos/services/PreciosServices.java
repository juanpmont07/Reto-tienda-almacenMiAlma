package compraproductos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import compraproductos.dao.PreciosDao;
import compraproductos.entity.Precios;

@Service
public class PreciosServices {
	@Autowired
	private PreciosDao preciosDao;

	// Crear
	public Precios crearPrecio(Precios precios) {
		return preciosDao.save(precios);
	}

	// Consultar todos los precios
	public List<Precios> consultarPrecio() {
		return (List<Precios>) preciosDao.findAll();
	}

	// Consultar un precio especifico
	public Precios consultarPrecio(int idProducto) {
		Optional<Precios> producto = preciosDao.findById(idProducto);
		if (producto.isPresent()) {
			return producto.get();
		}
		return null;
	}

	// Consultar un producto con su precio
	public Precios consultarProductoConPrecio(int idProducto) {
		Optional<Precios> producto = preciosDao.consultaProductoConPrecio(idProducto);
		if (producto.isPresent()) {
			return producto.get();
		}
		return null;
	}

	// Eliminar
	public void borrarPrecios(Precios precios) {
		preciosDao.delete(precios);
	}
}
