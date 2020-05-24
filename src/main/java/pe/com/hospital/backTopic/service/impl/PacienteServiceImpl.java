package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Paciente;
import pe.com.hospital.backTopic.repository.PacienteRepository;
import pe.com.hospital.backTopic.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Paciente> findAll() throws Exception {
		return pacienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Paciente> findById(Integer id) throws Exception {
		return pacienteRepository.findById(id);
	}

	@Override
	public Paciente save(Paciente t) throws Exception {
		return pacienteRepository.save(t);
	}

	@Override
	public Paciente update(Paciente t) throws Exception {
		return pacienteRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		pacienteRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		pacienteRepository.deleteAll();
	}

	@Override
	public List<Paciente> findByUserId(int id) throws Exception {
		return pacienteRepository.findByUserId(id);
	}

	@Override
	public Optional<Paciente> findInfoPacienteUser(int id) throws Exception {
		return pacienteRepository.findInfoPacienteUser(id);
	}

}
