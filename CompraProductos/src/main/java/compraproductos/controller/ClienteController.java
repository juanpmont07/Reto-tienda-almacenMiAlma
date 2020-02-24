package compraproductos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compraproductos.entity.Cliente;
import compraproductos.services.ClienteServices;

@RestController
@RequestMapping("api/cliente/")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteServices clienteServices;
	
	@GetMapping("consultar")
	public ResponseEntity<List<Cliente>> consultarClientes(){
		return new ResponseEntity<>(clienteServices.consultarClientes(), HttpStatus.OK);
	}
	@GetMapping("consultar/{idCliente}")
	public ResponseEntity<Cliente> consultarCliente(@PathVariable String idCliente){
		Cliente cliente=clienteServices.consultarCliente(idCliente);
		if(cliente==null) {
			return new ResponseEntity<>(cliente,HttpStatus.NO_CONTENT);
		}    
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}

	@PostMapping(value = "crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> crearCliente(@RequestBody  Cliente clie)throws IOException{
		
		if(clienteServices.consultarCliente(clie.getCedulaCliente())==null) {
				clienteServices.crearCliente(clie);
				return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> actualizarCliente(@RequestBody  Cliente clie) throws IOException {
		if(clienteServices.consultarCliente(clie.getCedulaCliente())!=null) {
				clienteServices.actualizarCliente(clie);
				return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping(value = "borrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cliente> borrarCliente(@RequestBody  Cliente cliente) {
		Cliente clie = clienteServices.consultarCliente(cliente.getCedulaCliente());
		if (clie != null) {
			clienteServices.borrarCliente(clie);
			return new ResponseEntity<>(clie, HttpStatus.OK);
		}
		return new ResponseEntity<>(clie, HttpStatus.NO_CONTENT);
	}
}
