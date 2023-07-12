package controller;

import java.util.ArrayList;
import java.util.List;

import model.bo.HospedeBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.ExclusaoGerenteException;
import model.gerador.GeradorPlanilha;
import model.seletor.HospedeSeletor;
import model.seletor.UsuarioSeletor;
import model.vo.Hospede;

public class HospedeController {
	private HospedeBO hospedeBO = new HospedeBO();

	public Hospede inserir(Hospede novoHospede) throws CampoInvalidoException, CpfDuplicadoException {
		this.validarCamposObrigatorios(novoHospede);
		return hospedeBO.inserir(novoHospede);
	}

	public boolean atualizar(Hospede Hospede) throws CampoInvalidoException, CpfAlteradoException {
		this.validarCamposObrigatorios(Hospede);
		return hospedeBO.atualizar(Hospede);
	}

	public boolean excluir(Hospede Hospede) throws ExclusaoGerenteException {
		return hospedeBO.excluir(Hospede);
	}

	private void validarCamposObrigatorios(Hospede novoHospede) throws CampoInvalidoException {
		String mensagemValidacao = "";

		if (novoHospede.getNome() == null || novoHospede.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inv�lido \n";
		}

		if (novoHospede.getCpf() == null || novoHospede.getCpf().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}

		if (novoHospede.getTelefone() == null || novoHospede.getTelefone().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}

		if (!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public List<Hospede> consultarComFiltro(HospedeSeletor hospedeSeletor) {
		return hospedeBO.consultarComFiltro(hospedeSeletor);
	}

	public int contarTotalRegistrosComFiltros(HospedeSeletor hospedeSeletor) {
		return hospedeBO.contarTotalRegistrosComFiltros(hospedeSeletor);
	}

	public String gerarPlanilha(ArrayList<Hospede> hospedes, String caminhoEscolhido) throws CampoInvalidoException {
		if (hospedes == null || caminhoEscolhido == null || caminhoEscolhido.trim().isEmpty()) {
			throw new CampoInvalidoException("Preencha todos os campos!");
		}
		GeradorPlanilha gerador = new GeradorPlanilha();
		return gerador.geradorPlanilhaHospedes(hospedes, caminhoEscolhido);
	}
}
