package controller;

import java.util.ArrayList;

import model.bo.ReservaBO;
import model.exception.CampoInvalidoException;
import model.vo.Reserva;


public class ReservaController {
	ReservaBO reservaBO = new ReservaBO();
	private ArrayList<String> listaHospedes;
	
	public ArrayList<String> buscarHospedePorNome(String nomeHospede) throws CampoInvalidoException {
		String mensagemValidacao = "";
		
		if(nomeHospede == null || nomeHospede.trim().length() < 2) {
			mensagemValidacao += "Nome dever ser preenchido";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
		return listaHospedes = reservaBO.buscarHospedePorNome(nomeHospede);
	}

	public void inserir(Reserva novaReserva) {
		
		
	}
	
	
}
