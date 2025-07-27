package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraTempoLimite extends AbstractRegraApuracao{

	public RegraTempoLimite(ContextoOperacaoDTO apuracaoContexto) {
		super(apuracaoContexto);
	}

	@Override
	protected void verificarRegra() throws DNFException {
		Duration tempoProva = contexto.boletim().getTempo(Prisma.CHEGADA);
	    if(tempoProva.compareTo(this.contexto.tempoMaximoProva()) > 0) 
	    	throw new DNFException("O atleta finalizou a prova, após o tempo limite");
	}

}
