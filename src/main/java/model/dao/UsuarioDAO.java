package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.seletor.UsuarioSeletor;
import model.vo.Usuario;

public class UsuarioDAO {

	/*
	 * INSERIR NOVO USU�RIO
	 */
	public Usuario inserir(Usuario novoUsuario) {
		Connection conn = Banco.getConnection();
		String query = "INSERT INTO USUARIO(NOME, CPF, TELEFONE, PERFIL) VALUES (?, ?, ?, ?)";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novoUsuario.getNome());
			pstmt.setString(2, novoUsuario.getCpf());
			pstmt.setString(3, novoUsuario.getTelefone());
			pstmt.setString(4, novoUsuario.getPerfil());
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				novoUsuario.setIdUsuario(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no m�todo inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novoUsuario;
	}

	/*
	 * ATUALIZAR USU�RIO EXISTENTE
	 */
	public boolean atualizar(Usuario usuario) {
		int registroAlterado = 0;
		Connection conn = Banco.getConnection();
		String query = "UPDATE USUARIO SET NOME = ?, CPF = ?, TELEFONE = ?, PERFIL = ? WHERE ID_USUARIO = ?";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getCpf());
			pstmt.setString(3, usuario.getTelefone());
			pstmt.setString(4, usuario.getPerfil());
			pstmt.setInt(5, usuario.getIdUsuario());
			registroAlterado = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no m�todo inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return registroAlterado > 0;
	}

	/*
	 * CONSULTAR REIGSTRO POR ID
	 */
	public Usuario consultarPorId(Integer idUsuario) {
		Usuario usuario = null;
		Connection conn = Banco.getConnection();
		String query = " SELECT * FROM USUARIO WHERE ID_USUARIO = ? ";
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setInt(1, idUsuario);
			ResultSet resultado = pstmt.executeQuery();
			if(resultado.next()) {
				usuario = montarUsuarioComResultadoDoBanco(resultado);
			}	
		}catch (Exception e) {
			System.out.println("Erro ao buscar usuario com id: " + idUsuario 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return usuario;
	}
	
	/*
	 * CONSULTAR REGISTROS POR FILTRO
	 */
	public List<Usuario> consultarComFiltro(UsuarioSeletor usuarioSeletor) {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Connection conn = Banco.getConnection();
		String query = " SELECT * FROM USUARIO ";
		
		if(usuarioSeletor.temFiltro()) {
			query = preencherFiltros(query, usuarioSeletor);
		}
		
		if(usuarioSeletor.temPaginacao()) {
			query += " LIMIT "  + usuarioSeletor.getLimite()
				 + " OFFSET " + usuarioSeletor.getOffset();  
		}
		
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet resultado = pstmt.executeQuery();
			
			while(resultado.next()) {
				Usuario usuarioBuscado = montarUsuarioComResultadoDoBanco(resultado);
				usuarios.add(usuarioBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todos os usuarios. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return usuarios;
	}


	/*
	 * VERIFICAR EXISTENTICA DE REGISTRO COM CPF
	 */
	public boolean verificarCpfDuplicado(String cpf) {
		boolean cpfDuplicado = false;
		Connection conn = Banco.getConnection();
		String query = "SELECT COUNT(*) FROM USUARIO WHERE CPF = ?";
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setString(1, cpf);
			ResultSet resultado = pstmt.executeQuery();
			
			if(resultado.next()) {
				cpfDuplicado = resultado.getInt(1) > 0;
			}
		} catch (Exception e) {
			System.out.println("Ocorreu um erro no m�todo verificarCpfDuplicado. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return cpfDuplicado;
	}
	
	/*
	 * COMPLETAR QUERY COM FILTROS DO USU�RIO
	 */
	private String preencherFiltros(String query, UsuarioSeletor usuarioSeletor) {
		
		boolean primeiro = true;
		if(usuarioSeletor.getNome() != null && !usuarioSeletor.getNome().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			
			query += " nome LIKE '%" + usuarioSeletor.getNome() + "%'";
			primeiro = false;
		}
		
		if(usuarioSeletor.getCpf() != null && !usuarioSeletor.getCpf().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " cpf LIKE '%" + usuarioSeletor.getCpf() + "%'";
			primeiro = false;
		}
		
		if(usuarioSeletor.getPerfil() != null && !usuarioSeletor.getPerfil().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " perfil LIKE '%" + usuarioSeletor.getPerfil() + "%'";
		}
		
		return query;
	}
	
	/*
	 * MONTAR OBJETO DE USUARIO COM RESULTADO DA QUERY
	 */
	private Usuario montarUsuarioComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Usuario usuarioBuscado = new Usuario();
		usuarioBuscado.setIdUsuario(resultado.getInt("ID_USUARIO"));
		usuarioBuscado.setNome(resultado.getString("NOME"));
		usuarioBuscado.setCpf(Formatador.formatarCpf(resultado.getString("CPF")));
		usuarioBuscado.setTelefone(Formatador.formatarTelefoneMovel(resultado.getString("TELEFONE")));
		usuarioBuscado.setPerfil(resultado.getString("PERFIL"));
		
		return usuarioBuscado;
	}


}
