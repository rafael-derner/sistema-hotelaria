package model.seletor;

public class QuartoSeletor extends BaseSeletor {
	
	private Integer numeroQuarto;
	private Double valorQuarto;
	private String tipoQuarto;
	private String filtroValor;
	

	@Override
	public boolean temFiltro() {
		// TODO Auto-generated method stub
		return (this.numeroQuarto != null
				|| this.tipoQuarto != null
				|| this.valorQuarto != null)
				|| this.filtroValor != null;
	}


	public String getFiltroValor() {
		return filtroValor;
	}


	public void setFiltroValor(String filtroValor) {
		this.filtroValor = filtroValor;
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
