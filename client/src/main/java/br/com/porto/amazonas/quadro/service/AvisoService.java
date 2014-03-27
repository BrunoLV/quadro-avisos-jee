package br.com.porto.amazonas.quadro.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import br.com.porto.amazonas.quadro.entidades.Aviso;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;

/**
 * Interface de negócio utilizada para descrever os serviços relacionados a execução de
 * tarefas relacionada com a entidade Avisoe.
 * @author Bruno Luiz Viana
 */
@Local
public interface AvisoService {
	
	/**
	 * Método utilizado para inserir um aviso no sistema.
	 * @param aviso
	 * @throws ServiceException
	 */
	void inserirAviso(Aviso aviso) throws ServiceException;
	
	/**
	 * Método utilizado para editar um aviso no sistema.
	 * @param aviso
	 * @return
	 * @throws ServiceException
	 */
	Aviso editarAviso(Aviso aviso) throws ServiceException;
	
	/**
	 * Método utilizado para remover um aviso do sistema.
	 * @param aviso
	 * @throws ServiceException
	 */
	void removerAviso(Aviso aviso) throws ServiceException;
	
	/**
	 * Método utilizado para buscar todos avisos cadastrados no sistema.
	 * @return
	 * @throws ServiceException
	 */
	List<Aviso> buscarTodosAvisos() throws ServiceException;
	
	/**
	 * Método utilizado para buscar todos os avisos de um usuário especifico.
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	List<Aviso> buscarAvisosPorIdDoUsuario(Integer id) throws ServiceException;
	
	/**
	 * Método utilizado para buscar todos os avisos dentro da validade.
	 * @param dataValidade
	 * @return
	 * @throws ServiceException
	 */
	List<Aviso> buscarAvisosDentroDoPrazoDeValidade(Date dataValidade) throws ServiceException;

} // fim da interface AvisoService