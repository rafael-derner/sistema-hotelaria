package model.bo;

import java.util.ArrayList;
import java.util.List;

import Util.Formatador;
import model.dao.QuartoDAO;
import model.exception.CpfAlteradoException;
import model.exception.QuartoComReservaException;
import model.exception.QuartoJaUtilizadoException;
import model.seletor.QuartoSeletor;
import model.vo.Quarto;
import model.vo.Usuario;

public class QuartoBO {
	
	private QuartoDAO quartoDAO = new QuartoDAO();

	public Quarto inserir(Quarto novoQuarto) throws QuartoJaUtilizadoException {
		if(quartoDAO.verificarNumeroJaUtilizado(novoQuarto.getNumeroQuarto())) {
			throw new QuartoJaUtilizadoException("O número do quarto já foi utilizado.");
		}
		
		return quartoDAO.inserir(novoQuarto);
		
	}

	public List<Quarto> consultarComFiltro(QuartoSeletor quartoSeletor) {
		return quartoDAO.consultarComFiltro(quartoSeletor);
	}

	public boolean atualizar(Quarto quartoVO) {
		return quartoDAO.atualizar(quartoVO);
	}

	public boolean inativar(Integer idQuarto) throws QuartoComReservaException{
		Quarto quarto = quartoDAO.consultarPorId(idQuarto);
		return quartoDAO.atualizar(quarto);
	}

}
