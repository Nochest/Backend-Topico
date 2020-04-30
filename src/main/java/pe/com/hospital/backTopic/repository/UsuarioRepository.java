package pe.com.hospital.backTopic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	Optional<Usuario> findByCorreo(String correo);
	@Query("Select us from Usuario us where us.correo = :correo and us.password = :password")
	Optional<Usuario> login(String correo, String password) throws Exception;
}
