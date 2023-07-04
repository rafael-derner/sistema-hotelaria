package model.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import Util.Formatador;
import model.vo.Quarto;

public class GeradorPlanilha {

	public String geradorPlanilhaQuarto(ArrayList<Quarto> quartos, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Quartos");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(0);
		linhaCabecalho.createCell(0).setCellValue("Número");
		linhaCabecalho.createCell(1).setCellValue("Tipo de Quarto");
		linhaCabecalho.createCell(2).setCellValue("Valor da diária");
		linhaCabecalho.createCell(3).setCellValue("Ativo?");
		
		int contadorLinhas = 1;
		for(Quarto q: quartos) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(q.getNumeroQuarto());
			novaLinha.createCell(1).setCellValue(q.getTipoQuarto());
			novaLinha.createCell(2).setCellValue(Formatador.formatarValorQuartoParaView(q.getValorQuarto()));
			novaLinha.createCell(3).setCellValue(q.isAtivo() ? "Sim" : "Não");
			contadorLinhas++;
			
		}
		
		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}

	private String salvarNoDisco(HSSFWorkbook planilha, String caminhoArquivo) {
		// TODO Auto-generated method stub
		String mensagem = "";
		FileOutputStream saida = null;
		String extensao = ".xls";
		
		try {
			saida = new FileOutputStream(new File(caminhoArquivo + extensao));
			planilha.write(saida);
			mensagem = "Planilha gerada com sucesso";
		}catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar planilha (sem acesso) : " + caminhoArquivo + extensao;
		}catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar planilha em : " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		}finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				}catch (IOException e) {
					mensagem = "Erro ao tentar salvar planilha em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}
		
		return mensagem;
	}

}
