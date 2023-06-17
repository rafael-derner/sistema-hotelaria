package model.bo;

import java.util.ArrayList;
import java.util.List;

import model.dao.UsuarioDAO;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario inserir(Usuario novoUsuario) throws CpfDuplicadoException{
		if(usuarioDAO.verificarCpfDuplicado(novoUsuario.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado jï¿½ foi utilizado.");
		}
		
		return usuarioDAO.inserir(novoUsuario);
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.consultarComFiltro(usuarioSeletor);
	}

	public boolean atualizar(Usuario usuario) throws CpfAlteradoException {
		Usuario usuarioOld = usuarioDAO.consultarPorId(usuario.getIdUsuario());
		if(usuario.getCpf() != usuarioOld.getCpf()) {
			System.out.println("old = " + usuarioOld.getCpf() + " || new = " + usuario.getCpf());
			throw new CpfAlteradoException("O CPF não pode ser alterado");
		}
		
		return usuarioDAO.atualizar(usuario);
	}

}
