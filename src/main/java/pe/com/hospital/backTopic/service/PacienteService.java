package pe.com.hospital.backTopic.service;

import java.util.List;

import pe.com.hospital.backTopic.model.Paciente;

public interface PacienteService extends CrudService<Paciente, Integer> {
	List<Paciente> findByUserId( int id) throws Exception;

}
