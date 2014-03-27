package br.com.porto.amazonas.quadro.mbeans;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.porto.amazonas.criptografia.exceptions.CriptografiaException;
import br.com.porto.amazonas.criptografia.implementacao.CriptografiaSHA512;
import br.com.porto.amazonas.criptografia.interfaces.Criptografia;
import br.com.porto.amazonas.quadro.entidades.Usuario;
import br.com.porto.amazonas.quadro.service.UsuarioService;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;

/**
 * Classe controladora de Login da aplicação.
 * @author Bruno Luiz Viana
 *
 */
public class LoginController extends BaseController {
	
	private static final String OUTCOME_SUCESSO = "sucesso";
	
	private String matricula;
	private String senha;
	
	@EJB
	private UsuarioService usuarioService;
	
	private Criptografia criptografia;
	
	public String getMatricula() {
		return matricula;
	}
	
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@PostConstruct
	public void inicializarBean() {
		this.criptografia = new CriptografiaSHA512();
	}

	/**
	 * Método utilizado para efetuar a ação de login na aplicação.
	 * @return
	 */
	public String executarLogin() {
		String outcome = null;
		try {
			Usuario usuario = this.usuarioService.buscarUsuario(this.matricula, this.criptografia.criptografar(this.senha));
			if (usuario == null) {
				inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.login"), null));
			} else {
				HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				session.setAttribute("usuarioLogado", usuario);
				outcome = OUTCOME_SUCESSO;
			} // fim do do bloco try/catch
		} catch (ServiceException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		} catch (CriptografiaException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		}
		return outcome;
	} // fim do método executarLogin
	
	/**
	 * Método utilizado para efetuar a ação de logout na aplicação.
	 * @return
	 */
	public String efetuarLogout() {
		removeAtributoSessao("usuarioLogado");
		getSession().invalidate();
		return "sucesso";
	} // fim do método efetuarLogout

} // fim da classe LoginController