package br.com.porto.amazonas.quadro.mbeans;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.porto.amazonas.quadro.entidades.Aviso;
import br.com.porto.amazonas.quadro.entidades.Usuario;
import br.com.porto.amazonas.quadro.service.AvisoService;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;

/**
 * Classe que interage com a View para ações que envolvem Aviso.
 * @author bruno
 *
 */
public class AvisoController extends BaseController {
	
	private Aviso aviso;
	private List<Aviso> avisos;
	
	private static final String OUTCOME_SUCESSO = "sucesso";
	private static final String OUTCOME_ROTA_EDICAO = "editar";
	
	private static final String ITEM_AFETADO = "Aviso";
	
	@EJB 
	private AvisoService avisoService;
	
	@PostConstruct
	private void inicializarBean() {
		Aviso aviso = (Aviso) getAtributoSessao("avisoEdicao");
		if(aviso == null) {
			this.aviso = new Aviso();
		} else {
			this.aviso = aviso;
		} // fim do bloco if/else
		this.listarAvisos();
	} // fim do méto inicializarBean
	
	public Aviso getAviso() {
		return aviso;
	}
	
	public void setAviso(Aviso aviso) {
		this.aviso = aviso;
	}
	
	public List<Aviso> getAvisos() {
		return avisos;
	}
	
	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}
	
	/**
	 * Método utilizado para executar a ação de cadastro de avisos no sistema.
	 * Pode tanto executar a ação de inclusão como atualizar registro já existente.
	 * @return
	 */
	public String cadastrarAviso() {
		String outcome_acao = null;
		try {
			if(this.aviso.getId() != null && !this.aviso.getId().equals(Integer.valueOf(0))) {
				this.avisoService.editarAviso(this.aviso);
				removeAtributoSessao("avisoEdicao");
				inserirMensagemInformativa(MessageFormat.format(getMensagemParametrizada("message.succes.edicao"), ITEM_AFETADO));
			} else {
				this.aviso.setId(null);
				this.aviso.setDataCadastro(new Date());
				this.aviso.setUsuario((Usuario) getAtributoSessao("usuarioLogado"));
				this.avisoService.inserirAviso(this.aviso);
				inserirMensagemInformativa(MessageFormat.format(getMensagemParametrizada("message.succes.inclusao"), ITEM_AFETADO));
			} // fim do bloco if/else
			outcome_acao = OUTCOME_SUCESSO;
		} catch (ServiceException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		} // fim do bloco try/catch
		return outcome_acao;
	} // fim do método cadastrarAviso
	
	/**
	 * Método utilizado para executar a ação de remover registros do sistema.
	 * @return
	 */
	public String removerAviso() {
		try {
			this.avisoService.removerAviso(this.aviso);
			inserirMensagemInformativa(MessageFormat.format(getMensagemParametrizada("message.succes.remocao"), ITEM_AFETADO));
		} catch (ServiceException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		} // fim do bloco try/catch
		this.inicializarBean();
		return null;
	} // fim do método removerAviso
	
	/*
	 * Método interno para atualizar a lista de avisos que será apresentada na tela.
	 */
	private void listarAvisos() {
		try {
			this.avisos = this.avisoService.buscarAvisosDentroDoPrazoDeValidade(Calendar.getInstance(getFacesContext().getViewRoot().getLocale()).getTime());
		} catch (ServiceException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
			this.avisos = new ArrayList<Aviso>();
		} // fim do bloco try/catch
	} // fim do método listarAvisos
	
	/*
	 * Método utilizado para cancelar a manutenção de um aviso no sistema.
	 */
	public String cancelarManutencaoAviso() {
		if (getAtributoSessao("avisoEdicao") == null) {
			removeAtributoSessao("avisoSelecionado");
		} // fim do bloco if
		return OUTCOME_SUCESSO;
	} // fim do método cancelarManutencaoAviso
	
	/*
	 * Método interno utilizado para carregar o aviso selecionado na sessão para realizar redirect
	 * nas requests da aplicação.
	 */
	public String selecionarParaEdicao() {
		setAtributoSessao("avisoEdicao", this.aviso);
		return OUTCOME_ROTA_EDICAO;
	} // fim do método selecionarParaEdicao

} // fim da classe AvisoController