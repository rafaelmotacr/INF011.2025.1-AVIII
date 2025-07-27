package br.ifba.edu.aval3.cor;

import br.ifba.edu.aval.exception.DNFException;

public abstract class AbstractRegraApuracao implements RegraApuracao {

    protected RegraApuracao proxima;
    protected ApuracaoContexto apuracaoContexto;

    public AbstractRegraApuracao(ApuracaoContexto apuracaoContexto) {
		super();
		this.apuracaoContexto = apuracaoContexto;
	}

	@Override
    public void setProxima(RegraApuracao proxima) {
        this.proxima = proxima;
    }

    protected void apurarProxima() throws DNFException {
        if (proxima != null) {
            proxima.apurar();
        }
    }
	
}
