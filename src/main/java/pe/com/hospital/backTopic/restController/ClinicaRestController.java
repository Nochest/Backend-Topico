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
import pe.com.hospital.backTopic.model.Clinica;
import pe.com.hospital.backTopic.service.ClinicaService;

@Api(value = "Endpoints de Clinica")
@RestController
@RequestMapping("/api/clinicas")
public class ClinicaRestController {
	@Autowired
	private ClinicaService clinicaService;

	@ApiOperation(value = "Endpoint que permite listar las clinicas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Clinica>> Listar() {
		ResponseEntity<List<Clinica>> response;
		try {
			List<Clinica> clinicas = clinicaService.findAll();
			response = new ResponseEntity<List<Clinica>>(clinicas, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener una clinica por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clinica> getClinica(@PathVariable("id") int id) {
		try {
			Optional<Clinica> clinica = clinicaService.findById(id);
			if (clinica.isPresent()) {
				return new ResponseEntity<Clinica>(clinica.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Clinica>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Clinica>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar una clinica")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clinica> nuevo(@RequestBody Clinica clinica) {
		try {
			Clinica nuevaClinica = clinicaService.save(clinica);
			return new ResponseEntity<Clinica>(nuevaClinica, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Clinica>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una clinica")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Clinica> actualizar(@PathVariable("id") Integer id, @RequestBody Clinica clinica) {
		try {
			if (id.equals(clinica.getId())) {
				Optional<Clinica> cli = clinicaService.findById(id);
				if (cli.isPresent()) {
					Clinica clinicaUpdate = clinicaService.update(clinica);
					return new ResponseEntity<Clinica>(clinicaUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Clinica>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Clinica>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Clinica>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Clinica> clinica = clinicaService.findById(id);
			if (clinica.isPresent()) {
				clinicaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);

			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
