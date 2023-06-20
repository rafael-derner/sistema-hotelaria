package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.vo.Quarto;

public class QuartoDAO {

	public boolean verificarNumeroJaUtilizado(Integer numeroQuarto) {
		// TODO Auto-generated method stub
		return false;
	}

	public Quarto inserir(Quarto novoQuarto) {
		Connection conn = Banco.getConnection();
		String query = "INSERT INTO QUARTO(NUMERO, VALOR_DIARIA, TIPO_QUARTO) VALUES (?, ?, ?)";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, novoQuarto.getNumeroQuarto());
			pstmt.setDouble(2, novoQuarto.getValorQuarto());
			pstmt.setString(3, novoQuarto.getTipoQuarto());
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				novoQuarto.setIdQuarto(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no m�todo inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novoQuarto;
	}

}
