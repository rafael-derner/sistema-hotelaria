package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.UsuarioComReservaException;
import model.exception.ExclusaoGerenteException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioController {
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	public Usuario inserir(Usuario novoUsuario) throws CampoInvalidoException, CpfDuplicadoException{
		this.validarCamposObrigatorios(novoUsuario);
		return usuarioBO.inserir(novoUsuario);
	}
	
	public boolean atualizar(Usuario usuario) throws CampoInvalidoException, CpfAlteradoException {
		this.validarCamposObrigatorios(usuario);
		return usuarioBO.atualizar(usuario);
	}

	public boolean inativar(Integer idUsuario) throws UsuarioComReservaException {
		return usuarioBO.inativar(idUsuario);
	}

	private void validarCamposObrigatorios(Usuario novoUsuario) throws CampoInvalidoException{
		String mensagemValidacao = "";
		
		if(novoUsuario.getNome() == null || novoUsuario.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inv�lido \n";
		}
		
		if(novoUsuario.getCpf() == null || novoUsuario.getCpf().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}
		
		if(novoUsuario.getTelefone() == null || novoUsuario.getTelefone().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioBO.consultarComFiltro(usuarioSeletor);
	}

	public int contarTotalRegistrosComFiltros(UsuarioSeletor usuarioSeletor) {
		return usuarioBO.contarTotalRegistrosComFiltros(usuarioSeletor);
	}

	public List<Usuario> consultarTodos() {
		return usuarioBO.consultarTodos();
	}
}
