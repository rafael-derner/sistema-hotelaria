package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.vo.Quarto;
import model.vo.telefonia.Cliente;

public class ReservaDAO {

	public ArrayList<Quarto> ConsultaQuartosVagos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		Connection conexao = Banco.getConnection();
		String sql = " select * from quarto join reserva on quarto.idquarto = reserva.idquarto "
				   + " where quarto. ";
			
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);
		try {
			query.setInt(1, id);
			ResultSet resultado = query.executeQuery();
				
			if(resultado.next()) {
				clienteBuscado = montarClienteComResultadoDoBanco(resultado);
			}
				
		}catch (Exception e) {
			System.out.println("Erro ao buscar cliente com id: " + id 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}
		
		return listaQuartos;
	}

}
