package br.ifba.edu.aval3.cor;

import br.ifba.edu.aval.exception.DNFException;

public abstract class AbstractRegraApuracao implements RegraApuracao {

    protected RegraApuracao proxima;
    protected ContextoOperacaoDTO contexto;

    public AbstractRegraApuracao(ContextoOperacaoDTO contexto) {
		super();
		this.contexto = contexto;
	}

	@Override
    public void setProxima(RegraApuracao proxima) {
        this.proxima = proxima;
    }
	
	@Override
	public final void apurar() throws DNFException {
		verificarRegra();
		if (proxima != null) {
			proxima.apurar();
		}
	}
	
	protected abstract void verificarRegra() throws DNFException;
	
}
