package model.seletor;

public class HospedeSeletor extends BaseSeletor {

	private String nome;
	private String cpf;
	
	@Override
	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
				|| (this.cpf != null && this.cpf.trim().length() > 0);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
