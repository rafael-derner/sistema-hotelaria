package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.UsuarioBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.UsuarioComReservaException;
import model.exception.UsuarioInativoException;
import model.gerador.GeradorPlanilha;
import model.exception.ExclusaoGerenteException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioController {
	private UsuarioBO usuarioBO = new UsuarioBO();

	public Usuario inserir(Usuario novoUsuario) throws CampoInvalidoException, CpfDuplicadoException {
		this.validarCamposObrigatorios(novoUsuario);
		return usuarioBO.inserir(novoUsuario);
	}

	public boolean atualizar(Usuario usuario) throws CampoInvalidoException, CpfAlteradoException {
		this.validarCamposObrigatorios(usuario);
		return usuarioBO.atualizar(usuario);
	}

	public boolean inativar(Integer idUsuario) throws UsuarioInativoException {
		return usuarioBO.inativar(idUsuario);
	}

	private void validarCamposObrigatorios(Usuario novoUsuario) throws CampoInvalidoException {
		String mensagemValidacao = "";

		if (novoUsuario.getNome() == null || novoUsuario.getNome().trim().length() < 2
				|| novoUsuario.getNome().matches(".*\\d+.*")) {
			mensagemValidacao += "Nome inválido \n";
		}

		if (novoUsuario.getCpf() == null || novoUsuario.getCpf().trim().length() < 2 || !validarCpf(novoUsuario.getCpf())) {
			mensagemValidacao += "CPF inválido \n";
		}

		if (novoUsuario.getTelefone() == null || novoUsuario.getTelefone().trim().length() < 2) {
			mensagemValidacao += "Telefone inválido \n";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}



	private boolean validarCpf(String cpf) {
		//Remove caracteres não numéricos
		cpf = cpf.replaceAll("\\D+", "");

        // Verifica se o CPF possui 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Verifica se todos os dígitos são iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        // Calcula o primeiro dígito verificador
        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        }
        int primeiroDigito = 11 - (soma % 11);
        if (primeiroDigito > 9) {
            primeiroDigito = 0;
        }

        // Verifica o primeiro dígito verificador
        if (Character.getNumericValue(cpf.charAt(9)) != primeiroDigito) {
            return false;
        }

        // Calcula o segundo dígito verificador
        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        }
        int segundoDigito = 11 - (soma % 11);
        if (segundoDigito > 9) {
            segundoDigito = 0;
        }

        // Verifica o segundo dígito verificador
        if (Character.getNumericValue(cpf.charAt(10)) != segundoDigito) {
            return false;
        }

        return true; // CPF válido
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

	public Usuario login(String codigoAcesso) throws CampoInvalidoException, UsuarioInativoException {
		if (codigoAcesso == null || codigoAcesso.trim().length() < 2) {
			throw new CampoInvalidoException("Código de acesso inválido.");
		}
		return usuarioBO.login(codigoAcesso);
	}

	public String gerarPlanilha(ArrayList<Usuario> usuarios, String caminhoEscolhido) throws CampoInvalidoException {
		if (usuarios == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos!");
		}
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.geradorPlanilhaUsuario(usuarios, caminhoEscolhido);
	}
}
