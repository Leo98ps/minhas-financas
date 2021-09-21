package com.capiau.minhasfinancas.services;

import com.capiau.minhasfinancas.model.entity.Usuario;

public interface UsuarioServices {

	public Usuario autenticar(String email,String senha);
	
	public Usuario salvarUsuario(Usuario usuario);
	
	public void validarEmail(String email);
}
