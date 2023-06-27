package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.bo.ReservaBO;
import model.exception.CampoInvalidoException;
import model.vo.Quarto;
import model.vo.Reserva;
import model.seletor.HospedeSeletor;

public class ReservaController {
	
	ReservaBO reservaBO = new ReservaBO();
	private ArrayList<String> listaHospedes;
	String mensagemValidacao = "";
	

	public void inserir(Reserva novaReserva) {
		
		
	}

	public ArrayList<Quarto> consultarQuartos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) throws CampoInvalidoException {
		if(dataInicio == null) {
			mensagemValidacao += "Data dever ser preenchido";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		return reservaBO.consultaQuartos(dataInicio, dataFim, categoria);
		
	}


	
	
}
