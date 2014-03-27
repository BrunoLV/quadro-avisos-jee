package br.com.porto.amazonas.quadro.listeners;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/**
 * Phase Listener utilizado para manter as mensagens no contexto entre as requisições
 * para que as mesmas sejam mostradas na pagina de destino.
 * @author Bruno Luiz Viana
 *
 */
public class FacesMessageListener implements PhaseListener {

	private static final long serialVersionUID = 1L;
	private static final String sessionToken = "MESSAGE_SESSION";
	
	/*
	 * Estou determinando que a mensagem será persistida no contexto caso ainda não esteja na fase de
	 * renderização da resposta.
	 * (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
		if (!PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
			FacesContext facesContext = event.getFacesContext();
			this.alocarMensagensNaSessao(facesContext);
		} // fim do bloco if
	} // fim do método afterPhase

	/*
	 * Estou determinando que na fase de renderização da resposta as mensagens serão restauradas do contexto e
	 * mostradas na tela.
	 * (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {
		if(PhaseId.RENDER_RESPONSE.equals(event.getPhaseId())) {
			FacesContext facesContext = event.getFacesContext();
			this.recuperarMensagensDaSessao(facesContext);
		} // fim do bloco if
	} // fim do método beforePhase

	/*
	 * Estou determinando qual fase do ciclo de vida esse listener escuta.
	 * (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	} // fim do métod getPhaseId
	
	/**
	 * Método utilizado para alocar as mensagens presentes no contexto do Faces na sessão para que elas possam
	 * trafegar pelo ciclo de vida das requisições.
	 * @param facesContext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int alocarMensagensNaSessao(final FacesContext facesContext) {
		// Recupera as mensagens que estçao no contexto
		List<FacesMessage> messages = new ArrayList<FacesMessage>();
		for (Iterator<FacesMessage> iterator = facesContext.getMessages(null); iterator.hasNext();) {
			messages.add(iterator.next());
			iterator.remove();
		} // fim do bloco for
		
		// Guarda na sessão
		if (messages.size() == 0) {
			return 0;
		} // fim do bloco if
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		List<FacesMessage> existingMessages = (List<FacesMessage>) sessionMap.get(sessionToken);
		if (existingMessages != null) {
			existingMessages.addAll(messages);
		} else {
			sessionMap.put(sessionToken, messages);
		} // fim do bloco if/else
		return messages.size();
	} // fim do método alocarMensagensNaSessao
	
	/**
	 * Método utilizado para recuperar as mensagens alocadas na sessão para apresentação em tela.
	 * @param facesContext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private int recuperarMensagensDaSessao(final FacesContext facesContext) {
		// Recupera as mensagens da sessão.
		Map<String, Object> sessionMap = facesContext.getExternalContext().getSessionMap();
		List<FacesMessage> messages = (List<FacesMessage>) sessionMap.remove(sessionToken);
		
		if (messages == null) {
			return 0;
		} // fim do bloco if
		int restoredCount = messages.size();
		
		// Lista que contém as mensagens que já estão no FacesContext
		List<FacesMessage> facesContextMessages = new ArrayList<FacesMessage>();
		for (Iterator<FacesMessage> iterator = facesContext.getMessages(null); iterator.hasNext();) {
			facesContextMessages.add(iterator.next());
			iterator.remove();
		} // fim do bloco for
		
		// Adiciona as que não estão.
		for (FacesMessage facesMessage : messages) {
			if (!facesContextMessages.contains(facesMessage)) {
				facesContext.addMessage(null, facesMessage);
			} // fim do bloco if
		} // fim do bloco for
		
		return restoredCount;
	} // fim do método

} // fim da classe FacesMessageListener