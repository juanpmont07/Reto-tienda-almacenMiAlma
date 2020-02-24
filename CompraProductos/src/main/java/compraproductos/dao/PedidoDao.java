package compraproductos.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import compraproductos.entity.Pedido;

public interface PedidoDao extends CrudRepository<Pedido, Integer> {
	
	@Procedure("registrarPedido")
	void registrarPedido(@Param("idProducto") int idProducto, @Param("idCliente") String idCliente,
			@Param("fechaCompra") Date fechaCompra, @Param("detalle") String detalle);

	
	@Procedure("editarPedido")
	void editarPedido(@Param("id") int idPedido, @Param("idProducto") int idProducto, @Param("idCliente") String idCliente,
			@Param("fechaCompra") Date fechaCompra, @Param("detalle") String detalle);

	
	@Procedure("eliminarPedido")
	void eliminarPedido(@Param("id") int idPedido);

	
	@Procedure("consultarPedidoPorIdCliente")
	List<Pedido> consultarPedidoPorIdCliente(@Param("id") String idCliente);

	
	@Procedure("consultarPedidoPorIdProducto")
	List<Pedido> consultarPedidoPorIdProducto(@Param("id") int idProducto);

	
	
}
