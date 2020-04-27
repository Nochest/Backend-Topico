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
import pe.com.hospital.backTopic.model.Especialidad;
import pe.com.hospital.backTopic.service.EspecialidadService;

@Api(value = "EndPoints de Especialidad")
@RestController
@RequestMapping("/api/especialidades")
public class EspecialidadRestController {
	@Autowired
	private EspecialidadService especialidadService;

	@ApiOperation(value = "Lista las especialidades")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Especialidad>> Listar() {
		ResponseEntity<List<Especialidad>> response;
		try {
			List<Especialidad> especialidades = especialidadService.findAll();
			response = new ResponseEntity<List<Especialidad>>(especialidades, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener una especialidad por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Especialidad> GetEspecialidad(@PathVariable("id") int id) {
		try {
			Optional<Especialidad> especialidad = especialidadService.findById(id);
			if (especialidad.isPresent()) {
				return new ResponseEntity<Especialidad>(especialidad.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Especialidad>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar una especialidad")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Especialidad> nuevo(@RequestBody Especialidad especialidad) {
		try {
			Especialidad nuevaEspecialidad = especialidadService.save(especialidad);
			return new ResponseEntity<Especialidad>(nuevaEspecialidad, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una especialidad")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Especialidad> actualizar(@PathVariable("id") Integer id,
			@RequestBody Especialidad especialidad) {
		try {
			if (id.equals(especialidad.getId())) {
				Optional<Especialidad> esp = especialidadService.findById(id);
				if (esp.isPresent()) {
					Especialidad especialidadUpdate = especialidadService.update(especialidad);
					return new ResponseEntity<Especialidad>(especialidadUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Especialidad>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Especialidad>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Especialidad>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Especialidad> especialidad = especialidadService.findById(id);
			if (especialidad.isPresent()) {
				especialidadService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
