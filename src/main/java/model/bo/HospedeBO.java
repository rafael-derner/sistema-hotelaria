package model.bo;

import java.util.List;

import Util.Formatador;
import Util.Validador;
import model.dao.HospedeDAO;
import model.exception.CpfAlteradoException;
import model.exception.CpfDuplicadoException;
import model.exception.ExclusaoGerenteException;
import model.exception.TelefoneInvalidoException;
import model.seletor.HospedeSeletor;
import model.seletor.UsuarioSeletor;
import model.vo.Hospede;

public class HospedeBO {
	private HospedeDAO hospedeDAO = new HospedeDAO();

	public Hospede inserir(Hospede novoHospede) throws CpfDuplicadoException, TelefoneInvalidoException {
		if (hospedeDAO.verificarCpfDuplicado(novoHospede.getCpf())) {
			throw new CpfDuplicadoException("O CPF informado j� foi utilizado.");
		}
		
		if(!Validador.validarTelefone(novoHospede.getTelefone())) {
			throw new TelefoneInvalidoException("O telefone é inválido.");
		}

		return hospedeDAO.inserir(novoHospede);
	}

	public boolean atualizar(Hospede hospede) throws CpfAlteradoException, TelefoneInvalidoException {
		Hospede hospedeOld = hospedeDAO.consultarPorId(hospede.getIdHospede());
		if (!Formatador.formatarCpfParaView(hospede.getCpf()).equals(hospedeOld.getCpf())) {
			throw new CpfAlteradoException("O CPF n�o pode ser alterado");
		}
		
		if(!Validador.validarTelefone(hospede.getTelefone())) {
			throw new TelefoneInvalidoException("O telefone é inválido.");
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

	public int contarTotalRegistrosComFiltros(HospedeSeletor hospedeSeletor) {
		return hospedeDAO.contarTotalRegistrosComFiltros(hospedeSeletor);
	}

}
