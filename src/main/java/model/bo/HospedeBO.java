package model.bo;

import java.util.List;

import Util.Formatador;
import model.dao.HospedeDAO;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.ExclusaoGerenteException;
import model.seletor.HospedeSeletor;
import model.vo.Hospede;

public class HospedeBO {
	private HospedeDAO hospedeDAO = new HospedeDAO();

	public Hospede inserir(Hospede novoHospede) throws CpfDuplicadoException{
		if(hospedeDAO.verificarCpfDuplicado(novoHospede.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado j� foi utilizado.");
		}
		
		return hospedeDAO.inserir(novoHospede);
	}

	public boolean atualizar(Hospede hospede) throws CpfAlteradoException {
		Hospede hospedeOld = hospedeDAO.consultarPorId(hospede.getIdHospede());
		if(!Formatador.formatarCpfParaView(hospede.getCpf()).equals(hospedeOld.getCpf())) {
			throw new CpfAlteradoException("O CPF n�o pode ser alterado");
		}
		
		return hospedeDAO.atualizar(hospede);
	}

	public boolean excluir(Hospede hospede) throws ExclusaoGerenteException {
		// COMPLETAR MÉTODO
		return false;
	}

	public List<Hospede> consultarComFiltro(HospedeSeletor hospedeSeletor) {
		return hospedeDAO.consultarComFiltro(hospedeSeletor);
	}

}
