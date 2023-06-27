package model.bo;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.dao.HospedeDAO;
import model.dao.ReservaDAO;
import model.vo.Quarto;

public class ReservaBO {
	
	HospedeDAO hospedeDAO = new HospedeDAO();
	ReservaDAO reservaDAO = new ReservaDAO();
	

	public ArrayList<Quarto> consultaQuartos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) {
		return reservaDAO.consultaQuartosVagos(dataInicio, dataFim, categoria);
	}

}
