package model.seletor;

import java.time.LocalDate;

public class ReservaSeletor extends BaseSeletor {

	private String nomeHospede;
	private int numQuarto;
	private LocalDate dataEntrada;
	private LocalDate dataSaida;

	@Override
	public boolean temFiltro() {
		return (this.nomeHospede != null && this.nomeHospede.trim().length() > 0) || (this.numQuarto > 0)
				|| (this.dataEntrada != null) || (this.dataSaida != null);
	}

	public String getNomeHospede() {
		return nomeHospede;
	}

	public void setNomeHospede(String nomeHospede) {
		this.nomeHospede = nomeHospede;
	}

	public int getNumQuarto() {
		return numQuarto;
	}

	public void setNumQuarto(int numQuarto) {
		this.numQuarto = numQuarto;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDate dataSaida) {
		this.dataSaida = dataSaida;
	}

}
