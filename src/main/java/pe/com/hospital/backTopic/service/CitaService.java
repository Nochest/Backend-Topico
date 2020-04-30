package pe.com.hospital.backTopic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.com.hospital.backTopic.model.Cita;

public interface CitaService extends CrudService<Cita, Integer> {
	List<Cita> fetchByCitasDisponibles() throws Exception;
	//List<Cita> findByLugarSeguroEspecialidadHora(String nombreSeguro, String nombreEspecialidad, String nombreLugar, Date hora) throws Exception;
	Optional<Cita> citaIdeal(Date fecha) throws Exception;
}
