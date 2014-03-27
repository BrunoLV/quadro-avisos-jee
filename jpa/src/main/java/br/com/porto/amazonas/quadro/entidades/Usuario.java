package br.com.porto.amazonas.quadro.entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * Classe que mapeia a entidade de banco de dados. 
 * Tabela: tb_usuarios
 * @author BRUNO VIANA
 *
 */
@Entity
@Table(name="tb_usuarios")
@NamedQueries({
	@NamedQuery(name="buscarPorMatriculaESenha", query="SELECT u FROM Usuario u WHERE u.matricula = :matricula AND u.senha = :senha"),
	@NamedQuery(name="buscarUsuarioFetchAvisos", query="SELECT u FROM Usuario u JOIN FETCH u.avisos WHERE u.id = :id"),
	@NamedQuery(name="buscarTodos", query="SELECT u FROM Usuario u")
	})
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String matricula;
	private String nome;
	private String senha;
	private List<Aviso> avisos;

	public Usuario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario", insertable=false, updatable=false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	@Column(name="mat_usuario")
	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}


	@Column(name="nom_usuario")
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


	@Column(name="pass_usuario")
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}


	//bi-directional many-to-one association to Aviso
	@OneToMany(mappedBy="usuario")
	public List<Aviso> getAvisos() {
		return this.avisos;
	}

	public void setAvisos(List<Aviso> avisos) {
		this.avisos = avisos;
	}

	public Aviso addAviso(Aviso aviso) {
		getAvisos().add(aviso);
		aviso.setUsuario(this);

		return aviso;
	}

	public Aviso removeAviso(Aviso aviso) {
		getAvisos().remove(aviso);
		aviso.setUsuario(null);

		return aviso;
	}

}