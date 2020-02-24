package compraproductos.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compraproductos.entity.Pedido;
import compraproductos.entity.Reporte;
import compraproductos.services.PedidoServices;

@RestController
@RequestMapping("api/pedido/")
@CrossOrigin
public class PedidoController {
	@Autowired
	private PedidoServices pedidoServices;
	
	@GetMapping("consultar")
	public ResponseEntity<List<Pedido>> consultarClientes(){
		return new ResponseEntity<>(pedidoServices.consultarPedidos(), HttpStatus.OK);
	}
	
	@GetMapping(value=  "consultarByidProducto" , produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> consultarPedidoProducto(@RequestBody int idProducto){
		List<Pedido> listPedidos =  pedidoServices.consultarPedidoProducto(idProducto);
		if(listPedidos.isEmpty()) {
			return new ResponseEntity<>(listPedidos,HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(listPedidos,HttpStatus.OK);
		}
	}
	
	@GetMapping( value = "consultarBycedulaCliente",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Pedido>> consultarPedidoCliente(@RequestBody String cedulaCliente){
		return new ResponseEntity<>(pedidoServices.consultarPedidoCliente(cedulaCliente), HttpStatus.OK);
	}
	
	@PostMapping(value = "crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> crearCliente(
			 @RequestBody  Pedido pedido) throws IOException {
		
			pedidoServices.crearPedido(pedido);
			return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> actualizarCliente(
			 @RequestBody  Pedido pedido) throws IOException {
		if(pedidoServices.consultarPedido(pedido.getIdPedido())!=null) {
				pedidoServices.actualizarPedido(pedido);
				return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	

	@DeleteMapping(value = "borrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pedido> borrarPedido(
			 @RequestBody  Pedido pedido) {
		
		Pedido pedi = pedidoServices.consultarPedido(pedido.getIdPedido());
		if (pedi != null) {
			pedidoServices.borrarPedido(pedi);
			return new ResponseEntity<>(pedi, HttpStatus.OK);
		}
		return new ResponseEntity<>(pedi, HttpStatus.NO_CONTENT);
	}
	
	@PostMapping( value = "consultarByFechas", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Reporte>> consultarPedidoClienteProducto(@RequestBody String[] dates){
		return new ResponseEntity<>(pedidoServices.consultarPedidosPorFecha(dates[0],dates[1]), HttpStatus.OK);
	}
}
