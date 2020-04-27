package pe.com.hospital.backTopic.service;

import java.util.List;

import pe.com.hospital.backTopic.model.Medico;

public interface MedicoService extends CrudService<Medico, Integer> {
	List<Medico> fetchByNombre(String nombre) throws Exception;
	List<Medico> fetchByApellidoPaterno(String apellidoPaterno) throws Exception;
}

