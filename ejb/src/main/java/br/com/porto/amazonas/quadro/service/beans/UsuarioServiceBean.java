package br.com.porto.amazonas.quadro.service.beans;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import br.com.porto.amazonas.quadro.eao.UsuarioEAO;
import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.eao.impl.UsuarioEAOImpl;
import br.com.porto.amazonas.quadro.entidades.Usuario;
import br.com.porto.amazonas.quadro.service.UsuarioService;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;
import br.com.porto.amazonas.quadro.service.interceptors.ServiceInterceptor;

@Stateless
@Interceptors({ServiceInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UsuarioServiceBean implements UsuarioService {

	private static final Logger logger = Logger.getLogger(UsuarioServiceBean.class);
	
	@PersistenceContext(name = "quadroPU")
	private EntityManager manager;
	
	private UsuarioEAO usuarioEAO;
	
	@PostConstruct
	public void inicializarBean() {
		this.usuarioEAO = new UsuarioEAOImpl(this.manager);
	}
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuario(String matricula, String senha) throws ServiceException {
		try {
			return this.usuarioEAO.buscarPorMatriculaESenha(matricula, senha);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserirUsuario(Usuario usuario) throws ServiceException {
		try {
			this.usuarioEAO.persistirEntidade(usuario);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método inserirUsuario

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Usuario buscarUsuarioPorIdComAvisos(Integer id) throws ServiceException {
		try {
			return this.usuarioEAO.buscarPorIdTrazendoAvisos(id);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarUsuarioPorIdComAvisos

}
