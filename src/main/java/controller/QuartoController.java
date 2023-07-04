package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.QuartoBO;
import model.exception.CampoInvalidoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoInativoException;
import model.exception.QuartoJaUtilizadoException;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;

public class QuartoController {
	private QuartoBO quartoBO = new QuartoBO();
	

	public Quarto inserir(Quarto novoQuarto) throws QuartoJaUtilizadoException, CampoInvalidoException {
		this.validarCamposObrigatorios(novoQuarto);
		return quartoBO.inserir(novoQuarto);
	}

	private void validarCamposObrigatorios(Quarto novoQuarto) throws CampoInvalidoException {
String mensagemValidacao = "";
		
		if(novoQuarto.getNumeroQuarto() == null) {
			mensagemValidacao += "Preencher campo número!";
		}
		
		if(novoQuarto.getValorQuarto() == null) {
			mensagemValidacao += "Preencher campo valor.";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}

	public boolean atualizar(Quarto quartoVO) throws CampoInvalidoException, QuartoJaUtilizadoException{
		this.validarCamposObrigatorios(quartoVO);
		return quartoBO.atualizar(quartoVO);
	}

	public List<Quarto> consultarComFiltro(QuartoSeletor quartoSeletor) {
		return quartoBO.consultarComFiltro(quartoSeletor);
		
	}

	public boolean inativar(Integer idQuarto) throws QuartoComReservaException, QuartoInativoException{
		// TODO Auto-generated method stub
		return quartoBO.inativar(idQuarto);
	}

	public ArrayList<Quarto> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
