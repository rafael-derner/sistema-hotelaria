package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import model.vo.Quarto;
import model.vo.Reserva;
import java.sql.Date;

public class ReservaDAO {

	public ArrayList<Quarto> consultaQuartosVagos(LocalDate dataInicio, LocalDate dataFim, String categoria) {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		Connection conexao = Banco.getConnection();
		QuartoDAO quartoDAO = new QuartoDAO();
		String sql = "select distinct(quarto.id_quarto), quarto.* "
				+ "from quarto "
				+ "left join reserva on reserva.id_quarto = quarto.id_quarto "
				+ "and ((' " + dataInicio + "' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT) "
				+ "and ('" + dataFim + "' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT))"
			+ "where "
				+ " quarto.tipo_quarto = '" + categoria + "'";
				
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while(resultado.next()) {
				listaQuartos.add(quartoDAO.montarQuartoComResultadoDoBanco(resultado));
			}
				
		}catch (Exception e) {
			System.out.println("Erro ao buscar a lista de quartos" 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaQuartos;
	}

	public void inserir(Reserva novaReserva) {
		Connection conn = Banco.getConnection();
		String query = "INSERT INTO reserva(ID_hospede,id_quarto, id_usuario, DTHR_CHECK_IN, DTHR_CHECK_OUT) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			
			pstmt.setInt(1, novaReserva.getHospede().getIdHospede());
			pstmt.setInt(2, novaReserva.getQuarto().getIdQuarto());
			pstmt.setInt(3, 1); // novaReserva.getUsuario().getIdUsuario());
			pstmt.setDate(4, java.sql.Date.valueOf(novaReserva.getDtCheckIn()));
			pstmt.setDate(5, java.sql.Date.valueOf(novaReserva.getDtCheckOut()));
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				System.out.println("Não inseriu");
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no método inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
	}

}
