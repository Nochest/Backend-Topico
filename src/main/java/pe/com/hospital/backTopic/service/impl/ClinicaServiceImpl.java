package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Clinica;
import pe.com.hospital.backTopic.repository.ClinicaRepository;
import pe.com.hospital.backTopic.service.ClinicaService;

@Service
public class ClinicaServiceImpl implements ClinicaService {
	@Autowired
	private ClinicaRepository clinicaRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Clinica> findAll() throws Exception {
		return clinicaRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Clinica> findById(Integer id) throws Exception {
		return clinicaRepository.findById(id);
	}

	@Override
	public Clinica save(Clinica t) throws Exception {
		return clinicaRepository.save(t);
	}

	@Override
	public Clinica update(Clinica t) throws Exception {
		return clinicaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		clinicaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		clinicaRepository.deleteAll();
	}

}
