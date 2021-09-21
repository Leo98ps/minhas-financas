package com.capiau.minhasfinancas.api.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capiau.minhasfinancas.api.dto.UsuarioDTO;
import com.capiau.minhasfinancas.exception.ErroAutenticacao;
import com.capiau.minhasfinancas.exception.RegraNegocioException;
import com.capiau.minhasfinancas.model.entity.Usuario;
import com.capiau.minhasfinancas.services.UsuarioServices;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioResource {

	private UsuarioServices usuarioService;

	public UsuarioResource(UsuarioServices usuarioService) {
		this.usuarioService = usuarioService;
	}

	@PostMapping("/autenticar")
	public ResponseEntity autenticar(@RequestBody UsuarioDTO dto) {
		try {
			Usuario usuarioAutenticado = usuarioService.autenticar(dto.getEmail(), dto.getSenha());
			return ResponseEntity.ok(usuarioAutenticado);
		} catch (ErroAutenticacao ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity salvar(@RequestBody UsuarioDTO dto) {
		Usuario usuario = new Usuario();
		
		usuario.setEmail(dto.getEmail());
		usuario.setNome(dto.getNome());
		usuario.setSenha(dto.getSenha());
		
		try {
			usuario = usuarioService.salvarUsuario(usuario);
			return new ResponseEntity(usuario, HttpStatus.CREATED);
		} catch (RegraNegocioException ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}
}