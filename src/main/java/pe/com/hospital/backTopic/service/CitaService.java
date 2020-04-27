package pe.com.hospital.backTopic.service;

import java.util.List;

import pe.com.hospital.backTopic.model.Cita;

public interface CitaService extends CrudService<Cita, Integer> {
	List<Cita> fetchByCitasDisponibles() throws Exception;
}
