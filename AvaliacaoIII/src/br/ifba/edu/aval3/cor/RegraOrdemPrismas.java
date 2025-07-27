package br.ifba.edu.aval3.cor;

import java.time.Duration;
import java.util.List;

import br.ifba.edu.aval.exception.DNFException;

public class RegraOrdemPrismas extends AbstractRegraApuracao{

	public RegraOrdemPrismas(ApuracaoContexto apuracaoContexto) {
		super(apuracaoContexto);
	}

	@Override
	public void apurar() throws DNFException {
		List<Integer> ordemPrismas = this.apuracaoContexto.getBoletim().getOrdemPrismas();
    	for(int iCont = 0; iCont < ordemPrismas.size() - 1; iCont++) {
    		Duration anterior = this.apuracaoContexto.getBoletim().getTempo(ordemPrismas.get(iCont));
    		Duration atual = this.apuracaoContexto.getBoletim().getTempo(ordemPrismas.get(iCont+1));
    		if(anterior != null && atual != null) 
    			if(anterior.compareTo(atual) > 0)
    				throw new DNFException("Atleta registrou prisma fora da ordem");
    	}
    	apurarProxima();
	}

}
