package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;
import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.desafio.exceptions.IdentificadorUtilizadoException;
import br.com.codenation.desafio.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.desafio.exceptions.TimeNaoEncontradoException;
import br.com.codenation.jogador.Jogador;
import br.com.codenation.time.Time;

public class DesafioMeuTimeApplication implements MeuTimeInterface {

  // Listas
  private List<Time> times = new ArrayList<>();
  private List<Jogador> jogadores = new ArrayList<>();

  @Desafio("incluirTime")
	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
      if (!verificaIdTime(id)) {
      Time time = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
      times.add(time);
    }else throw new IdentificadorUtilizadoException("Identificador já utilizado: " + id);
	}

	@Desafio("incluirJogador")
	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
      if (!verificaIdJogador(id)) {
        Time time = buscaTime(idTime);

        if (nivelHabilidade < 0 || nivelHabilidade > 100) {
          throw new IllegalArgumentException("Nível de habilidade inválido");
        }

        Jogador jogador = new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario);
        time.adicionaJogador(jogador);
        jogadores.add(jogador);
      }else throw new IdentificadorUtilizadoException("Identificador já utilizado: " + id);
	}

	@Desafio("definirCapitao")
	public void definirCapitao(Long idJogador) {
    Jogador jogador = buscaJogador(idJogador);
    Time time = buscaTime(jogador.getIdTime());
    time.setCapitao(jogador);
	}

	@Desafio("buscarCapitaoDoTime")
	public Long buscarCapitaoDoTime(Long idTime) {
      Time time = buscaTime(idTime);
      Jogador capitao = time.getCapitao();
      if(Objects.isNull(capitao)){
        throw new CapitaoNaoInformadoException("O time com id " + idTime + " não possui capitão");
      }else return capitao.getId();
  }

	@Desafio("buscarNomeJogador")
	public String buscarNomeJogador(Long idJogador) {
     Jogador jogador = buscaJogador(idJogador);
     return jogador.getNome();
	}

	@Desafio("buscarNomeTime")
	public String buscarNomeTime(Long idTime) {
		Time time = buscaTime(idTime);
		return time.getNome();
	}

	@Desafio("buscarJogadoresDoTime")
	public List<Long> buscarJogadoresDoTime(Long idTime) {
		Time time = buscaTime(idTime);
    List<Jogador> listaDeJogadores = time.getJogadores();
		List<Long> listaDeIds = new ArrayList<>();
    listaDeJogadores.stream().forEach((jogador) -> listaDeIds.add(jogador.getId()));
    return listaDeIds.stream().sorted().collect(Collectors.toList());
	}

	@Desafio("buscarMelhorJogadorDoTime")
	public Long buscarMelhorJogadorDoTime(Long idTime) {
    Time time = buscaTime(idTime);
    List<Jogador> listaDeJogadores = time.getJogadores();
    Jogador jogador = listaDeJogadores.stream().max(Comparator.comparingInt(Jogador::getNivelHabilidade)).get();
    return jogador.getId();
	}

	@Desafio("buscarJogadorMaisVelho")
	public Long buscarJogadorMaisVelho(Long idTime) {
		Time time = buscaTime(idTime);
    List<Jogador> listaDeJogadores = time.getJogadores();
    Jogador jogador = listaDeJogadores.stream()
              .min(Comparator.comparing(Jogador::getDataNascimento).thenComparing(Jogador::getId)).get();
    return jogador.getId();
	}

	@Desafio("buscarTimes")
	public List<Long> buscarTimes() {
    return times.stream().sorted(Comparator.comparing(Time::getId))
             .map(Time::getId).collect(Collectors.toList());
	}

	@Desafio("buscarJogadorMaiorSalario")
	public Long buscarJogadorMaiorSalario(Long idTime) {
    Time time = buscaTime(idTime);
    List<Jogador> listaDeJogadores = time.getJogadores();

    Jogador jogador = listaDeJogadores.stream().max(Comparator.comparing(Jogador::getSalario)).get();
    return jogador.getId();
	}

	@Desafio("buscarSalarioDoJogador")
	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		Jogador jogador = buscaJogador(idJogador);
		return jogador.getSalario();
	}

	@Desafio("buscarTopJogadores")
	public List<Long> buscarTopJogadores(Integer top) {
    return jogadores.stream()
      .sorted(Comparator.comparingInt(Jogador::getNivelHabilidade).reversed()
      .thenComparingLong(Jogador::getId))
      .limit(top)
      .map(Jogador::getId)
      .collect(Collectors.toList());
	}

	@Desafio("buscarCorCamisaTimeDeFora")
	public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
		Time daCasa = buscaTime(timeDaCasa);
		Time deFora = buscaTime(timeDeFora);

		if(daCasa.getCorUniformePrincipal().equalsIgnoreCase(deFora.getCorUniformePrincipal())){
		  return deFora.getCorUniformeSecundario();
    } else {
		  return deFora.getCorUniformePrincipal();
    }
	}

	// Verifica se o Id informado já foi utilizado por outro Time
  private boolean verificaIdTime(Long id) {
    // Retorna true ao encontrar o primeiro elemento correspondente ao predicado
    return times.stream().anyMatch(time -> time.getId().equals(id));
  }

  // Verifica se o Id informado já foi utilizado por outro Jogador
  private boolean verificaIdJogador(Long id) {
    return jogadores.stream().anyMatch(jogador -> jogador.getId().equals(id));
  }

  private Time buscaTime(Long id) {
    return times.stream()
             .filter(time -> time.getId().equals(id))
             .findFirst()
             .orElseThrow(() -> new TimeNaoEncontradoException("Time não encontrado, id:" + id));
  }

  private Jogador buscaJogador(Long id) {
    return jogadores.stream()
             .filter(jogador -> jogador.getId().equals(id))
             .findFirst()
             .orElseThrow(() -> new JogadorNaoEncontradoException("Jogador não encontrado, id:" + id));
  }

}
