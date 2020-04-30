package pe.com.hospital.backTopic.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.com.hospital.backTopic.model.Medico;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {
	@Query("Select me from Medico me where me.nombre = :nombre")
	List<Medico> fetchByNombre(String nombre) throws Exception;
	@Query("Select me from Medico me where me.apellidoPaterno = :apellidoPaterno")
	List<Medico> fetchByApellidoPaterno(String apellidoPaterno) throws Exception;
	@Query("Select me from Medico me where me.apellidoMaterno = :apellidoMaterno")
	List<Medico> fetchByApellidoMaterno(String apellidoMaterno) throws Exception;
	@Query("Select me from Medico me where me.nombre = :nombre and me.apellidoPaterno = :apellidoPaterno and me.apellidoMaterno = :apellidoMaterno")
	List<Medico> fetchByNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) throws Exception;
}
