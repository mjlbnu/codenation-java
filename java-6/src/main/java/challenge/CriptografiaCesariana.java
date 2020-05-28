package challenge;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {
      return processaTexto(texto, 3);
    }

    @Override
    public String descriptografar(String texto) {
      return processaTexto(texto, -3);
      }

    private String processaTexto(String texto, int casas) {
      // início e fim de letras minúsculas na tabela ascii
      int ascI = 97;
      int ascF = 122;

      // verifica se o texto está vazio
      if (texto.length() == 0) {
        throw new IllegalArgumentException();
      }
      // transforma o texto em minúsculo
      String textoLC = texto.toLowerCase();
      String textoFinal = "";

      int aux;
      int i;
      int length = textoLC.length();
      char letra;

      for (i = 0; i < length; i++) {
        letra = textoLC.charAt(i);
        aux = (int) letra;
        // somente altera se for letra
        if ((aux >= ascI) && (aux <= ascF))
          textoFinal = textoFinal + (char) (aux + casas);
        else
          textoFinal += (char) (aux);
      }
      return textoFinal;
    }
}
