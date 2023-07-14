package Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validador {

	/*
	 * Validar dígito 9 na 3ª posição da string
	 */
	public static boolean validarTelefone(String telefone) {
		boolean retorno = false;
//		String padrao = "^[0-9]{2}9";
		String padrao = "^[0-9]{2}9.*";
        Pattern pattern = Pattern.compile(padrao);
        Matcher matcher = pattern.matcher(telefone);
        if(matcher.matches()) {
        	retorno = true;
        }
		return retorno;
	}
}
