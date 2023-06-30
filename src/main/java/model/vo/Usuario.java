package model.vo;

public class Usuario {
	private Integer idUsuario;
	private String nome;
	private String cpf;
	private String telefone;
	private String perfil;
	private boolean ativo;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(Integer idUsuario, String nome, String cpf, String telefone, String perfil) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.perfil = perfil;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getPerfil() {
		return perfil;
	}

	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
}
