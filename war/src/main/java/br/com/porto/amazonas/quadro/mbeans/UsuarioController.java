package br.com.porto.amazonas.quadro.mbeans;

import java.text.MessageFormat;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import br.com.porto.amazonas.criptografia.exceptions.CriptografiaException;
import br.com.porto.amazonas.criptografia.implementacao.CriptografiaSHA512;
import br.com.porto.amazonas.criptografia.interfaces.Criptografia;
import br.com.porto.amazonas.quadro.entidades.Usuario;
import br.com.porto.amazonas.quadro.service.UsuarioService;
import br.com.porto.amazonas.quadro.service.exception.ServiceException;

public class UsuarioController extends BaseController {
	
	private Usuario usuario;
	
	private static final String ITEM_AFETADO = "Usuário";
	private static final String OUTCOME_SUCESSO = "sucesso";
	
	@EJB
	private UsuarioService usuarioService;
	
	private Criptografia criptografia;
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	} 

	@PostConstruct
	public void inicializarBean() {
		this.criptografia = new CriptografiaSHA512();
		this.usuario = new Usuario();
	} // fim do método inicializarBean
	
	/**
	 * Método utilizado para cadastrar um usuario no sistema.
	 * @return
	 */
	public String cadastrarUsuario() {
		String outcome_acao = null;
		try {
			this.usuario.setId(null);
			this.usuario.setSenha(this.criptografia.criptografar(usuario.getSenha()));
			this.usuarioService.inserirUsuario(this.usuario);
			inserirMensagemInformativa(MessageFormat.format(getMensagemParametrizada("message.succes.inclusao"), ITEM_AFETADO));
			outcome_acao = OUTCOME_SUCESSO;
		} catch (ServiceException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		} catch (CriptografiaException e) {
			inserirMensagemDeErro(MessageFormat.format(getMensagemParametrizada("message.error.geral"), e.getMessage()));
		} // fim do bloco try/catch
		return outcome_acao;
	} // fim do método cadastrarUsuario

} // fim da classe UsuarioController