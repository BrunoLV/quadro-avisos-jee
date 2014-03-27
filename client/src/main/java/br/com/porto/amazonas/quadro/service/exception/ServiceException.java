package br.com.porto.amazonas.quadro.service.exception;

/**
 * Exception utilizada para encapsular os erros ocorridos na execução das regras de
 * negócio.
 * @author Bruno Luiz Viana
 *
 */
public class ServiceException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ServiceException() {
	}
	
	public ServiceException(String message) {
		super(message);
	}
	
	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

} // fim da classe ServiceException