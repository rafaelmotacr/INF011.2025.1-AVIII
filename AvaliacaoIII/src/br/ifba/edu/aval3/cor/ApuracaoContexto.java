package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.model.BoletimProva;

public class ApuracaoContexto {
	
	private Duration tempoMaximoProva;
	private BoletimProva boletim;
	
	public ApuracaoContexto(Duration tempoMaximoProva, BoletimProva boletim) {
		super();
		this.tempoMaximoProva = tempoMaximoProva;
		this.boletim = boletim;
	}
	
	public Duration getTempoMaximoProva() {
		return tempoMaximoProva;
	}

	public BoletimProva getBoletim() {
		return boletim;
	}
	
}
