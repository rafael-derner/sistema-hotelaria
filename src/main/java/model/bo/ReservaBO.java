package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.dao.HospedeDAO;
import model.dao.ReservaDAO;
import model.seletor.ReservaSeletor;
import model.vo.Quarto;
import model.vo.Reserva;

public class ReservaBO {
	
	HospedeDAO hospedeDAO = new HospedeDAO();
	ReservaDAO reservaDAO = new ReservaDAO();
	

	public ArrayList<Quarto> consultaQuartos(LocalDate dataInicio, LocalDate dataFim, String categoria) {
		return reservaDAO.consultaQuartosVagos(dataInicio, dataFim, categoria);
	}

	public Reserva inserir(Reserva novaReserva) {
		return reservaDAO.inserir(novaReserva);
	}

	public ArrayList<Reserva> consultarComFiltro(ReservaSeletor reservaSeletor) {
		return reservaDAO.consultarComFiltro(reservaSeletor);
	}

	public int contarTotalRegistrosComFiltros(ReservaSeletor reservaSeletor) {
		return reservaDAO.contarTotalRegistrosComFiltros(reservaSeletor);
	}
	
	public Boolean atualizar(Reserva reservaVO) {
		return reservaDAO.atualizar(reservaVO);
	}

}
