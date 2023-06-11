package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Usuario;

public class UsuarioDAO {

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
			System.out.println("Ocorreu um erro no método verificarCpfDuplicado. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return cpfDuplicado;
	}

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
			System.out.println("Ocorreu um erro no método inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novoUsuario;
	}

}
