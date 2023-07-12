package model.bo;

import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.dao.QuartoDAO;
import model.exception.CpfAlteradoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoInativoException;
import model.exception.QuartoJaUtilizadoException;
import model.exception.UsuarioInativoException;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;
import model.vo.Usuario;

public class QuartoBO {

	private QuartoDAO quartoDAO = new QuartoDAO();

	public Quarto inserir(Quarto novoQuarto) throws QuartoJaUtilizadoException {
		if (quartoDAO.verificarNumeroJaUtilizado(novoQuarto.getNumeroQuarto())) {
			throw new QuartoJaUtilizadoException("O número do quarto já foi utilizado.");
		}

		return quartoDAO.inserir(novoQuarto);

	}

	public List<Quarto> consultarComFiltro(QuartoSeletor quartoSeletor) {
		return quartoDAO.consultarComFiltro(quartoSeletor);
	}

	public boolean atualizar(Quarto quartoAlterado) {
		return quartoDAO.atualizar(quartoAlterado);
	}

	public boolean inativar(Integer idQuarto) throws QuartoComReservaException, QuartoInativoException {
		Quarto quarto = quartoDAO.consultarPorId(idQuarto);

		if (!quarto.isAtivo()) {
			throw new QuartoInativoException("O quarto já se encontra inativo.");
		}

		quarto.setAtivo(false);
		return quartoDAO.atualizar(quarto);
	}

	public int contarTotalRegistrosComFiltros(QuartoSeletor quartoSeletor) {
		return quartoDAO.contarTotalRegistrosComFiltros(quartoSeletor);
	}

}
