package br.com.porto.amazonas.quadro.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * Classe persistente para a entidade de banco de dados Aviso.
 * Tabela: tb_avisos
 * @author bruno
 *
 */
@Entity
@Table(name="tb_avisos")
@NamedQueries({
	@NamedQuery(name="buscarTodosAvisos", query="SELECT a FROM Aviso a"),
	@NamedQuery(name="buscarTodosAvisosDeUmUsuario", query="SELECT a FROM Aviso a JOIN a.usuario u WHERE u.id = :idUsuario"),
	@NamedQuery(name="buscarTodosAvisosDentroDoPeriodoDeValidade", query="SELECT a FROM Aviso a WHERE a.dataCadastro >= :dataValidade")
})
public class Aviso implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Date dataCadastro;
	private String texto;
	private Date vigencia;
	private Usuario usuario;

	public Aviso() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_aviso", updatable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="cad_aviso")
	public Date getDataCadastro() {
		return this.dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	@Column(name="txt_aviso")
	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="vig_aviso")
	public Date getVigencia() {
		return this.vigencia;
	}

	public void setVigencia(Date vigencia) {
		this.vigencia = vigencia;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_usuario")
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

} // fim da classe Aviso