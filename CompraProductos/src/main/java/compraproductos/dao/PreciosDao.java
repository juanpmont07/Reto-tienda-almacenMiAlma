package compraproductos.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import compraproductos.entity.Precios;

public interface PreciosDao extends CrudRepository<Precios, Integer> {

	@Procedure(name = "consultaProductoConPrecio")
	Optional<Precios> consultaProductoConPrecio(@Param("idProducto") int idProducto);
}
