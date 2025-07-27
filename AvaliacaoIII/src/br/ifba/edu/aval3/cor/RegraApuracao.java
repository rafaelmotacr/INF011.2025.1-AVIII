package br.ifba.edu.aval3.cor;

import br.ifba.edu.aval.exception.DNFException;

public interface RegraApuracao {
	
	public void setProxima(RegraApuracao regraApuracao);
	public void apurar() throws DNFException;

}
