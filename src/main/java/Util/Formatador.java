package Util;

public class Formatador {
	
	/*
	 * Formata a string passada para o formato ###.###.###-##
	 */
	public static String formatarCpf(String cpf) {
		String cpfFormatado = cpf.substring(0, 3) + "." + cpf.substring(3, 6) + "." + cpf.substring(6, 9) + "-" + cpf.substring(9, 11);
		return cpfFormatado;
	}
	
	/*
	 * Formata a string passada para o formato (##) #####-####
	 */
	public static String formatarTelefoneMovel(String numero) {
		String numeroFormatado = "(" + numero.substring(0, 2) + ") " + numero.substring(2, 7) + "-" + numero.substring(7, 11);
		return numeroFormatado;
	}

}