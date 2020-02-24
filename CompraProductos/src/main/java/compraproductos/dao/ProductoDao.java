package compraproductos.dao;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import compraproductos.entity.Producto;

public interface ProductoDao extends CrudRepository<Producto, Integer> {

	@Procedure("registrarProducto")
	void registrarProducto(@Param("nombre") String nombre, @Param("descripcion") String descripcion,
			@Param("imagen") String imagen, @Param("precio") double precio);

	@Procedure("editarproducto")
	void editarproducto(@Param("id") int id, @Param("nombre") String nombre, @Param("descripcion") String descripcion,
			@Param("imagen") String imagen, @Param("precio") double precio);

	@Procedure("eliminarproducto")
	void eliminarproducto(@Param("id") int id);

}
