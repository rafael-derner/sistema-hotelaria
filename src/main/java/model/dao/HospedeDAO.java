package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.seletor.HospedeSeletor;
import model.seletor.UsuarioSeletor;
import model.vo.Hospede;

public class HospedeDAO {

	/*
	 * INSERIR NOVO USU�RIO
	 */
	public Hospede inserir(Hospede novohospede) {
		Connection conn = Banco.getConnection();
		String query = "INSERT INTO hospede(NOME, CPF, TELEFONE) VALUES (?, ?, ?)";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, novohospede.getNome());
			pstmt.setString(2, novohospede.getCpf());
			pstmt.setString(3, novohospede.getTelefone());
			pstmt.execute();
			
			ResultSet resultado = pstmt.getGeneratedKeys();
			if(resultado.next()) {
				novohospede.setIdHospede(resultado.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro no m�todo inserir. \n Causa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return novohospede;
	}

	/*
	 * ATUALIZAR USU�RIO EXISTENTE
	 */
	public boolean atualizar(Hospede hospede) {
		int registroAlterado = 0;
		Connection conn = Banco.getConnection();
		String query = "UPDATE HOSPEDE SET NOME = ?, CPF = ?, TELEFONE = ? WHERE ID_hospede = ?";
		PreparedStatement pstmt = Banco.getPreparedStatementWithPk(conn, query);
		try {
			pstmt.setString(1, hospede.getNome());
			pstmt.setString(2, hospede.getCpf());
			pstmt.setString(3, hospede.getTelefone());
			pstmt.setInt(4, hospede.getIdHospede());
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
	public Hospede consultarPorId(Integer idHospede) {
		Hospede hospede = null;
		Connection conn = Banco.getConnection();
		String query = " SELECT * FROM HOSPEDE WHERE ID_hospede = ? ";
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			pstmt.setInt(1, idHospede);
			ResultSet resultado = pstmt.executeQuery();
			if(resultado.next()) {
				hospede = montarhospedeComResultadoDoBanco(resultado);
			}	
		}catch (Exception e) {
			System.out.println("Erro ao buscar hospede com id: " + idHospede 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		return hospede;
	}
	
	/*
	 * CONSULTAR REGISTROS POR FILTRO
	 */
	public List<Hospede> consultarComFiltro(HospedeSeletor hospedeSeletor) {
		List<Hospede> hospedes = new ArrayList<Hospede>();
		Connection conn = Banco.getConnection();
		String query = " SELECT * FROM hospede ";
		
		if(hospedeSeletor.temFiltro()) {
			query = preencherFiltros(query, hospedeSeletor);
		}
		
		if(hospedeSeletor.temPaginacao()) {
			query += " LIMIT "  + hospedeSeletor.getLimite()
				 + " OFFSET " + hospedeSeletor.getOffset();  
		}
		
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet resultado = pstmt.executeQuery();
			
			while(resultado.next()) {
				Hospede hospedeBuscado = montarhospedeComResultadoDoBanco(resultado);
				hospedes.add(hospedeBuscado);
			}
			
		}catch (Exception e) {
			System.out.println("Erro ao buscar todos os hospedes. \n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return hospedes;
	}


	/*
	 * VERIFICAR EXISTENTICA DE REGISTRO COM CPF
	 */
	public boolean verificarCpfDuplicado(String cpf) {
		boolean cpfDuplicado = false;
		Connection conn = Banco.getConnection();
		String query = "SELECT COUNT(*) FROM HOSPEDE WHERE CPF = ?";
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
	private String preencherFiltros(String query, HospedeSeletor hospedeSeletor) {
		
		boolean primeiro = true;
		if(hospedeSeletor.getNome() != null && !hospedeSeletor.getNome().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			
			query += " nome LIKE '%" + hospedeSeletor.getNome() + "%'";
			primeiro = false;
		}
		
		if(hospedeSeletor.getCpf() != null && !hospedeSeletor.getCpf().trim().isEmpty()) {
			if(primeiro) {
				query += " WHERE ";
			} else {
				query += " AND ";
			}
			query += " cpf LIKE '%" + hospedeSeletor.getCpf() + "%'";
			primeiro = false;
		}
		
		return query;
	}
	
	/*
	 * MONTAR OBJETO DE HOSPEDE COM RESULTADO DA QUERY
	 */
	private Hospede montarhospedeComResultadoDoBanco(ResultSet resultado) throws SQLException {
		Hospede hospedeBuscado = new Hospede();
		hospedeBuscado.setIdHospede(resultado.getInt("ID_HOSPEDE"));
		hospedeBuscado.setNome(resultado.getString("NOME"));
		hospedeBuscado.setCpf(Formatador.formatarCpfParaView(resultado.getString("CPF")));
		hospedeBuscado.setTelefone(Formatador.formatarTelefoneMovelParaView(resultado.getString("TELEFONE")));
		
		return hospedeBuscado;
	}

	public int contarTotalRegistrosComFiltros(HospedeSeletor hospedeSeletor) {
		int total = 0;
		Connection conn = Banco.getConnection();
		String query = " SELECT COUNT(*) FROM HOSPEDE ";
		
		if(hospedeSeletor.temFiltro()) {
			query = preencherFiltros(query, hospedeSeletor);
		}
		
		PreparedStatement pstmt = Banco.getPreparedStatement(conn, query);
		try {
			ResultSet resultado = pstmt.executeQuery();
			
			if(resultado.next()) {
				total = resultado.getInt(1);
			}
		}catch (Exception e) {
			System.out.println("Erro contar o total de hóspedes" 
					+ "\n Causa:" + e.getMessage());
		}finally {
			Banco.closePreparedStatement(pstmt);
			Banco.closeConnection(conn);
		}
		
		return total;
	}

}
