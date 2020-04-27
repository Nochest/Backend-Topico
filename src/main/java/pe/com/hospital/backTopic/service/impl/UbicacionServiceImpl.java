package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Ubicacion;
import pe.com.hospital.backTopic.repository.UbicacionRepository;
import pe.com.hospital.backTopic.service.UbicacionService;

@Service
public class UbicacionServiceImpl implements UbicacionService {
	@Autowired
	private UbicacionRepository ubicacionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Ubicacion> findAll() throws Exception {
		return ubicacionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Ubicacion> findById(Integer id) throws Exception {
		return ubicacionRepository.findById(id);
	}

	@Override
	public Ubicacion save(Ubicacion t) throws Exception {
		return ubicacionRepository.save(t);
	}

	@Override
	public Ubicacion update(Ubicacion t) throws Exception {
		return ubicacionRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		ubicacionRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		ubicacionRepository.deleteAll();
	}

}
