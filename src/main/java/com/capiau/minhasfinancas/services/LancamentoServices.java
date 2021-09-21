package com.capiau.minhasfinancas.services;

import java.util.List;

import com.capiau.minhasfinancas.model.entity.Lancamento;
import com.capiau.minhasfinancas.model.enums.StatusLancamento;

public interface LancamentoServices {

	public Lancamento salvar(Lancamento lancamento);

	public Lancamento atualizar(Lancamento lancamento);

	public void deletar(Lancamento lancamento);

	public List<Lancamento> buscar(Lancamento lancamento);

	public void atualizarStatus(Lancamento lancamento, StatusLancamento status);
	
	public void validar(Lancamento lancamento);
}
