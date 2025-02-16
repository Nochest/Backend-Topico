package pe.com.hospital.backTopic.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import pe.com.hospital.backTopic.model.Cita;
import pe.com.hospital.backTopic.model.Medico;

public interface CitaService extends CrudService<Cita, Integer> {
	List<Cita> fetchByCitasDisponibles() throws Exception;
	//List<Cita> findByLugarSeguroEspecialidadHora(String nombreSeguro, String nombreEspecialidad, String nombreLugar, Date hora) throws Exception;
	List<Cita> citaIdeal(Date fecha, String Distrito, String especialidad, String seguro) throws Exception;
	List<Cita> findByUSerId(int id) throws Exception;
	
	Optional<Medico> findByCitaMedico(Medico medico, int id) throws Exception;
}
