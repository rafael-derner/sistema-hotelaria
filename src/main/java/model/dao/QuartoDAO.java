package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;
import model.vo.Usuario;

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

	public List<Quarto> consultarComFiltro(QuartoSeletor quartoSeletor) {
		
		List<Quarto> quartos = new ArrayList<Quarto>();
		Connection conn = Banco.getConnection();
		
		String query = " SELECT * FROM QUARTO ";
		
		if(quartoSeletor.temFiltro()) {
			query = preencherFiltros(query, quartoSeletor);
		}
		
		if(quartoSeletor.temPaginacao()) {
			query += " LIMIT "  + quartoSeletor.getLimite()
				 + " OFFSET " + quartoSeletor.getOffset();  
		}
		
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		
		try {
			ResultSet resultado = pstmt.executeQuery();
			
			while(resultado.next()) {
				Quarto quartoBuscado = montarQuartoComResultadoDoBanco(resultado);
				quartos.add(quartoBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todos os usuarios. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return quartos;
	}

	private String preencherFiltros(String query, QuartoSeletor quartoSeletor) {
		boolean primeiro = true;
		if(quartoSeletor.getNumeroQuarto() != null && quartoSeletor.getNumeroQuarto().toString().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " NUMERO = '%" + quartoSeletor.getNumeroQuarto() + "%'";
			primeiro = false;
			
		}
		
		if(quartoSeletor.getTipoQuarto() != null && !quartoSeletor.getTipoQuarto().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " TIPO_QUARTO LIKE '%" + quartoSeletor.getTipoQuarto() + "%'";
		}
		
		return query;
	}

	public Quarto montarQuartoComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Quarto quartoBuscado = new Quarto();
		quartoBuscado.setIdQuarto(resultado.getInt("ID_QUARTO"));
		quartoBuscado.setNumeroQuarto(resultado.getInt("NUMERO"));
		quartoBuscado.setValorQuarto(resultado.getDouble("VALOR_DIARIA"));
		quartoBuscado.setTipoQuarto(resultado.getString("TIPO_QUARTO"));
		return quartoBuscado;
	}

}