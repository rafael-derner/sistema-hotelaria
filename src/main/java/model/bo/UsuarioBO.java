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
		if(!Formatador.formatarCpf(usuario.getCpf()).equals(usuarioOld.getCpf())) {
			throw new CpfAlteradoException("O CPF n�o pode ser alterado");
		}
		
		return usuarioDAO.atualizar(usuario);
	}

	public int contarTotalRegistrosComFiltros(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.contarTotalRegistrosComFiltros(usuarioSeletor);
	}

	public boolean excluir(Integer idUsuario) throws UsuarioComReservaException, ExclusaoGerenteException{
		Usuario usuario = usuarioDAO.consultarPorId(idUsuario);
		
		//NESSA ETAPA DEVE SER PEQUISADO NA TABELA DE RESERVA SE O USUÁRIO NÃO ESTÁ ASSOCIADO A ALGUMA RESERVA
		//E TAMBÉM SE O USUÁRIO NÃO É GERENTE
		//if(!usuario.getReservas().isEmpty()) {
		//	throw new UsuarioComReservaException("O usuário possui reservas associadas. Não foi possível efetuar a exclusão.");
		//}
		return usuarioDAO.excluir(idUsuario);
	}

	public List<Usuario> consultarTodos() {
		return usuarioDAO.consultarTodos();
	}

	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		return usuarioDAO.consultarComFiltro(usuarioSeletor);
	}

}
