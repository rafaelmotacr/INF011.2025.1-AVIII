package br.ifba.edu.aval3.cor;

import java.time.Duration;
import java.util.List;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraTodosPrismas extends AbstractRegraApuracao {

	public RegraTodosPrismas(ApuracaoContexto apuracaoContexto) {
		super(apuracaoContexto);
	}

	@Override
	public void apurar() throws DNFException {
		List<Integer> ordemPrismas = this.apuracaoContexto.getBoletim().getOrdemPrismas();
       	for(int iCont = 0; iCont < ordemPrismas.size() - 1; iCont++) {
       		Duration tempo = this.apuracaoContexto.getBoletim().getTempo(ordemPrismas.get(iCont));
       		if(ordemPrismas.get(iCont) != Prisma.CHEGADA && tempo == null)
       			throw new DNFException("Atleta não registrou um dos prismas.");
       	}
       	apurarProxima();
	}
	
}
