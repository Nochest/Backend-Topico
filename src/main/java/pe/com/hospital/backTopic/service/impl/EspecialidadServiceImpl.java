package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Especialidad;
import pe.com.hospital.backTopic.repository.EspecialidadRepository;
import pe.com.hospital.backTopic.service.EspecialidadService;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
	@Autowired
	private EspecialidadRepository especialidadRepository;

	@Override
	@Transactional(readOnly = true)

	public List<Especialidad> findAll() throws Exception {
		return especialidadRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Especialidad> findById(Integer id) throws Exception {
		return especialidadRepository.findById(id);
	}

	@Override
	public Especialidad save(Especialidad t) throws Exception {
		return especialidadRepository.save(t);
	}

	@Override
	public Especialidad update(Especialidad t) throws Exception {
		return especialidadRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		especialidadRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		especialidadRepository.deleteAll();
	}

}
