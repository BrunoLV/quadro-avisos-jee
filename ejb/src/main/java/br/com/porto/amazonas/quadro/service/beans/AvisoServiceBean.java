package br.com.porto.amazonas.quadro.service.beans;

import java.util.Date;
import java.util.List;

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

import br.com.porto.amazonas.quadro.eao.AvisoEAO;
import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.eao.impl.AvisoEAOImpl;
import br.com.porto.amazonas.quadro.entidades.Aviso;
import br.com.porto.amazonas.quadro.service.AvisoService;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;
import br.com.porto.amazonas.quadro.service.interceptors.ServiceInterceptor;

@Stateless
@Interceptors({ServiceInterceptor.class})
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AvisoServiceBean implements AvisoService {
	
	private static final Logger logger = Logger.getLogger(AvisoServiceBean.class);
	
	@PersistenceContext(name = "quadroPU")
	private EntityManager manager;
	
	private AvisoEAO avisoEAO;
	
	@PostConstruct
	public void inicializarBean() {
		this.avisoEAO = new AvisoEAOImpl(this.manager);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void inserirAviso(Aviso aviso) throws ServiceException {
		try {
			this.avisoEAO.persistirEntidade(aviso);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método inserirAviso

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public Aviso editarAviso(Aviso aviso) throws ServiceException {
		try {
			return this.avisoEAO.editarEntidade(aviso);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método editarAviso

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void removerAviso(Aviso aviso) throws ServiceException {
		try {
			this.avisoEAO.removerEntidade(aviso);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método removerAviso

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Aviso> buscarTodosAvisos() throws ServiceException {
		try {
			return this.avisoEAO.buscarTodos();
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarTodos

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Aviso> buscarAvisosPorIdDoUsuario(Integer id) throws ServiceException {
		try {
			return this.avisoEAO.buscarAvisosPorIdUsuario(id);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarAvisosPorIdDoUsuario

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Aviso> buscarAvisosDentroDoPrazoDeValidade(Date dataValidade) throws ServiceException {
		try {
			return this.avisoEAO.buscarAvisosDentroDoPrazoDeValidade(dataValidade);
		} catch (EAOException e) {
			logger.error(String.format("Ocorreu um erro na execução do método: %s",  e.getMessage()), e);
			throw new ServiceException(e.getMessage(), e.getCause());
		} // fim do bloco try/catch
	} // fim do método buscarAvisosDentroDoPrazoDeValidade

} // fim da classe AvisoServiceBean