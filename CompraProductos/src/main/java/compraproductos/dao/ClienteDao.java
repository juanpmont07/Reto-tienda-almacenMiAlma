package compraproductos.dao;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import compraproductos.entity.Cliente;

public interface ClienteDao extends CrudRepository<Cliente, String>{

	@Procedure(name="registrarCliente")
	void registrarCliente(@Param("cedula") String cedula,@Param("nombre") String nombre);
	 
	@Procedure(name="editarCliente")
	void editarCliente(@Param("cedula") String cedula,@Param("nombre") String nombre);
	 
	@Procedure(name="eliminarcliente")
	void eliminarcliente(@Param("cedula") String cedula);

}
