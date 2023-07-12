package model.vo;

public class Quarto {

	private Integer idQuarto;
	private Integer numeroQuarto;
	private Double valorQuarto;
	private String tipoQuarto;
	private boolean ativo;

	public Quarto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quarto(Integer idQuarto, Integer numeroQuarto, Double valorQuarto, String tipoQuarto, boolean ativo) {
		super();
		this.idQuarto = idQuarto;
		this.numeroQuarto = numeroQuarto;
		this.valorQuarto = valorQuarto;
		this.tipoQuarto = tipoQuarto;
		this.ativo = ativo;
	}

	public Integer getIdQuarto() {
		return idQuarto;
	}

	public void setIdQuarto(Integer idQuarto) {
		this.idQuarto = idQuarto;
	}

	public Integer getNumeroQuarto() {
		return numeroQuarto;
	}

	public void setNumeroQuarto(Integer numeroQuarto) {
		this.numeroQuarto = numeroQuarto;
	}

	public Double getValorQuarto() {
		return valorQuarto;
	}

	public void setValorQuarto(Double valorQuarto) {
		this.valorQuarto = valorQuarto;
	}

	public String getTipoQuarto() {
		return tipoQuarto;
	}

	public void setTipoQuarto(String tipoQuarto) {
		this.tipoQuarto = tipoQuarto;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

}
