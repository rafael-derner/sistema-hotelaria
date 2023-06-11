package model.bo;

import model.dao.UsuarioDAO;
import model.exception.CpfDuplicadoException;
import model.vo.Usuario;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario inserir(Usuario novoUsuario) throws CpfDuplicadoException{
		if(usuarioDAO.verificarCpfDuplicado(novoUsuario.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado já foi utilizado.");
		}
		
		return usuarioDAO.inserir(novoUsuario);
	}

}
