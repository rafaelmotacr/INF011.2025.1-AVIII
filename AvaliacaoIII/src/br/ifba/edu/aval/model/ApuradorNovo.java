package br.ifba.edu.aval.model;

import java.time.Duration;

import br.ifba.edu.aval.exception.AtividadeNaoPermitidaException;
import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval3.cor.ApuracaoContexto;
import br.ifba.edu.aval3.cor.RegraApuracao;
import br.ifba.edu.aval3.cor.RegraOrdemPrismas;
import br.ifba.edu.aval3.cor.RegraRegistroChegada;
import br.ifba.edu.aval3.cor.RegraTempoLimite;
import br.ifba.edu.aval3.cor.RegraTodosPrismas;

public class ApuradorNovo {

	private Duration tempoMaximo;
	
	public ApuradorNovo(Duration tempoMaximoProva) {
		this.tempoMaximo = tempoMaximoProva;
	}
	
	public Duration apurar(BoletimProva boletim) throws DNFException, AtividadeNaoPermitidaException {
		Duration tempoProva = Duration.ZERO;
		tempoProva = boletim.getTempo(Prisma.CHEGADA);
		
    	ApuracaoContexto contexto = new ApuracaoContexto(tempoMaximo, boletim);
    	
    	RegraApuracao regra1 = new RegraRegistroChegada(contexto);
    	RegraApuracao regra2 = new RegraTempoLimite(contexto);
    	RegraApuracao regra3 = new RegraOrdemPrismas(contexto);
    	RegraApuracao regra4 = new RegraTodosPrismas(contexto);
    	
    	regra1.setProxima(regra2);
    	regra2.setProxima(regra3);
    	regra3.setProxima(regra4);
    	
    	regra1.apurar();
       	
       	tempoProva = tempoProva.plus(Duration.ofMinutes(boletim.getMinutosAtraso()));
    	return tempoProva;
    	
	}	
	
}
