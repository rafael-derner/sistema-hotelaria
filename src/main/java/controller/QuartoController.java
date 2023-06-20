package controller;

import model.bo.QuartoBO;
import model.exception.CampoInvalidoException;
import model.exception.QuartoJaUtilizadoException;
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
			mensagemValidacao += "Preencher campo valor";
		}
		
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
		
	}

	public void atualizar(Quarto quartoVO) throws QuartoJaUtilizadoException{
		// TODO Auto-generated method stub
		
	}

}
