package br.com.porto.amazonas.quadro.eao;

import java.util.List;

import br.com.porto.amazonas.quadro.eao.exception.EAOException;

/**
 * Interfa base das classes que fazem manipulação no banco de dados de Entidades.
 * @author Bruno Luiz Viana
 *
 * @param <T>
 */
public interface BaseEAO<T> {
	
	/**
	 * 
	 * @param entidade
	 * @return
	 * @throws EAOException
	 */
	T persistirEntidade(T entidade) throws EAOException;
	
	/**
	 * 
	 * @param entidade
	 * @return
	 * @throws EAOException
	 */
	T editarEntidade(T entidade) throws EAOException;
	
	/**
	 * 
	 * @param entidade
	 * @throws EAOException
	 */
	void removerEntidade(T entidade) throws EAOException;
	T resetarEntidade(T entidade) throws EAOException;
	T buscarPorId(Integer id) throws EAOException;
	List<T> buscarTodos() throws EAOException;

} // fim da classe BaseEAO