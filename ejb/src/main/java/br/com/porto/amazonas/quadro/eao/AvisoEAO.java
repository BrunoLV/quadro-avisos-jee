package br.com.porto.amazonas.quadro.eao;

import java.util.Date;
import java.util.List;

import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.entidades.Aviso;

/**
 * Interface utilizada para descrever o comportamento de classes que fazem manipulação de dados
 * na entidade Aviso.
 * @author Bruno Luiz Viana
 *
 */
public interface AvisoEAO extends BaseEAO<Aviso> {
	
	/**
	 * Método utilizado para buscar todos os avisos de um determinada usuário.
	 * @param idUsuario
	 * @return
	 * @throws EAOException
	 */
	List<Aviso> buscarAvisosPorIdUsuario(Integer idUsuario) throws EAOException;
	
	/**
	 * Método utilizado para buscar todos os avisos que estão dentro de uma data de validade especifica.
	 * @param dataValidade
	 * @return
	 * @throws EAOException
	 */
	List<Aviso> buscarAvisosDentroDoPrazoDeValidade(Date dataValidade) throws EAOException;
	
} // fim da interface AvisoEAO