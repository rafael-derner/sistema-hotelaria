package Util;

import java.text.NumberFormat;
import java.util.Locale;

import model.vo.Quarto;

public class Formatador {
	
	/*
	 * Formata a string passada para o formato ###.###.###-##
	 */
	public static String formatarCpfParaView(String cpf) {
		String cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
		return cpfFormatado;
	}

	/*
	 * Altera a string para não ter formatação
	 */
	public static String formatarCpfParaBanco(String cpf) {
		return cpf.replaceAll("[()\\-.\\s]", "");
	}
	
	/*
	 * Formata a string passada para o formato (##) #####-####
	 */
	public static String formatarTelefoneMovelParaView(String numero) {
		String numeroFormatado = "(" + numero.substring(0, 2) + ") " + numero.substring(2, 7) + "-" + numero.substring(7, 11);
		return numeroFormatado;
	}

	/*
	 * Altera a string para não ter formatação
	 */
	public static String formatarTelefoneMovelParaBanco(String telefone) {
		return telefone.replaceAll("[()\\-.\\s]", "");
	}

//	public static Object formatarValorQuartoParaView(Quarto valorDiaria) {
//		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
//        String valorFormatado = nf.format(valorDiaria);
//        return Double.parseDouble(valorFormatado.replace("R$", "").trim());
//	}

	public static String formatarValorQuartoParaView(Quarto quarto) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return nf.format(quarto);
   
	}

}
