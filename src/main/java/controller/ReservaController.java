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
	

	public void inserir(Reserva novaReserva) throws CampoInvalidoException {
		if(novaReserva.getHospede() == null) {
			mensagemValidacao += "\nUm hospede dever ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(novaReserva.getDtCheckIn() == null) {
			mensagemValidacao += "\nUm periodo deve ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(novaReserva.getQuarto() == null) {
			mensagemValidacao += "\nUm quarto deve ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(mensagemValidacao.trim().isEmpty()) {
			reservaBO.inserir(novaReserva);
		}
		
	}

	public ArrayList<Quarto> consultarQuartos(LocalDate dataInicio, LocalDate dataFim, String categoria) throws CampoInvalidoException {
		if(dataInicio == null) {
			mensagemValidacao += "Data dever ser preenchido";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(categoria.trim().isEmpty()) {
			mensagemValidacao += "Uma categoria deve ser selecionada";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		return reservaBO.consultaQuartos(dataInicio, dataFim, categoria);
		
	}


	
	
}
