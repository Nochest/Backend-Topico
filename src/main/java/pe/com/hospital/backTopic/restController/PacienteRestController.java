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
import pe.com.hospital.backTopic.model.Paciente;
import pe.com.hospital.backTopic.service.PacienteService;

@Api(value = "Endpoints de Paciente")
@RestController
@RequestMapping("/api/pacientes")
public class PacienteRestController {
	@Autowired
	private PacienteService pacienteService;

	@ApiOperation(value = "EndPoint que permite listar los pacientes")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Paciente>> Listar() {
		ResponseEntity<List<Paciente>> response;
		try {
			List<Paciente> pacientes = pacienteService.findAll();
			response = new ResponseEntity<List<Paciente>>(pacientes, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un paciente por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> GetPaciente(@PathVariable("id") int id) {
		try {
			Optional<Paciente> paciente = pacienteService.findById(id);
			if (paciente.isPresent()) {
				return new ResponseEntity<Paciente>(paciente.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar un paciente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> nuevo(@RequestBody Paciente paciente) {
		try {
			Paciente nuevoPaciente = pacienteService.save(paciente);
			return new ResponseEntity<Paciente>(nuevoPaciente, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar un paciente")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> actualizar(@PathVariable("id") Integer id, @RequestBody Paciente paciente) {
		try {
			if (id.equals(paciente.getId())) {
				Optional<Paciente> pac = pacienteService.findById(id);
				if (pac.isPresent()) {
					Paciente pacienteUpdate = pacienteService.update(paciente);
					return new ResponseEntity<Paciente>(pacienteUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Paciente>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Paciente> paciente = pacienteService.findById(id);
			if (paciente.isPresent()) {
				pacienteService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
