package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.exception.DNFException;
import br.ifba.edu.aval.model.Prisma;

public class RegraRegistroChegada extends AbstractRegraApuracao {

	public RegraRegistroChegada(ApuracaoContexto apuracaoContexto) {
		super(apuracaoContexto);
	}

	@Override
	public void apurar() throws DNFException {
		Duration tempoProva = apuracaoContexto.getBoletim().getTempo(Prisma.CHEGADA);
    	if(tempoProva == null)
    		throw new DNFException("Atleta não registrou chegada");
    	apurarProxima();
	}

}
