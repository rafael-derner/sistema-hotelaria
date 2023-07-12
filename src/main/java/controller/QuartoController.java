package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.QuartoBO;
import model.exception.CampoInvalidoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoInativoException;
import model.exception.QuartoJaUtilizadoException;
import model.gerador.GeradorPlanilha;
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

		if (novoQuarto.getNumeroQuarto() == null) {
			mensagemValidacao += "Preencher campo número!";
		}

		if (novoQuarto.getValorQuarto() == null) {
			mensagemValidacao += "Preencher campo valor.";
		}

		if (novoQuarto.getTipoQuarto() == null) {
			mensagemValidacao += "Preencher campo Tipo de Quarto.";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}

	}

	public boolean atualizar(Quarto quartoAlterado) throws CampoInvalidoException, QuartoJaUtilizadoException {
		this.validarCamposObrigatorios(quartoAlterado);
		return quartoBO.atualizar(quartoAlterado);
	}

	public List<Quarto> consultarComFiltro(QuartoSeletor quartoSeletor) {
		return quartoBO.consultarComFiltro(quartoSeletor);

	}

	public boolean inativar(Integer idQuarto) throws QuartoComReservaException, QuartoInativoException {
		// TODO Auto-generated method stub
		return quartoBO.inativar(idQuarto);
	}

	public ArrayList<Quarto> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	public String gerarPlanilha(ArrayList<Quarto> quartos, String destinoArquivo) throws CampoInvalidoException {

		if (quartos == null || destinoArquivo == null || destinoArquivo.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos");
		}

		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.geradorPlanilhaQuarto(quartos, destinoArquivo);
	}

	public int contarTotalRegistrosComFiltros(QuartoSeletor quartoSeletor) {
		return quartoBO.contarTotalRegistrosComFiltros(quartoSeletor);
	}

}
