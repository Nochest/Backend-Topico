package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.SeguroClinica;
import pe.com.hospital.backTopic.repository.SeguroClinicaRepository;
import pe.com.hospital.backTopic.service.SeguroClinicaService;

@Service
public class SeguroClinicaServiceImpl implements SeguroClinicaService {
	@Autowired
	private SeguroClinicaRepository seguroClinicaRepository;

	@Override
	@Transactional(readOnly = true)
	public List<SeguroClinica> findAll() throws Exception {
		return seguroClinicaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SeguroClinica> findById(Integer id) throws Exception {
		return seguroClinicaRepository.findById(id);
	}

	@Override
	public SeguroClinica save(SeguroClinica t) throws Exception {
		return seguroClinicaRepository.save(t);
	}

	@Override
	public SeguroClinica update(SeguroClinica t) throws Exception {
		return seguroClinicaRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		seguroClinicaRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		seguroClinicaRepository.deleteAll();
	}

}
