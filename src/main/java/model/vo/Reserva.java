package model.vo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reserva {
	private Integer idReserva;
	private Hospede hospede;
	private Quarto quarto;
	private Usuario usuario;
	private LocalDate dtCheckIn;
	private LocalDate dtCheckOut;

	public Reserva() {
		super();
	}

	public Reserva(Integer idReserva, Hospede hospede, Quarto quarto, Usuario usuario, LocalDate dtCheckIn,
			LocalDate dtCheckOut) {
		super();
		this.idReserva = idReserva;
		this.hospede = hospede;
		this.quarto = quarto;
		this.usuario = usuario;
		this.dtCheckIn = dtCheckIn;
		this.dtCheckOut = dtCheckOut;
	}


	public Integer getIdReserva() {
		return idReserva;
	}


	public void setIdReserva(Integer idReserva) {
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
	
	public double calcularValorReserva(Reserva reserva) {
		double valor = 0;
		long dias = ChronoUnit.DAYS.between(reserva.getDtCheckIn(), reserva.getDtCheckOut());
        int diasFormatados = Math.toIntExact(dias);
        valor = diasFormatados * reserva.getQuarto().getValorQuarto();
        if(valor == 0) {
        	valor = reserva.getQuarto().getValorQuarto();
        }
        return valor;
    }
	
}

