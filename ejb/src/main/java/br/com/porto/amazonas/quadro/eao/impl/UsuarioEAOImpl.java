package br.com.porto.amazonas.quadro.eao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.porto.amazonas.quadro.eao.UsuarioEAO;
import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.entidades.Usuario;

public class UsuarioEAOImpl extends BaseEAOImpl<Usuario> implements UsuarioEAO {

	private static final Class<Usuario> CLASSE_PERSISTENTE = Usuario.class;
	
	public UsuarioEAOImpl() {
	}
	
	public UsuarioEAOImpl(EntityManager manager) {
		super(manager);
		this.classePersistente = CLASSE_PERSISTENTE;
	}
	
	@SuppressWarnings("unchecked")
	public List<Usuario> buscarTodos() {
		Query query = this.manager.createNamedQuery("buscarTodos");
		return query.getResultList();
	}

	public Usuario buscarPorMatriculaESenha(String matricula, String senha) throws EAOException {
		try {
			Query query = this.manager.createNamedQuery("buscarPorMatriculaESenha");
			query.setParameter("matricula", matricula);
			query.setParameter("senha", senha);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			throw new EAOException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarPorMatriculaESenha

	public Usuario buscarPorIdTrazendoAvisos(Integer id) throws EAOException {
		try {
			Query query = this.manager.createNamedQuery("buscarUsuarioFetchAvisos");
			query.setParameter("id", id);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			throw new EAOException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarPorMatriculaESenha

} // fim da classe UsuarioEAOImpl