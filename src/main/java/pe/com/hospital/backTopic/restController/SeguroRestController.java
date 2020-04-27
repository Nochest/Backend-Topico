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
import pe.com.hospital.backTopic.model.Seguro;
import pe.com.hospital.backTopic.service.SeguroService;

@Api(value = "Endpoints de Seguro")
@RestController
@RequestMapping("/api/seguros")
public class SeguroRestController {
	@Autowired
	private SeguroService seguroService;

	@ApiOperation(value = "EndPoint que permite listar los seguros")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Seguro>> Listar() {
		ResponseEntity<List<Seguro>> response;
		try {
			List<Seguro> seguros = seguroService.findAll();
			response = new ResponseEntity<List<Seguro>>(seguros, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un seguro por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seguro> GetSeguro(@PathVariable("id") int id) {
		try {
			Optional<Seguro> seguro = seguroService.findById(id);
			if (seguro.isPresent()) {
				return new ResponseEntity<Seguro>(seguro.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Seguro>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Seguro>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite grabar un seguro")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seguro> nuevo(@RequestBody Seguro seguro) {
		try {
			Seguro nuevoSeguro = seguroService.save(seguro);
			return new ResponseEntity<Seguro>(nuevoSeguro, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Seguro>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite actualizar un seguro")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Seguro> actualizar(@PathVariable("id") Integer id, @RequestBody Seguro seguro) {
		try {
			if (id.equals(seguro.getId())) {
				Optional<Seguro> seg = seguroService.findById(id);
				if (seg.isPresent()) {
					Seguro seguroUpdate = seguroService.update(seguro);
					return new ResponseEntity<Seguro>(seguroUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Seguro>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Seguro>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Seguro>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Seguro> seguro = seguroService.findById(id);
			if (seguro.isPresent()) {
				seguroService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
