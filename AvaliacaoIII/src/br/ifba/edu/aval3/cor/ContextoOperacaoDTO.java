package br.ifba.edu.aval3.cor;

import java.time.Duration;

import br.ifba.edu.aval.model.BoletimProva;

public record ContextoOperacaoDTO (Duration tempoMaximoProva, BoletimProva boletim) {}
