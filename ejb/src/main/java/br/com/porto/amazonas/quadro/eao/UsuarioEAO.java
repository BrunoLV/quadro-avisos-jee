package br.com.porto.amazonas.quadro.eao;

import br.com.porto.amazonas.quadro.eao.exception.EAOException;
import br.com.porto.amazonas.quadro.entidades.Usuario;

public interface UsuarioEAO extends BaseEAO<Usuario> {
	
	Usuario buscarPorMatriculaESenha(String matricula, String senha) throws EAOException;
	Usuario buscarPorIdTrazendoAvisos(Integer id) throws EAOException;

}