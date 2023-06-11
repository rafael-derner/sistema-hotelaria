package controller;

import model.bo.UsuarioBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfDuplicadoException;
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
			mensagemValidacao += "Nome inválido \n";
		}
		
		if(novoUsuario.getCpf() == null || novoUsuario.getCpf().trim().length() < 2) {
			mensagemValidacao += "Telefone inválido \n";
		}
		
		if(novoUsuario.getTelefone() == null || novoUsuario.getTelefone().trim().length() < 2) {
			mensagemValidacao += "Telefone inválido \n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}
}
