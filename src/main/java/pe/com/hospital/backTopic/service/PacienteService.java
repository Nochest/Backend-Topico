package pe.com.hospital.backTopic.service;

import java.util.List;
import java.util.Optional;

import pe.com.hospital.backTopic.model.Paciente;

public interface PacienteService extends CrudService<Paciente, Integer> {
	List<Paciente> findByUserId( int id) throws Exception;
	Optional<Paciente> findInfoPacienteUser(int id, boolean poseeCuenta) throws Exception;
	
}
