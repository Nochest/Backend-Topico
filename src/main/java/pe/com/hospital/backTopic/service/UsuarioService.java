package pe.com.hospital.backTopic.service;

import java.util.Optional;

import pe.com.hospital.backTopic.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Integer> {
	Optional<Usuario> login(String correo, String password) throws Exception;
	Optional<Usuario>findByUserAccount(String correo) throws Exception;

}
