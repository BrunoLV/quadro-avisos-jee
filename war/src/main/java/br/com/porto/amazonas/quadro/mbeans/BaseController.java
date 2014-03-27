package br.com.porto.amazonas.quadro.mbeans;

import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 * Classe base para os Controllers da aplicação.
 * @author bruno
 *
 */
public abstract class BaseController {
	
	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}
	
	protected ExternalContext getExternalContext() {
		return getFacesContext().getExternalContext();
	}
	
	protected HttpSession getSession() {
		HttpSession httpSession = (HttpSession) getExternalContext().getSession(false);
		return httpSession;
	}
	
	protected Object getAtributoSessao(String keySessao) {
		return getSession().getAttribute(keySessao);
	}
	
	protected void setAtributoSessao(String keySessao, Object objeto) {
		getSession().setAttribute(keySessao, objeto);
	}
	
	protected void removeAtributoSessao(String keySessao) {
		getSession().removeAttribute(keySessao);
	}
	
	protected String getMensagemParametrizada(String keyMensagemBundle) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages", getFacesContext().getViewRoot().getLocale());
		return bundle.getString(keyMensagemBundle);
	} 
	
	protected void inserirMensagemInformativa(String mensagem) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação", mensagem));
	}
	
	protected void inserirMensagemDeErro(String mensagem) {
		getFacesContext().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", mensagem));
	}
	
	protected String montarMensagemEmail(String nomeUsuario, String aviso) {
		return MessageFormat.format(getMensagemParametrizada("message.email.aviso"), nomeUsuario, aviso);
	}

}
