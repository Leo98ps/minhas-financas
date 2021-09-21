package com.capiau.minhasfinancas.services.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.capiau.minhasfinancas.exception.ErroAutenticacao;
import com.capiau.minhasfinancas.exception.RegraNegocioException;
import com.capiau.minhasfinancas.model.entity.Usuario;
import com.capiau.minhasfinancas.model.repository.UsuarioRepository;
import com.capiau.minhasfinancas.services.UsuarioServices;

@Service
public class UsuarioServicesImpl implements UsuarioServices {

	private UsuarioRepository usuarioRepository;

	public UsuarioServicesImpl(UsuarioRepository usuarioRepository) {
		super();
		this.usuarioRepository = usuarioRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = usuarioRepository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new ErroAutenticacao("Usuario não encontrado");
		}

		if (!usuario.get().getSenha().equals(senha)) {
			throw new ErroAutenticacao("Senha inválida");
		}

		return usuario.get();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return usuarioRepository.save(usuario);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void validarEmail(String email) {
		boolean existe = usuarioRepository.existsByEmail(email);
		if (existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email.");
		}
	}
}
