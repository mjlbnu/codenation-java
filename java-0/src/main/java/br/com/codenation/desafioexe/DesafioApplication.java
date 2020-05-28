package br.com.codenation.desafioexe;

import java.util.ArrayList;
import java.util.List;

public class DesafioApplication {

	public static List<Integer> fibonacci()
  {
    List<Integer> lista = new ArrayList<>();
    lista.add(0);
    lista.add(1);
    int x;

    do {
      x = lista.get(lista.size()-1) + lista.get(lista.size()-2);
      lista.add(x);
      System.out.println(x);
    } while (x <= 350);
		return lista;
	}

	public static List<Integer> montalista(List<Integer> list) {
	  return list;
  }

	public static Boolean isFibonacci(Integer a) {
    List<Integer> lista = new ArrayList<Integer>();
    lista = fibonacci();

    return lista.contains(a);
	}

}
