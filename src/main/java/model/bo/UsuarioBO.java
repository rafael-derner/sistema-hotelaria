package model.bo;

import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.dao.UsuarioDAO;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.UsuarioComReservaException;
import model.exception.ExclusaoGerenteException;
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

	public boolean atualizar(Usuario usuario) throws CpfAlteradoException {
		Usuario usuarioOld = usuarioDAO.consultarPorId(usuario.getIdUsuario());
		if(!Formatador.formatarCpfParaView(usuario.getCpf()).equals(usuarioOld.getCpf())) {
			throw new CpfAlteradoException("O CPF n�o pode ser alterado");
		}
		
		return usuarioDAO.atualizar(usuario);
	}

	public int contarTotalRegistrosComFiltros(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.contarTotalRegistrosComFiltros(usuarioSeletor);
	}

	public boolean inativar(Integer idUsuario) throws UsuarioComReservaException{
		Usuario usuario = usuarioDAO.consultarPorId(idUsuario);
		usuario.setCpf(Formatador.formatarCpfParaBanco(usuario.getCpf()));
		usuario.setTelefone(Formatador.formatarTelefoneMovelParaBanco(usuario.getTelefone()));
		
		//NESSA ETAPA DEVE SER PEQUISADO NA TABELA DE RESERVA SE O USUÁRIO NÃO ESTÁ ASSOCIADO A ALGUMA RESERVA ATIVA
		// Utilizar o seletor de reservas para pesquisar se existe alguma reserva com o Usuario

		usuario.setAtivo(false);
		return usuarioDAO.atualizar(usuario);
	}

	public List<Usuario> consultarTodos() {
		return usuarioDAO.consultarTodos();
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.consultarComFiltro(usuarioSeletor);
	}

}
