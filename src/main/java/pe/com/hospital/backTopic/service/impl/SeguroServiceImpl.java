package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Seguro;
import pe.com.hospital.backTopic.repository.SeguroRepository;
import pe.com.hospital.backTopic.service.SeguroService;

@Service
public class SeguroServiceImpl implements SeguroService {
	@Autowired
	private SeguroRepository seguroRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Seguro> findAll() throws Exception {
		return seguroRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Seguro> findById(Integer id) throws Exception {
		return seguroRepository.findById(id);
	}

	@Override
	public Seguro save(Seguro t) throws Exception {
		return seguroRepository.save(t);
	}

	@Override
	public Seguro update(Seguro t) throws Exception {
		return seguroRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		seguroRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		seguroRepository.deleteAll();
	}
}
