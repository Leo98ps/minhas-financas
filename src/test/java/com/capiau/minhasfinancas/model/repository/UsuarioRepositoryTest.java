package com.capiau.minhasfinancas.model.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.capiau.minhasfinancas.model.entity.Usuario;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	public UsuarioRepository usuarioRepository;

	/**
	 * 
	 */
	@Test
	public void verificaExitenciaEmail() {
		Usuario usuario = new Usuario();
		usuario.setNome("Usuario");
		usuario.setEmail("usuario@hotmail.com");

		usuarioRepository.save(usuario);

		boolean resultado = usuarioRepository.existsByEmail("usuario@hotmail.com");
		Assertions.assertThat(resultado).isTrue();
	}

	@Test
	public void verificaEmailUsuario() {
		usuarioRepository.deleteAll();
		
		boolean result = usuarioRepository.existsByEmail("usuario@hotmail.com");
		Assertions.assertThat(result).isFalse();
	}
}
