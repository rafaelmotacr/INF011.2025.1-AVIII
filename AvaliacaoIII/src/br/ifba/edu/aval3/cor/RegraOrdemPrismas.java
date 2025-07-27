package br.ifba.edu.aval3.cor;

import java.time.Duration;
import java.util.List;

import br.ifba.edu.aval.exception.DNFException;

public class RegraOrdemPrismas extends AbstractRegraApuracao{

	public RegraOrdemPrismas(ContextoOperacaoDTO contexto) {
		super(contexto);
	}

	@Override
	protected void verificarRegra() throws DNFException {
		List<Integer> ordemPrismas = this.contexto.boletim().getOrdemPrismas();
    	for(int iCont = 0; iCont < ordemPrismas.size() - 1; iCont ++) {
    		Duration anterior = this.contexto.boletim().getTempo(ordemPrismas.get(iCont));
    		Duration atual = this.contexto.boletim().getTempo(ordemPrismas.get(iCont + 1));
    		if(anterior != null && atual != null) 
    			if(anterior.compareTo(atual) > 0)
    				throw new DNFException("Atleta registrou prisma fora da ordem");
    	}
		
	}

}
