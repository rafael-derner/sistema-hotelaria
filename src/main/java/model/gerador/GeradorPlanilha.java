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
import model.vo.Hospede;
import model.vo.Quarto;
import model.vo.Reserva;
import model.vo.Usuario;

public class GeradorPlanilha {

	public String geradorPlanilhaQuarto(ArrayList<Quarto> quartos, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Quartos");

		HSSFRow linhaTitulo = abaPlanilha.createRow(0);
		linhaTitulo.createCell(0).setCellValue("Relatório de Quartos");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(1);
		linhaCabecalho.createCell(0).setCellValue("ID");
		linhaCabecalho.createCell(1).setCellValue("Número");
		linhaCabecalho.createCell(2).setCellValue("Tipo de Quarto");
		linhaCabecalho.createCell(3).setCellValue("Valor da diária");
		linhaCabecalho.createCell(4).setCellValue("Ativo?");

		int contadorLinhas = 2;
		for (Quarto q : quartos) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(q.getIdQuarto());
			novaLinha.createCell(1).setCellValue(q.getNumeroQuarto());
			novaLinha.createCell(2).setCellValue(q.getTipoQuarto());
			novaLinha.createCell(3).setCellValue(Formatador.formatarValorQuartoParaView(q.getValorQuarto()));
			novaLinha.createCell(4).setCellValue(q.isAtivo() ? "Sim" : "Não");
			contadorLinhas++;

		}

		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}

	public String geradorPlanilhaUsuario(ArrayList<Usuario> usuarios, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Funcionários");
		
		HSSFRow linhaTitulo = abaPlanilha.createRow(0);
		linhaTitulo.createCell(0).setCellValue("Relatório de Funcionários");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(1);
		linhaCabecalho.createCell(0).setCellValue("ID");
		linhaCabecalho.createCell(1).setCellValue("Nome");
		linhaCabecalho.createCell(2).setCellValue("CPF");
		linhaCabecalho.createCell(3).setCellValue("Telefone");
		linhaCabecalho.createCell(4).setCellValue("Perfil");
		linhaCabecalho.createCell(5).setCellValue("Ativo");

		int contadorLinhas = 2;
		for (Usuario usuario : usuarios) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(usuario.getIdUsuario());
			novaLinha.createCell(1).setCellValue(usuario.getNome());
			novaLinha.createCell(2).setCellValue(usuario.getCpf());
			novaLinha.createCell(3).setCellValue(usuario.getTelefone());
			novaLinha.createCell(4).setCellValue(usuario.getPerfil());
			novaLinha.createCell(5).setCellValue(usuario.isAtivo() ? "Sim" : "Não");
			contadorLinhas++;
		}

		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}

	public String geradorPlanilhaReserva(ArrayList<Reserva> listaReservas, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Reservas");
		
		HSSFRow linhaTitulo = abaPlanilha.createRow(0);
		linhaTitulo.createCell(0).setCellValue("Relatório de Reservas");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(1);
		linhaCabecalho.createCell(0).setCellValue("ID");
		linhaCabecalho.createCell(1).setCellValue("Hospede");
		linhaCabecalho.createCell(2).setCellValue("Numero do Quarto");
		linhaCabecalho.createCell(3).setCellValue("Check-in Previsto");
		linhaCabecalho.createCell(4).setCellValue("Check-out Previsto");
		linhaCabecalho.createCell(5).setCellValue("Valor");

		int contadorLinhas = 2;
		for (Reserva reserva : listaReservas) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(reserva.getIdReserva());
			novaLinha.createCell(1).setCellValue(reserva.getHospede().getNome());
			novaLinha.createCell(2).setCellValue(reserva.getQuarto().getNumeroQuarto());
			novaLinha.createCell(3).setCellValue(reserva.getDtCheckIn());
			novaLinha.createCell(4).setCellValue(reserva.getDtCheckOut());
			novaLinha.createCell(5).setCellValue(Formatador.formatarValorQuartoParaView(reserva.calcularValorReserva(reserva)));
			contadorLinhas++;
		}

		return salvarNoDisco(arquivoExcel, destinoArquivo);
	}

	public String geradorPlanilhaHospedes(ArrayList<Hospede> hospedes, String destinoArquivo) {
		HSSFWorkbook arquivoExcel = new HSSFWorkbook();
		HSSFSheet abaPlanilha = arquivoExcel.createSheet("Hóspedes");

		HSSFRow linhaTitulo = abaPlanilha.createRow(0);
		linhaTitulo.createCell(0).setCellValue("Relatório de Hóspedes");
		
		HSSFRow linhaCabecalho = abaPlanilha.createRow(1);
		linhaCabecalho.createCell(0).setCellValue("ID");
		linhaCabecalho.createCell(1).setCellValue("Nome");
		linhaCabecalho.createCell(2).setCellValue("CPF");
		linhaCabecalho.createCell(3).setCellValue("Telefone");

		int contadorLinhas = 2;
		for (Hospede hospede : hospedes) {
			HSSFRow novaLinha = abaPlanilha.createRow(contadorLinhas);
			novaLinha.createCell(0).setCellValue(hospede.getIdHospede());
			novaLinha.createCell(1).setCellValue(hospede.getNome());
			novaLinha.createCell(2).setCellValue(hospede.getCpf());
			novaLinha.createCell(3).setCellValue(hospede.getTelefone());
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
			mensagem = "Relatório gerado com sucesso";
		} catch (FileNotFoundException e) {
			mensagem = "Erro ao tentar salvar relatório (sem acesso) : " + caminhoArquivo + extensao;
		} catch (IOException e) {
			mensagem = "Erro de I/O ao tentar salvar relatório em : " + caminhoArquivo + extensao;
			System.out.println("Causa: " + e.getMessage());
		} finally {
			if (saida != null) {
				try {
					saida.close();
					planilha.close();
				} catch (IOException e) {
					mensagem = "Erro ao tentar salvar relatório em: " + caminhoArquivo + extensao;
					System.out.println("Causa: " + e.getMessage());
				}
			}
		}

		return mensagem;
	}

}
