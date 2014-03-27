package br.com.porto.amazonas.quadro.eao.exception;

public class EAOException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public EAOException() {
	}
	
	public EAOException(String message) {
		super(message);
	}
	
	public EAOException(Throwable cause) {
		super(cause);
	}
	
	public EAOException(String message, Throwable cause) {
		super(message, cause);
	}

} // fim da classe EAOException
