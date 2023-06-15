package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.UsuarioDAO;
import model.exception.CpfDuplicadoException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario inserir(Usuario novoUsuario) throws CpfDuplicadoException{
		if(usuarioDAO.verificarCpfDuplicado(novoUsuario.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado j� foi utilizado.");
		}
		
		return usuarioDAO.inserir(novoUsuario);
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.consultarComFiltro(usuarioSeletor);
	}

}
