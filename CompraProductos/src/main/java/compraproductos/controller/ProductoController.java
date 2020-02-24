package compraproductos.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import compraproductos.entity.Producto;
import compraproductos.services.PreciosServices;
import compraproductos.services.ProductoServices;

@RestController
@RequestMapping("api/producto/")
@CrossOrigin
public class ProductoController {
	@Autowired
	private ProductoServices productoServices;
	@Autowired
	private PreciosServices preSer;
	
	@GetMapping("consultar")
	public ResponseEntity<List<Producto>> consultarProductos(){
		return new ResponseEntity<>(productoServices.consultarProductos(), HttpStatus.OK);
	}
	@GetMapping("consultar/{idProducto}")
	public ResponseEntity<Producto> consultarProducto(@PathVariable int idProducto){
		Producto cliente=productoServices.consultarProducto(idProducto);
		if(cliente==null) {
			return new ResponseEntity<>(cliente,HttpStatus.NO_CONTENT);
		}    
		return new ResponseEntity<>(cliente,HttpStatus.OK);
	}

	@PostMapping(value = "crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> crearProducto(@RequestBody  Producto producto) throws IOException {
		 if(producto.getNombreProducto() != "") {
			 
			 producto.setImagen(producto.getImagen()==null?"":producto.getImagen());
				productoServices.crearProducto(producto);
			
				return new ResponseEntity<>(HttpStatus.CREATED);
		 }else {
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		 }

	}
	
	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> actualizarProducto(@RequestBody  Producto producto) throws IOException {
		if(productoServices.consultarProducto(producto.getIdProducto())!=null) {
				productoServices.actualizarProducto(producto);
				return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@PostMapping(value = "borrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Producto> borrarProducto(@RequestBody  Producto producto) {
	
		Producto produ = productoServices.consultarProducto(producto.getIdProducto());
		if (produ != null) {
			System.out.println(producto.getIdProducto()+"entro");
			productoServices.borrarProducto(produ);
			return new ResponseEntity<>(produ, HttpStatus.OK);
		}
		return new ResponseEntity<>(produ, HttpStatus.NO_CONTENT);
	}
}
