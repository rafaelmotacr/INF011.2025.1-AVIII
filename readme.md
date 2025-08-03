# AVALIAÇÃO FINAL DA DISCIPLINA DE PADRÕES DE PROJETO

Equipe: Alexandre Gonçalves Tuvo Matheus Conceição Bonfim e Rafael Mota Correia.
### Questão 1: State 

#### **Participantes do Padrão:**

| Papel no State          | Classe/Componente                                    | Descrição                                                                                                                                                                                                                                                                                                                                                      |
| ----------------------- | ---------------------------------------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Interface do State ** | `FaseDaCorrida`                                      | Define a interface comum para todos os estados possíveis. Na interface constam todas as operações suportadas universalmente pelos estados, cabendo a cada um tratá-las adequadamente;                                                                                                                                                                          |
| **Abstract State**      | `AbstractFaseDaCorrida`                              | Classe abstrata que define o comportamento padrão para a maioria dos estados (lançamento de exceções, em geral), o que  permite com que os estados concretos implementem apenas os métodos que lhe são necessários. Por padrão, não realiza a transição entre estados. Os estados que não suportarem determinada operação lançarão as exceções definidas aqui; |
| **Concrete State**      | `PreProva`, `MomentoLargada`, `Pista`,<br>`PosProva` | Cada estado concreto implementa as operações que lhe cabem. A exemplo, o estado de `Pista` permite que o atleta registre chegada, e imediatamente muda o estado interno do objeto para `PosProva`. Os estados concretos são responsáveis pela transição de estados do objeto contexto;                                                                         |
| **Context**             | `BoletimProva`                                       | Fornece as informações para que o estado processe corretamente as requisições. Mantém uma referência a um objeto estado (neste caso uma classe que implemente `AbstractFaseDaCorrida`) que representa dos possíveis estados do objeto. Os comportamentos específicos de cada estado estão contidos nas classes que os representam.                             |

#### **Por que usamos o State?**

O padrão state resolve o problema de manutenibilidade apresentado no enunciado de forma eficiente e facilmente extensível. Anteriormente `BoletimProva` poderia ser considerada uma _god class_, com inúmeras responsabilidades e grandes cadeias de instruções de controle de fluxo (if/else). Além disso, a definição prévia de todos os estados na classe de boletim dificultava a legibilidade do código, bem como a inserção de possíveis novos estados.

O padrão state corrige estes problemas delegando os comportamentos específicos de cada estado à classes que tratarão os requests feitos pelo contexto. Então, ao invés de grandes cadeias de if/else, cada estado pode realizar o tratamento adequado da requisição (se lhe for cabido) e transicionar para o estado correto, dependendo do contexto. 

De tal modo, o contexto passa a possuir uma referência a um objeto `state` que representa seu estado atual (aqui tratado como fase da corrida). E, ao invés de tratar ele mesmo cada uma das possibilidades de transição de estado e requests válidos ou inválidos, delega tais responsabilidades ao seu estado atual. Isso respeita o open closed e torna transparente para `BoletimProva` a forma como sua requisição está sendo tratada: ele apenas delega a requisição ao state. 

Esta solução também se mostra robusta por definir as exceções numa classe abstrata, o que diminui a repetição de código comum à todos os estados. 

Consequências: Um novo estado para ser adicionado deve apenas seguir o que foi definido na classe abstrata e redefinir os métodos que lhe couberem. No entanto, a adição de um novo comportamento em `FaseDaCorrida` resultaria na necessidade de alteração em todos os estados concretos existentes.

### Questão 2: Chain of Responsibility (CoR) + Template Method
#### **Participantes do Padrão:**
| Papel no CoR          | Papel no Template | Classe/Componente                                                                       | Descrição                                                                                                                                                                                                                                                                                                                                                         |
| --------------------- | ----------------- | --------------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Interface Handler** | Não aplicável;    | `RegraApuracao`                                                                         | Define a interface comum para todos os handlers e gerencia a cadeia (`setProximo`);                                                                                                                                                                                                                                                                               |
| **Abstract Handler**  | Classe template   | `AbstractRegraApuracao`                                                                 | Define o esqueleto do comportamento de cada hander: verificar a regra, tratar, e passar para o próximo da cadeia. No entanto, a implementação da verificação de regra deve ser implementada por cada Handler. Isso ajuda a diminuir a repetição de código comum a todas as classes concretas. Esta classe define o método template, no caso, o método `apurar()`; |
| **Concrete Handlers** | Não aplicável;    | `RegraOrdemPrismas`, `RegraRegistroChegada`, `RegraTempoLimite`,<br>`RegraTodosPrismas` | Implementam a lógica específica de verificação da determinada regra, por meio do método `verificarRegra()`                                                                                                                                                                                                                                                        |
| **Client**            | Não aplicável;    | `Apurador`                                                                              | Monta a cadeia de handlers e inicia a apuração.                                                                                                                                                                                                                                                                                                                   |

#### **Por que usamos o Chain of Responsibility?**

O padrão permite a criação de novas regras sem a necessidade de alteração no código das regras já existentes, além de permitir que o apurador, cliente, escolha quais regras deseja encadear e em que ordem. Ademais, cabe a cada regra verificar sua aplicabilidade, conforme requisitado no enunciado. O padrão também respeita o open closed pois a adição de uma nova regra é tão simples quanto criar uma nova classe e instanciá-la no apurador. 

#### **Por que usamos uma classe abstrata em vez de apenas uma interface no Chain of Responsibility?**

No padrão CoR clássico, interfaces são comuns, mas neste cenário, a classe abstrata foi preferida para **evitar repetição de código** e **garantir coesão**, já que todos os handlers compartilham a mesma estrutura e dependências. Interfaces seriam úteis se os handlers tivessem implementações radicalmente diferentes, o que não é o caso aqui.

##### Sub-tópico: Template Method

- **Template Method**: `apurar()` definido em `AbstractRegraApuracao`
- **Métodos abstratos (hooks)**: `verificarRegra()`

Justificativa: Apurar define uma rotina com chamadas para diferentes métodos que podem variar de acordo com o Handler. Contudo, a lógica de tratar a requisição e passar para o próximo é comum a todos os possíveis handlers. Abaixo, o método apurar em`AbstractRegraApuracao`:

```
public final void apurar() throws DNFException {

	verificarRegra();

	if (proxima != null) {
		proxima.apurar();
	}
}
```

Verifica-se uma rotina comum a todos os possíveis handlers (regras), no caso, a rotina de apurar, onde a única diferenciação se encontra em como cada handler fará a verificação de tal regra. Mesmo sendo uma rotina simples, o uso do Template Method neste caso reforça a reutilização e a clareza da lógica de apuração, centralizando o fluxo e isolando apenas a parte variável: `verificarRegra()`.