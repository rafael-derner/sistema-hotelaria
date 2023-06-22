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
	
	public ArrayList<String> buscarHospedePorNome(String nomeHospede) {
		ArrayList<String> listaNomesHospede = new ArrayList<String>();
		listaNomesHospede = hospedeDAO.buscarHospedePorNome(nomeHospede);
		return listaNomesHospede;
	}

	public ArrayList<Quarto> consultaQuartos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		listaQuartos = reservaDAO.ConsultaQuartosVagos(dataInicio, dataFim, categoria);
		return listaQuartos;
	}

}
