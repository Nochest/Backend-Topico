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
import pe.com.hospital.backTopic.model.Paciente;
import pe.com.hospital.backTopic.model.Usuario;
import pe.com.hospital.backTopic.service.PacienteService;
import pe.com.hospital.backTopic.service.UsuarioService;

@Api(value = "Endpoints de Usuario")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PacienteService pacienteService;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> Listar() {
		ResponseEntity<List<Usuario>> response;
		try {
			List<Usuario> usuarios = usuarioService.findAll();
			response = new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
			return response;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@ApiOperation(value = "EndPoint que permite obtener un usuario por su ID")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> GetUsuario(@PathVariable("id") int id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "EndPoint que permite obtener correo y password")
	@GetMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> login(@RequestParam(value = "correo") String correo, @RequestParam(value = "password") String password) {
		try {
			Optional<Usuario> login = usuarioService.login(correo, password);
			if (login.isPresent()) {
				return new ResponseEntity<Usuario>(login.get(), HttpStatus.OK);
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}

	
	@ApiOperation(value = "EndPoint que permite grabar un usuario")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> nuevo(@RequestBody Usuario usuario) {
		try {
			Usuario nuevoUsuario = usuarioService.save(usuario);
			return new ResponseEntity<Usuario>(nuevoUsuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
		}
	}
	@ApiOperation(value = "EndPoint que permite grabar un paciente en un usuario, donde id es el identificador dl usuario" )
	@PostMapping(path = "/{id}/paciente", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Paciente> nuevoPaciente(@PathVariable("id") Integer id, @RequestBody Paciente paciente){
		try {
			Optional<Usuario> us = usuarioService.findById(id);
			if(us.isPresent()) {
				paciente.setUsuario(us.get());
				Paciente nuevo = pacienteService.save(paciente);
				return new ResponseEntity<Paciente>(nuevo, HttpStatus.CREATED);
			}
			else {
				return new ResponseEntity<Paciente>(HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<Paciente>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@ApiOperation(value = "EndPoint que permite actualizar un usuario")
	@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Usuario> actualizar(@PathVariable("id") Integer id, @RequestBody Usuario usuario) {
		try {
			if (id.equals(usuario.getId())) {
				Optional<Usuario> usu = usuarioService.findById(id);
				if (usu.isPresent()) {
					Usuario usuarioUpdate = usuarioService.update(usuario);
					return new ResponseEntity<Usuario>(usuarioUpdate, HttpStatus.OK);
				} else {
					return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
				}
			} else {
				return new ResponseEntity<Usuario>(HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			return new ResponseEntity<Usuario>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
		try {
			Optional<Usuario> usuario = usuarioService.findById(id);
			if (usuario.isPresent()) {
				usuarioService.deleteById(id);
				return new ResponseEntity<String>("Eliminado", HttpStatus.ACCEPTED);
			} else {
				return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
