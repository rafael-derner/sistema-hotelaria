package model.vo;

import java.time.LocalDate;

public class Reserva {
	private int idReserva;
	private Hospede hospede;
	private Quarto quarto;
	private Usuario usuario;
	private LocalDate dtCheckIn;
	private LocalDate dtCheckOut;

	public Reserva() {
		super();
	}

	public Reserva(int idReserva, Hospede hospede, Quarto quarto, Usuario usuario, LocalDate dtCheckIn,
			LocalDate dtCheckOut) {
		super();
		this.idReserva = idReserva;
		this.hospede = hospede;
		this.quarto = quarto;
		this.usuario = usuario;
		this.dtCheckIn = dtCheckIn;
		this.dtCheckOut = dtCheckOut;
	}


	public int getIdReserva() {
		return idReserva;
	}


	public void setIdReserva(int idReserva) {
		this.idReserva = idReserva;
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
	
}

