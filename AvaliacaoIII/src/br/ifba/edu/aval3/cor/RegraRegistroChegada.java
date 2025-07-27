package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraRegistroChegada extends AbstractRegraApuracao {

	public RegraRegistroChegada(ContextoOperacaoDTO contexto) {
		super(contexto);
	}
	
	@Override
	protected void verificarRegra() throws DNFException {
		Duration tempoProva = contexto.boletim().getTempo(Prisma.CHEGADA);
    	if(tempoProva == null)
    		throw new DNFException("Atleta não registrou chegada");	
	}

}
