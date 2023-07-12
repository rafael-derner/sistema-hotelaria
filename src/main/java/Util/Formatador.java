package Util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import model.vo.Quarto;

public abstract class Formatador {

	/*
	 * Formata a string passada para o formato ###.###.###-##
	 */
	public static String formatarCpfParaView(String cpf) {
		String cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-"
				+ cpf.substring(9, 11);
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
		String numeroFormatado = "(" + numero.substring(0, 2) + ") " + numero.substring(2, 7) + "-"
				+ numero.substring(7, 11);
		return numeroFormatado;
	}

	/*
	 * Altera a string para não ter formatação
	 */
	public static String formatarTelefoneMovelParaBanco(String telefone) {
		return telefone.replaceAll("[()\\-.\\s]", "");
	}

	public static String formatarValorQuartoParaView(Double valor) {
		DecimalFormat formatador = new DecimalFormat("0.00");

		return "R$ " + formatador.format(valor);
	}

	public static int formatarTipoQuarto(String tipoQuarto) {
		int numeroTipoQuarto = 0;
		if (tipoQuarto.equalsIgnoreCase("Básico")) {
			numeroTipoQuarto = 0;
		} else if (tipoQuarto.equalsIgnoreCase("Intermediário")) {
			numeroTipoQuarto = 1;
		} else if (tipoQuarto.equalsIgnoreCase("Luxo")) {
			numeroTipoQuarto = 2;
		}
		return numeroTipoQuarto;
	}

	public static int formatarTipoUsuario(String perfil) {
		int numeroTipoPerfil = 0;
		if (perfil.equalsIgnoreCase("Recepcionista")) {
			numeroTipoPerfil = 0;
		} else if (perfil.equalsIgnoreCase("Gerente")) {
			numeroTipoPerfil = 1;
		}
		return numeroTipoPerfil;
	}

}
