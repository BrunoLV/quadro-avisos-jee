package br.com.porto.amazonas.quadro.eao.impl;

import javax.persistence.EntityManager;

import br.com.porto.amazonas.quadro.eao.BaseEAO;

public abstract class BaseEAOImpl<T> implements BaseEAO<T> {
	
	protected EntityManager manager;
	protected Class<T> classePersistente;
	
	public BaseEAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public BaseEAOImpl(EntityManager manager) {
		this.manager = manager;
	}

	public T persistirEntidade(T entidade) {
		this.manager.persist(entidade);
		return entidade;
	}

	public T editarEntidade(T entidade) {
		return this.manager.merge(entidade);
	}

	public void removerEntidade(T entidade) {
		try {
			entidade = this.manager.merge(entidade);
			this.manager.remove(entidade);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public T resetarEntidade(T entidade) {
		this.manager.refresh(this.manager.merge(entidade));
		return entidade;
	}

	public T buscarPorId(Integer id) {
		return this.manager.find(this.classePersistente, id);
	}

}
