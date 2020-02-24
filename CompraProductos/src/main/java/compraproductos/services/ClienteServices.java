package compraproductos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import compraproductos.dao.ClienteDao;
import compraproductos.entity.Cliente;

@Service
public class ClienteServices {

	@Autowired
	private ClienteDao daoCliente;

	// Crear
	public void crearCliente(Cliente cliente) {
		 daoCliente.registrarCliente(cliente.getCedulaCliente(), cliente.getNombreCliente());
	}

	// actualizar
	public void actualizarCliente(Cliente cliente) {
		 daoCliente.editarCliente(cliente.getCedulaCliente(), cliente.getNombreCliente());
	}

	// Consultar todos los clientes
	public List<Cliente> consultarClientes() {
		return (List<Cliente>) daoCliente.findAll();
	}

	// Consultar un cliente especifico
	public Cliente consultarCliente(String idCliente) {
		Optional<Cliente> cliente = daoCliente.findById(idCliente);
		if (cliente.isPresent()) {
			return cliente.get();
		}
		return null;
	}

	// Eliminar
	public void borrarCliente(Cliente cliente) {
		daoCliente.eliminarcliente(cliente.getCedulaCliente());
	}
}
