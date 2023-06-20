package model.bo;

import model.dao.QuartoDAO;
import model.exception.QuartoJaUtilizadoException;
import model.vo.Quarto;

public class QuartoBO {
	
	private QuartoDAO quartoDAO = new QuartoDAO();

	public Quarto inserir(Quarto novoQuarto) throws QuartoJaUtilizadoException {
		if(quartoDAO.verificarNumeroJaUtilizado(novoQuarto.getNumeroQuarto())) {
			throw new QuartoJaUtilizadoException("O número do quarto já foi utilizado!");
		}
		
		return quartoDAO.inserir(novoQuarto);
	}

}
