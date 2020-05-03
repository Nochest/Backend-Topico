package pe.com.hospital.backTopic.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Cita;
import pe.com.hospital.backTopic.repository.CitaRepository;
import pe.com.hospital.backTopic.service.CitaService;

@Service
public class CitaServiceImpl implements CitaService {
	@Autowired
	private CitaRepository citaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Cita> findAll() throws Exception {
		return citaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Cita> findById(Integer id) throws Exception {
		return citaRepository.findById(id);
	}

	@Override
	public Cita save(Cita t) throws Exception {
		return citaRepository.save(t);
	}

	@Override
	public Cita update(Cita t) throws Exception {
		return citaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		citaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		citaRepository.deleteAll();
	}

	@Override
	public List<Cita> fetchByCitasDisponibles() throws Exception {
		return citaRepository.fetchByCitasDisponibles();
	}

	@Override
	public List<Cita> citaIdeal(Date fecha, String distrito, String especialidad, String seguro) throws Exception {
		return citaRepository.citaIdeal(fecha, distrito, especialidad, seguro);
	}

	/*@Override
	public List<Cita> findByLugarSeguroEspecialidadHora(String nombreSeguro, String nombreEspecialidad,
			String nombreLugar, Date hora) throws Exception {
		return citaRepository.findByLugarSeguroEspecialidadHora(nombreSeguro, nombreEspecialidad, nombreLugar, hora);
	}*/

}
