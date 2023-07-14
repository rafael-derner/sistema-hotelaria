package model.bo;

import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import Util.Validador;
import model.dao.UsuarioDAO;
import model.exception.CampoInvalidoException;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.UsuarioComReservaException;
import model.exception.UsuarioInativoException;
import model.exception.ExclusaoGerenteException;
import model.exception.TelefoneInvalidoException;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioBO {
	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	public Usuario inserir(Usuario novoUsuario) throws CpfDuplicadoException, TelefoneInvalidoException {
		if (usuarioDAO.verificarCpfDuplicado(novoUsuario.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado já foi utilizado.");
		}
		
		if(!Validador.validarTelefone(novoUsuario.getTelefone())) {
			throw new TelefoneInvalidoException("O telefone é inválido.");
		}

		return usuarioDAO.inserir(novoUsuario);
	}

	public boolean atualizar(Usuario usuario) throws CpfAlteradoException, TelefoneInvalidoException {
		Usuario usuarioOld = usuarioDAO.consultarPorId(usuario.getIdUsuario());
		if (!Formatador.formatarCpfParaView(usuario.getCpf()).equals(usuarioOld.getCpf())) {
			throw new CpfAlteradoException("O CPF não pode ser alterado");
		}
		
		if(!Validador.validarTelefone(usuario.getTelefone())) {
			throw new TelefoneInvalidoException("O telefone é inválido.");
		}

		return usuarioDAO.atualizar(usuario);
	}

	public int contarTotalRegistrosComFiltros(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.contarTotalRegistrosComFiltros(usuarioSeletor);
	}

	public boolean inativar(Integer idUsuario) throws UsuarioInativoException {
		Usuario usuario = usuarioDAO.consultarPorId(idUsuario);

		if (!usuario.isAtivo()) {
			throw new UsuarioInativoException("O usuário já se encontra inativo.");
		}
		usuario.setCpf(Formatador.formatarCpfParaBanco(usuario.getCpf()));
		usuario.setTelefone(Formatador.formatarTelefoneMovelParaBanco(usuario.getTelefone()));

		usuario.setAtivo(false);
		return usuarioDAO.atualizar(usuario);
	}

	public List<Usuario> consultarTodos() {
		return usuarioDAO.consultarTodos();
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.consultarComFiltro(usuarioSeletor);
	}

	public Usuario login(String codigoAcesso) throws UsuarioInativoException, CampoInvalidoException {
		Usuario usuario = usuarioDAO.consultarPorCpf(codigoAcesso);
		if (usuario == null) {
			throw new CampoInvalidoException("Não foi encontrado nenhum usuário com esse código de acesso");
		}
		if (!usuario.isAtivo()) {
			throw new UsuarioInativoException("Esse usuário se encontra inativo.");
		}
		return usuario;
	}

}
