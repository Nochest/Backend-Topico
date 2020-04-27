package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Medico;
import pe.com.hospital.backTopic.repository.MedicoRepository;
import pe.com.hospital.backTopic.service.MedicoService;

@Service
public class MedicoServiceImpl implements MedicoService {
	@Autowired
	private MedicoRepository medicoRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Medico> findAll() throws Exception {
		return medicoRepository.findAll();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<Medico> findById(Integer id) throws Exception {
		return medicoRepository.findById(id);
	}

	@Override
	public Medico save(Medico t) throws Exception {
		return medicoRepository.save(t);
	}

	@Override
	public Medico update(Medico t) throws Exception {
		return medicoRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		medicoRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		medicoRepository.deleteAll();
	}

	@Override
	public List<Medico> fetchByNombre(String nombre) throws Exception {
		 return medicoRepository.fetchByNombre(nombre);
	}
	
	@Override
	public List<Medico> fetchByApellidoPaterno(String apellidoPaterno) throws Exception{
		return medicoRepository.fetchByApellidoPaterno(apellidoPaterno);
	}

}
