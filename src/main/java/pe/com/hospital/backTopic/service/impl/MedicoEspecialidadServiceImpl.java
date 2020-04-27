package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.MedicoEspecialidad;
import pe.com.hospital.backTopic.repository.MedicoEspecialidadRepository;
import pe.com.hospital.backTopic.service.MedicoEspecialidadService;

@Service
public class MedicoEspecialidadServiceImpl implements MedicoEspecialidadService {
	@Autowired
	private MedicoEspecialidadRepository medicoEspecialidadRepository;

	@Transactional(readOnly = true)
	@Override
	public List<MedicoEspecialidad> findAll() throws Exception {
		return medicoEspecialidadRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<MedicoEspecialidad> findById(Integer id) throws Exception {
		return medicoEspecialidadRepository.findById(id);
	}

	@Override
	public MedicoEspecialidad save(MedicoEspecialidad t) throws Exception {
		return medicoEspecialidadRepository.save(t);
	}

	@Override
	public MedicoEspecialidad update(MedicoEspecialidad t) throws Exception {
		return medicoEspecialidadRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		medicoEspecialidadRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		medicoEspecialidadRepository.deleteAll();
	}

}
