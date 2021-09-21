package com.capiau.minhasfinancas.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capiau.minhasfinancas.exception.RegraNegocioException;
import com.capiau.minhasfinancas.model.entity.Lancamento;
import com.capiau.minhasfinancas.model.enums.StatusLancamento;
import com.capiau.minhasfinancas.model.repository.LancamentoRepository;
import com.capiau.minhasfinancas.services.LancamentoServices;

@Service
public class LancamentoServicesImpl implements LancamentoServices {

	private LancamentoRepository lancamentoRepository;

	public LancamentoServicesImpl(LancamentoRepository lancamentoRepository) {
		this.lancamentoRepository = lancamentoRepository;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Lancamento salvar(Lancamento lancamento) {
		validar(lancamento);
		return lancamentoRepository.save(lancamento);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Lancamento atualizar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getID());
		validar(lancamento);
		lancamento.setStatus(StatusLancamento.PENDENTE);
		
		return lancamentoRepository.save(lancamento);
	}

	@Override
	public void deletar(Lancamento lancamento) {
		Objects.requireNonNull(lancamento.getID());
		lancamentoRepository.delete(lancamento);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Lancamento> buscar(Lancamento lancamento) {
		Example example = Example.of(lancamento,
				ExampleMatcher.matching().withIgnoreCase().withStringMatcher(StringMatcher.CONTAINING));

		return lancamentoRepository.findAll(example);
	}

	@Override
	public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
		lancamento.setStatus(status);
		atualizar(lancamento);
	}

	@Override
	public void validar(Lancamento lancamento) {

		if (lancamento.getDescricao() == null || lancamento.getDescricao().trim().equals("")) {
			throw new RegraNegocioException("Informe uma descrição válida.");
		}

		if (lancamento.getMes() == null || lancamento.getMes() < 1 || lancamento.getMes() > 12) {
			throw new RegraNegocioException("Informe um Mês válido.");
		}

		if (lancamento.getAno() == null || lancamento.getAno().toString().length() != 4) {
			throw new RegraNegocioException("Informe um Ano válido.");
		}

		if (lancamento.getUsuario() == null || lancamento.getUsuario().getID() == null) {
			throw new RegraNegocioException("Informe um Usuário.");
		}

		if (lancamento.getValor() == null || lancamento.getValor().compareTo(BigDecimal.ZERO) < 1) {
			throw new RegraNegocioException("Informe um valor válido");
		}
		
		if(lancamento.getTipo() == null) {
			throw new RegraNegocioException("Informe um tipo de lançamento.");
		}
	}
}
