package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfDuplicadoException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioController {
	private UsuarioBO usuarioBO = new UsuarioBO();
	
	public Usuario inserir(Usuario novoUsuario) throws CampoInvalidoException, CpfDuplicadoException{
		this.validarCamposObrigatorios(novoUsuario);
		return usuarioBO.inserir(novoUsuario);
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
}
