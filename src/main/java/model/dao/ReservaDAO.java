package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonModel;

import Util.Formatador;
import model.seletor.HospedeSeletor;
import model.seletor.ReservaSeletor;
import model.vo.Hospede;
import model.vo.Quarto;
import model.vo.Reserva;
import java.sql.Date;

public class ReservaDAO {

	public ArrayList<Quarto> consultaQuartosVagos(LocalDate dataInicio, LocalDate dataFim, String categoria) {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		Connection conexao = Banco.getConnection();
		QuartoDAO quartoDAO = new QuartoDAO();
		String sql = "select distinct(quarto.id_quarto), quarto.* " + "from quarto "
				+ "left join reserva on reserva.id_quarto = quarto.id_quarto " + "and ((' " + dataInicio
				+ "' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT) " + "and ('" + dataFim
				+ "' not between reserva.DTHR_CHECK_IN and reserva.DTHR_CHECK_OUT))" + "where "
				+ " quarto.tipo_quarto = '" + categoria + "'";

		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			ResultSet resultado = query.executeQuery();
			while (resultado.next()) {
				listaQuartos.add(quartoDAO.montarQuartoComResultadoDoBanco(resultado));
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar a lista de quartos" + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		return listaQuartos;
	}

	public Reserva inserir(Reserva novaReserva) {
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
			if (resultado.next()) {
				novaReserva.setIdReserva(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no método inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novaReserva;
	}

	public ArrayList<Reserva> consultarComFiltro(ReservaSeletor reservaSeletor) {
		ArrayList<Reserva> listaReservas = new ArrayList<Reserva>();
		Connection conn = Banco.getConnection();
		String query = "SELECT RESERVA.ID_RESERVA,RESERVA.ID_HOSPEDE,RESERVA.ID_QUARTO, HOSPEDE.NOME, QUARTO.NUMERO, RESERVA.DTHR_CHECK_IN, RESERVA.DTHR_CHECK_OUT, (SELECT DATEDIFF(RESERVA.DTHR_CHECK_IN, RESERVA.DTHR_CHECK_OUT) * QUARTO.VALOR_DIARIA) AS VALOR_TOTAL"
				+ " FROM RESERVA" + "	JOIN QUARTO ON RESERVA.ID_QUARTO = QUARTO.iD_QUARTO"
				+ "    JOIN HOSPEDE ON RESERVA.ID_HOSPEDE = HOSPEDE.ID_HOSPEDE";

		if (reservaSeletor.temFiltro()) {
			query = preencherFiltros(query, reservaSeletor);
		}

		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet resultado = pstmt.executeQuery();

			while (resultado.next()) {
				HospedeDAO hospedeDAO = new HospedeDAO();
				QuartoDAO quartoDAO = new QuartoDAO();
				Hospede hospede = hospedeDAO.consultarPorId(resultado.getInt(2));
				Quarto quarto = quartoDAO.consultarPorId(resultado.getInt(3));
				Reserva reservaBanco = montarReservaComResultadoDoBanco(resultado, hospede, quarto);
				listaReservas.add(reservaBanco);
			}

		} catch (Exception e) {
			System.out.println("Erro ao buscar reserva. \n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return listaReservas;
	}

	private Reserva montarReservaComResultadoDoBanco(ResultSet resultado, Hospede hospede, Quarto quarto)
			throws SQLException {
		Reserva reservaBanco = new Reserva();
		reservaBanco.setIdReserva(resultado.getInt("RESERVA.ID_RESERVA"));
		reservaBanco.setHospede(hospede);
		reservaBanco.setQuarto(quarto);
		reservaBanco.setDtCheckIn(java.time.LocalDate.parse(resultado.getString("RESERVA.DTHR_CHECK_IN")));
		reservaBanco.setDtCheckOut(java.time.LocalDate.parse(resultado.getString(("RESERVA.DTHR_CHECK_OUT"))));

		return reservaBanco;
	}

	private String preencherFiltros(String query, ReservaSeletor reservaSeletor) {

		boolean primeiro = true;
		if (reservaSeletor.getNomeHospede() != null && !reservaSeletor.getNomeHospede().trim().isEmpty()) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}

			query += " HOSPEDE.NOME LIKE '%" + reservaSeletor.getNomeHospede() + "%'";
			primeiro = false;
		}

		if (reservaSeletor.getNumQuarto() > 0) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " QUARTO.NUMERO = '" + reservaSeletor.getNumQuarto() + "'";
			primeiro = false;
		}

		if (reservaSeletor.getDataEntrada() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " RESERVA.DTHR_CHECK_IN = '" + reservaSeletor.getDataEntrada() + "'";
			primeiro = false;
		}

		if (reservaSeletor.getDataSaida() != null) {
			if (primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " RESERVA.DTHR_CHECK_OUT = '" + reservaSeletor.getDataSaida() + "'";
			primeiro = false;
		}

		return query;
	}

	public int contarTotalRegistrosComFiltros(ReservaSeletor reservaSeletor) {
		int total = 0;
		Connection conn = Banco.getConnection();
		String query = " SELECT COUNT(*) FROM RESERVA ";

		if (reservaSeletor.temFiltro()) {
			query = preencherFiltros(query, reservaSeletor);
		}

		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet resultado = pstmt.executeQuery();

			if (resultado.next()) {
				total = resultado.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("Erro contar o total de reservas" + "\n Causa:" + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}

		return total;
	}

	public Boolean atualizar(Reserva reservaVO) {
		int registroAlterado = 0;
		Connection conn = Banco.getConnection();
		String query = "UPDATE RESERVA SET ID_HOSPEDE = ?, ID_QUARTO = ?, ID_USUARIO = ?, DTHR_CHECK_IN = ?, DTHR_CHECK_OUT = ? WHERE ID_RESERVA = ?";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setInt(1, reservaVO.getHospede().getIdHospede());
			pstmt.setInt(2, reservaVO.getQuarto().getIdQuarto());
			pstmt.setInt(3, reservaVO.getUsuario().getIdUsuario());
			pstmt.setDate(4, java.sql.Date.valueOf(reservaVO.getDtCheckIn()));
			pstmt.setDate(5, java.sql.Date.valueOf(reservaVO.getDtCheckOut()));
			pstmt.setInt(6, reservaVO.getIdReserva());
			registroAlterado = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no m�todo atualizar. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return registroAlterado > 0;
	}

}
