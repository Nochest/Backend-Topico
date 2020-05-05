package pe.com.hospital.backTopic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	
	@Query("select pa from Paciente pa where pa.usuario.id = :id")
	List<Paciente> findByUserId( int id) throws Exception;

}
