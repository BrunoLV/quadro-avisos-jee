package br.com.porto.amazonas.quadro.eao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.porto.amazonas.quadro.eao.AvisoEAO;
import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.entidades.Aviso;

/**
 * Implementação de AvisoEAO.
 * @author Bruno Luiz Viana
 * @see AvisoEAO
 *
 */
public class AvisoEAOImpl extends BaseEAOImpl<Aviso> implements AvisoEAO {
	
	private static final Class<Aviso> CLASSE_PERSISTENTE = Aviso.class;
	
	public AvisoEAOImpl() {
	}
	
	public AvisoEAOImpl(EntityManager manager) {
		this.manager = manager;
		this.classePersistente = CLASSE_PERSISTENTE;
	}

	@SuppressWarnings("unchecked")
	public List<Aviso> buscarTodos() throws EAOException {
		try {
			Query query = this.manager.createNamedQuery("buscarTodosAvisos");
			return query.getResultList();
		} catch (Exception e) {
			throw new EAOException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarTodos

	@SuppressWarnings("unchecked")
	public List<Aviso> buscarAvisosPorIdUsuario(Integer idUsuario) throws EAOException {
		try {
			Query query = this.manager.createNamedQuery("buscarTodosAvisosDeUmUsuario");
			query.setParameter("idUsuario", idUsuario);
			return query.getResultList();
		} catch (Exception e) {
			throw new EAOException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarAvisosPorIdUsuario

	@SuppressWarnings("unchecked")
	public List<Aviso> buscarAvisosDentroDoPrazoDeValidade(Date dataValidade) throws EAOException {
		try {
			Query query = this.manager.createNamedQuery("buscarTodosAvisosDentroDoPeriodoDeValidade");
			query.setParameter("dataValidade", dataValidade);
			return query.getResultList();
		} catch (Exception e) {
			throw new EAOException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarAvisosDentroDoPrazoDeValidade

} // fim da classe AvisoEAOImpl