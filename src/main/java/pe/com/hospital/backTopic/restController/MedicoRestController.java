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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import pe.com.hospital.backTopic.model.Medico;
import pe.com.hospital.backTopic.service.MedicoService;

@Api(value = "Endpoints de Medico")
@RestController
@RequestMapping("/api/medicos")
public class MedicoRestController {
	@Autowired
	private MedicoService medicoService;

	@ApiOperation(value = "EndPoint que permite listar los medicos")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> Listar() {
		ResponseEntity<List<Medico>> response;
		try {
			List<Medico> medicos = medicoService.findAll();
			response = new ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un medico por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Medico> GetMedico(@PathVariable("id") int id) {
		try {
			Optional<Medico> medico = medicoService.findById(id);
			if (medico.isPresent()) {
				return new ResponseEntity<Medico>(medico.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Medico>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Medico>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite obtener medico o medicos por su nombre")
	@GetMapping(path = "/{nombre}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> getNombreMedico(@RequestParam(value = "nombre") String nombre){
		ResponseEntity<List<Medico>> response;
		try {
			List<Medico> medicos = medicoService.fetchByNombre(nombre);
			response = new  ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<List<Medico>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite obtener medico o medicos por su apellido paterno")
	@GetMapping(path = "/{apellidoPaterno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> getApellidoPMedico(@RequestParam(value = "apellidoPaterno") String apellidoPaterno){
		ResponseEntity<List<Medico>> response;
		try {
			List<Medico> medicos = medicoService.fetchByApellidoPaterno(apellidoPaterno);
			response = new  ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<List<Medico>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite obtener medico o medicos por su apellido materno")
	@GetMapping(path = "/{apellidoMaterno}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> getApellidoMMedico(@RequestParam(value = "apellidoMaterno") String apellidoMaterno){
		ResponseEntity<List<Medico>> response;
		try {
			List<Medico> medicos = medicoService.fetchByApellidoMaterno(apellidoMaterno);
			response = new  ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<List<Medico>>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite obtener medico o medicos NOMBRE COMPLETO")
	@GetMapping(path = "/fullName", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Medico>> getCompleto(@RequestParam(value = "apellidoMaterno") String apellidoMaterno, 
			@RequestParam(value = "apellido Paterno") String apellidoPaterno, @RequestParam(value = "nombre") String nombre){
		ResponseEntity<List<Medico>> response;
		try {
			List<Medico> medicos = medicoService.fetchByNombreCompleto(nombre, apellidoPaterno, apellidoMaterno);
			response = new  ResponseEntity<List<Medico>>(medicos, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			return new ResponseEntity<List<Medico>>(HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "EndPoint que permite grabar un medico")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Medico> nuevo(@RequestBody Medico medico) {
		try {
			Medico nuevoMedico = medicoService.save(medico);
			return new ResponseEntity<Medico>(nuevoMedico, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Medico>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una especialidad")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Medico> actualizar(@PathVariable("id") Integer id, @RequestBody Medico medico) {
		try {
			if (id.equals(medico.getId())) {
				Optional<Medico> mec = medicoService.findById(id);
				if (mec.isPresent()) {
					Medico medicoUpdate = medicoService.update(medico);
					return new ResponseEntity<Medico>(medicoUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Medico>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Medico>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Medico>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Medico> medico = medicoService.findById(id);
			if (medico.isPresent()) {
				medicoService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
