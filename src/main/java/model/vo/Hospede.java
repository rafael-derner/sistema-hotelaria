package model.vo;

public class Hospede {
	private Integer idHospede;
	private String nome;
	private String cpf;
	private String telefone;
	
	public Hospede(Integer idHospede, String nome, String cpf, String telefone) {
		super();
		this.idHospede = idHospede;
		this.nome = nome;
		this.cpf = cpf;
		this.telefone = telefone;
	}

	public Hospede() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getIdHospede() {
		return idHospede;
	}

	public void setIdHospede(Integer idHospede) {
		this.idHospede = idHospede;
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
	
}
