package model.seletor;

public class UsuarioSeletor extends BaseSeletor {
	private String nome;
	private String cpf;
	private String perfil;

	@Override
	public boolean temFiltro() {
		return (this.nome != null && this.nome.trim().length() > 0)
				|| (this.cpf != null && this.cpf.trim().length() > 0) || (this.perfil != null);
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

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

}
