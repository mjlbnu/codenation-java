package br.com.codenation;

import java.util.*;
import java.util.stream.IntStream;

public class StatisticUtil {

	public static int average(int[] elements) {
	  IntStream st = Arrays.stream(elements);
	  return st.sum() / elements.length;
	}

	public static int mode(int[] elements) {
    // Ordena
	  Arrays.sort(elements);

	  int tamanho = elements.length;

	  // Inicializa as variáveis
	  int qtdMaior = 1;
	  int elemento = elements[0];
	  int qtd = 1;

	  for (int i = 1; i < elements.length; i++) {
	    if (elements[i] == elements[i - 1])
	      qtd++;
	    else {
	      if (qtd > qtdMaior) {
	        qtdMaior = qtd;
	        elemento = elements[i - 1];
        }
	      qtd = 1;
      }
    }

	  if (qtd > qtdMaior) {
	    qtdMaior = qtd;
	    elemento = elements[tamanho - 1];
    }

		return elemento;
	}

	public static int median(int[] elements) {
    Arrays.sort(elements);

    if (elements.length % 2 == 0)
      return (elements[elements.length / 2] + elements[elements.length / 2 - 1]) / 2;
    else
      return elements[elements.length / 2];
	}
}
