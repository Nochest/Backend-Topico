package pe.com.hospital.backTopic.restController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.com.hospital.backTopic.model.Ubicacion;
import pe.com.hospital.backTopic.service.UbicacionService;

@Api(value = "Endpoints de Ubicaciones")
@RestController
@RequestMapping("/api/ubicaciones")
public class UbicacionRestController {
	@Autowired
	private UbicacionService ubicacionService;

	@ApiOperation(value = "EndPoint que permite listar las ubicaciones")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Ubicacion>> Listar() {
		ResponseEntity<List<Ubicacion>> response;
		try {
			List<Ubicacion> ubicaciones = ubicacionService.findAll();
			response = new ResponseEntity<List<Ubicacion>>(ubicaciones, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener una ubicacion por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ubicacion> GetUbicacion(@PathVariable("id") int id) {
		try {
			Optional<Ubicacion> ubicacion = ubicacionService.findById(id);
			if (ubicacion.isPresent()) {
				return new ResponseEntity<Ubicacion>(ubicacion.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Ubicacion>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Ubicacion>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar una ubicacion")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ubicacion> nuevo(@RequestBody Ubicacion ubicacion) {
		try {
			Ubicacion nuevaUbicacion = ubicacionService.save(ubicacion);
			return new ResponseEntity<Ubicacion>(nuevaUbicacion, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Ubicacion>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una especialidad")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Ubicacion> actualizar(@PathVariable("id") Integer id, @RequestBody Ubicacion ubicacion) {
		try {
			if (id.equals(ubicacion.getId())) {
				Optional<Ubicacion> ubi = ubicacionService.findById(id);
				if (ubi.isPresent()) {
					Ubicacion ubicacionUpdate = ubicacionService.update(ubicacion);
					return new ResponseEntity<Ubicacion>(ubicacionUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Ubicacion>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Ubicacion>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Ubicacion>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Ubicacion> ubicacion = ubicacionService.findById(id);
			if (ubicacion.isPresent()) {
				ubicacionService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
