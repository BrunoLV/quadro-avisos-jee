package br.com.porto.amazonas.quadro.service;

import javax.ejb.Local;

import br.com.porto.amazonas.quadro.entidades.Usuario;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;

/**
 * Interface local do EJB que lida com ações relacionadas com a entidade Usuário.
 * @author BRUNO VIANA
 */
@Local
public interface UsuarioService {
	
	/**
	 * Método utilizado para inserir um usuario no sistema.
	 * @param usuario
	 * @throws ServiceException
	 */
	void inserirUsuario(Usuario usuario) throws ServiceException;
	
	/**
	 * Método utilizado para editar um usuario no sistema.
	 * @param matricula
	 * @param senha
	 * @return
	 * @throws ServiceException
	 */
	Usuario buscarUsuario(String matricula, String senha) throws ServiceException;
	
	/**
	 * Método utilizado para remover um usuário do sistema.
	 * @param id
	 * @return
	 * @throws ServiceException
	 */
	Usuario buscarUsuarioPorIdComAvisos(Integer id) throws ServiceException;
	
} // fim da interface UsuarioService