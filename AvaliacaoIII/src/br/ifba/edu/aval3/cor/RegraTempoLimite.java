package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraTempoLimite extends AbstractRegraApuracao{

	public RegraTempoLimite(ApuracaoContexto apuracaoContexto) {
		super(apuracaoContexto);
	}

	@Override
	public void apurar() throws DNFException {
		Duration tempoProva = apuracaoContexto.getBoletim().getTempo(Prisma.CHEGADA);
	    if(tempoProva.compareTo(this.apuracaoContexto.getTempoMaximoProva()) > 0) 
	    	throw new DNFException("O atleta finalizou a prova, após o tempo limite");
	    apurarProxima();
	}

}
