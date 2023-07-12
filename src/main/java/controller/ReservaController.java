package controller;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonModel;

import model.bo.ReservaBO;
import model.exception.CampoInvalidoException;
import model.gerador.GeradorPlanilha;
import model.vo.Quarto;
import model.vo.Reserva;
import model.seletor.HospedeSeletor;
import model.seletor.ReservaSeletor;

public class ReservaController {
	
	ReservaBO reservaBO = new ReservaBO();
	private ArrayList<String> listaHospedes;
	String mensagemValidacao = "";
	

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

	public ArrayList<Reserva> consultarComFiltro(ReservaSeletor reservaSeletor) {
		return reservaBO.consultarComFiltro(reservaSeletor);
	}

	public int contarTotalRegistrosComFiltros(ReservaSeletor reservaSeletor) {
		return reservaBO.contarTotalRegistrosComFiltros(reservaSeletor);
	}

	public String gerarPlanilha(ArrayList<Reserva> listaReservas, String caminhoEscolhido) throws CampoInvalidoException {
		if(listaReservas == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.geradorPlanilhaReserva(listaReservas, caminhoEscolhido);
	}
	
	public Reserva inserir(Reserva novaReserva) throws CampoInvalidoException {
		this.validarCampos(novaReserva);
		return reservaBO.inserir(novaReserva);
	}
	
	public void validarCampos(Reserva reservaVO) throws CampoInvalidoException {
		if(reservaVO.getHospede() == null) {
			mensagemValidacao += "\nUm hospede dever ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(reservaVO.getDtCheckIn() == null) {
			mensagemValidacao += "\nUm periodo deve ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(reservaVO.getQuarto() == null) {
			mensagemValidacao += "\nUm quarto deve ser selecionado";
			throw new CampoInvalidoException(mensagemValidacao);
		}
		if(!mensagemValidacao.trim().isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public Boolean atualizar(Reserva reservaVO) throws CampoInvalidoException {
		validarCampos(reservaVO);
		return reservaBO.atualizar(reservaVO);
	}

	
}
