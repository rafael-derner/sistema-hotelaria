package model.vo;

public class Quarto {
	
	private Integer idQuarto;
	private Integer numeroQuarto;
	private Double valorQuarto;
	private String tipoQuarto;
	
	public Quarto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Quarto(Integer idQuarto, Integer numeroQuarto, Double valorQuarto, String tipoQuarto) {
		super();
		this.idQuarto = idQuarto;
		this.numeroQuarto = numeroQuarto;
		this.valorQuarto = valorQuarto;
		this.tipoQuarto = tipoQuarto;
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
	

}
