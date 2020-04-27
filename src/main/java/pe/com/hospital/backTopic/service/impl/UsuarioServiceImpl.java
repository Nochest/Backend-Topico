package pe.com.hospital.backTopic.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.hospital.backTopic.model.Usuario;
import pe.com.hospital.backTopic.repository.UsuarioRepository;
import pe.com.hospital.backTopic.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() throws Exception {
		return usuarioRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Integer id) throws Exception {
		return usuarioRepository.findById(id);
	}

	@Override
	public Usuario save(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public Usuario update(Usuario t) throws Exception {
		return usuarioRepository.save(t);
	}

	@Override
	public void deleteById(Integer id) throws Exception {
		usuarioRepository.deleteById(id);
	}

	@Override
	public void deleteAll() throws Exception {
		usuarioRepository.deleteAll();
	}

}
