package model.vo;

import java.time.LocalDate;

public class Reserva {
	private Hospede hospede;
	private Quarto quarto;
	private Usuario usuario;
	private LocalDate dtCheckIn;
	private LocalDate dtCheckOut;
	
	public Reserva(Hospede hospede, Quarto quarto, Usuario usuario, LocalDate dtCheckIn, LocalDate dtCheckOut) {
		super();
		this.hospede = hospede;
		this.quarto = quarto;
		this.usuario = usuario;
		this.dtCheckIn = dtCheckIn;
		this.dtCheckOut = dtCheckOut;
	}
	
	public Reserva(Hospede hospede, Quarto quarto, Usuario usuario) {
		super();
		this.hospede = hospede;
		this.quarto = quarto;
		this.usuario = usuario;
	}

	public Reserva() {
		super();
	}
	
	public Hospede getHospede() {
		return hospede;
	}
	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}
	public Quarto getQuarto() {
		return quarto;
	}
	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getDtCheckIn() {
		return dtCheckIn;
	}
	public void setDtCheckIn(LocalDate dtCheckIn) {
		this.dtCheckIn = dtCheckIn;
	}
	public LocalDate getDtCheckOut() {
		return dtCheckOut;
	}
	public void setDtCheckOut(LocalDate dtCheckOut) {
		this.dtCheckOut = dtCheckOut;
	}

	@Override
	public String toString() {
		return "Reserva [hospede=" + hospede + ", quarto=" + quarto + ", usuario=" + usuario + ", dtCheckIn="
				+ dtCheckIn + ", dtCheckOut=" + dtCheckOut + "]";
	}
	
}

