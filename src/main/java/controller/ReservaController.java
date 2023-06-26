package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.bo.ReservaBO;
import model.exception.CampoInvalidoException;
import model.vo.Quarto;
import model.vo.Reserva;


public class ReservaController {
	
	ReservaBO reservaBO = new ReservaBO();
	private ArrayList<String> listaHospedes;
	String mensagemValidacao = "";
	
	public ArrayList<String> buscarHospedePorNome(String nomeHospede) throws CampoInvalidoException {
		
		
		if(nomeHospede == null || nomeHospede.trim().length() < 2) {
			mensagemValidacao += "Nome dever ser preenchido";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		return listaHospedes = reservaBO.buscarHospedePorNome(nomeHospede);
	}

	public void inserir(Reserva novaReserva) {
		
		
	}

	public ArrayList<Quarto> consultarQuartos(LocalDate dataInicio, LocalDate dataFim, ButtonModel categoria) throws CampoInvalidoException {
		ArrayList<Quarto> listaQuartos = new ArrayList<Quarto>();
		if(dataInicio == null) {
			mensagemValidacao += "Data dever ser preenchido";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		listaQuartos = reservaBO.consultaQuartos(dataInicio, dataFim, categoria);
		
		return listaQuartos;
		
	}


	
	
}
