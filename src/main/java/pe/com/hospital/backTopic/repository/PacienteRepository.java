package pe.com.hospital.backTopic.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {
	
	@Query("select pa from Paciente pa where pa.usuario.id = :id")
	List<Paciente> findByUserId( int id) throws Exception;

	@Query("select pa from Paciente pa where pa.usuario.id = :id ")
	Optional<Paciente> findInfoPacienteUser(int id) throws Exception;
	
	@Query("select pa from Paciente pa where pa.accountManagment = :poseedor")
	Optional<Paciente> findPoseedor(boolean poseedor) throws Exception;
}
