package controller;

import java.util.List;

import model.bo.HospedeBO;
import model.exception.CampoInvalidoException;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.ExclusaoGerenteException;
import model.seletor.HospedeSeletor;
import model.vo.Hospede;

public class HospedeController {
	private HospedeBO hospedeBO = new HospedeBO();
	
	public Hospede inserir(Hospede novoHospede) throws CampoInvalidoException, CpfDuplicadoException{
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

	private void validarCamposObrigatorios(Hospede novoHospede) throws CampoInvalidoException{
		String mensagemValidacao = "";
		
		if(novoHospede.getNome() == null || novoHospede.getNome().trim().length() < 2) {
			mensagemValidacao += "Nome inv�lido \n";
		}
		
		if(novoHospede.getCpf() == null || novoHospede.getCpf().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}
		
		if(novoHospede.getTelefone() == null || novoHospede.getTelefone().trim().length() < 2) {
			mensagemValidacao += "Telefone inv�lido \n";
		}
		
		if(!mensagemValidacao.isEmpty()) {
			throw new CampoInvalidoException(mensagemValidacao);
		}
	}

	public List<Hospede> consultarComFiltro(HospedeSeletor HospedeSeletor) {
		return hospedeBO.consultarComFiltro(HospedeSeletor);
	}
}
