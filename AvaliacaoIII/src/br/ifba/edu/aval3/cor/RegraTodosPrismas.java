package br.ifba.edu.aval3.cor;

import java.time.Duration;
import java.util.List;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraTodosPrismas extends AbstractRegraApuracao {

	public RegraTodosPrismas(ContextoOperacaoDTO contexto) {
		super(contexto);
	}

	@Override
	protected void verificarRegra() throws DNFException {
		List<Integer> ordemPrismas = this.contexto.boletim().getOrdemPrismas();
       	for(int iCont = 0; iCont < ordemPrismas.size() - 1; iCont++) {
       		Duration tempo = this.contexto.boletim().getTempo(ordemPrismas.get(iCont));
       		if(ordemPrismas.get(iCont) != Prisma.CHEGADA && tempo == null)
       			throw new DNFException("Atleta não registrou um dos prismas.");
       	}
		
	}
	
}
