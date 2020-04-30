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
import pe.com.hospital.backTopic.model.Cita;
import pe.com.hospital.backTopic.service.CitaService;


@Api(value = "Endpoints de Cita")
@RestController
@RequestMapping("api/citas")
public class CitaRestController {
	@Autowired
	private CitaService citaService;
	
	@ApiOperation(value = "EndPoint que permite listar las citas")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cita>> Listar() {
		ResponseEntity<List<Cita>> response;
		try {
			List<Cita> citas = citaService.findAll();
			response = new ResponseEntity<List<Cita>>(citas, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener una cita por su id")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cita> GetCita(@PathVariable("id") int id) {
		try {
			Optional<Cita> cita = citaService.findById(id);
			if (cita.isPresent()) {
				return new ResponseEntity<Cita>(cita.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Cita>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Cita>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar una especialidad")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cita> nuevo(@RequestBody Cita cita) {
		try {
			Cita nuevaCita = citaService.save(cita);
			return new ResponseEntity<Cita>(nuevaCita, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Cita>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar una cita")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cita> actualizar(@PathVariable("id") Integer id, @RequestBody Cita cita) {
		try {
			if (id.equals(cita.getId())) {
				Optional<Cita> cit = citaService.findById(id);
				if (cit.isPresent()) {
					Cita citaUpdate = citaService.update(cita);
					return new ResponseEntity<Cita>(citaUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Cita>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Cita>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Cita>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Cita> cita = citaService.findById(id);
			if (cita.isPresent()) {
				citaService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@ApiOperation(value = "EndPoint que permite obtener citas disponibles")
	@GetMapping(path = "/{reserva}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cita>> getCitasDisponibles(){
		ResponseEntity<List<Cita>> response;
		try {
			List<Cita> citas = citaService.fetchByCitasDisponibles();
			response = new ResponseEntity<List<Cita>>(citas, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	/*
	@ApiOperation(value = "EndPoint que permite obtener citas disponibles por parametros")
	@GetMapping(path = "/CITAWITHPARAMS", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cita>> getCitaWithParams(
			@RequestParam(value = "nombreSeguro") String nombreSeguro, 
			@RequestParam(value = "nombreEspecialidad") String nombreEspecialidad,
			@RequestParam(value = "nombreLugar") String nombreLugar,
			@RequestParam(value = "hora") Date hora){
		ResponseEntity<List<Cita>> response;
		try {  
			if(ubicacionService.getClinica().getNombre() == nombreLugar &&
				citaP.getFecha() == hora && 
				especialidad.getNombre() == nombreEspecialidad && 
				seguro.getNombre() == nombreSeguro) {
				List<Cita> citasHabiles = citaService.findByLugarSeguroEspecialidadHora(nombreSeguro, nombreEspecialidad, nombreLugar, hora);
				response = new ResponseEntity<List<Cita>>(citasHabiles, HttpStatus.OK);
				return response;
			}
			else {
				return new ResponseEntity<List<Cita>>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}*/
}
