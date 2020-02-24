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

import compraproductos.entity.Precios;
import compraproductos.services.PreciosServices;

@RestController
@RequestMapping("api/precios/")
@CrossOrigin
public class PreciosController {
	@Autowired
	private PreciosServices preciosServices;
	
	@GetMapping("consultar/precios")
	public ResponseEntity<List<Precios>> consultarPrecios(){
		return new ResponseEntity<>(preciosServices.consultarPrecio(), HttpStatus.OK);
	}
	@GetMapping("consultar/precios{idProducto}")
	public ResponseEntity<Precios> consultarPrecios(@PathVariable int idProducto){
		Precios precios=preciosServices.consultarPrecio(idProducto);
		if(precios==null) {
			return new ResponseEntity<>(precios,HttpStatus.NO_CONTENT);
		}    
		return new ResponseEntity<>(precios,HttpStatus.OK);
	}

	@PostMapping(value = "crear", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> crearPrecios(@RequestBody  Precios precio) throws IOException {
		if(preciosServices.consultarPrecio(precio.getIdProducto())==null) {
				preciosServices.crearPrecio(precio);
				return new ResponseEntity<>(HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
	}
	
	@PutMapping(value = "actualizar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> actualizarPrecio(@RequestBody  Precios precio) throws IOException {
		if(preciosServices.consultarPrecio(precio.getIdProducto())!=null) {
				preciosServices.crearPrecio(precio);
				return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	@DeleteMapping(value = "borrar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Precios> borrarPrecios(@RequestBody  Precios precio) {
		Precios preci = preciosServices.crearPrecio(precio);
		if (preci != null) {
			preciosServices.borrarPrecios(preci);
			return new ResponseEntity<>(preci, HttpStatus.OK);
		}
		return new ResponseEntity<>(preci, HttpStatus.NO_CONTENT);
	}
}
