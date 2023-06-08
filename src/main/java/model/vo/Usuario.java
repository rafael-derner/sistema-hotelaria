package model.vo;

public class Usuario {
	private int idUsuario;
	private String nome;
	private String cpf;
	private String telefone;
	private TipoUsuario perfil;
	
	public Usuario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Usuario(int idUsuario, String nome, String cpf, String telefone, TipoUsuario perfil) {
		super();
		this.idUsuario = idUsuario;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
		this.perfil = perfil;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
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

	public TipoUsuario getPerfil() {
		return perfil;
	}

	public void setPerfil(TipoUsuario perfil) {
		this.perfil = perfil;
	}
}
