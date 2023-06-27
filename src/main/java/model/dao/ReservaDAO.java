package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.ButtonModel;
import model.vo.Quarto;

public class ReservaDAO {

	public ArrayList<Quarto> consultaQuartosVagos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		Connection conexao = Banco.getConnection();
		QuartoDAO quartoDAO = new QuartoDAO();
		String sql = "select quarto.* from quarto join reserva on reserva.id_quarto = quarto.id_quarto "
				+ "where reserva.dthr_check_out < '" + dataInicio + "' and reserva.dthr_check_in > '" + dataFim + "'"
				+ " and quarto.tipo_quarto = '" + categoria + "'";
				
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

}
