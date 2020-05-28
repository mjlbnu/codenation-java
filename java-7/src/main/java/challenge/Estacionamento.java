package challenge;

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

    private static final byte LIMITE = 10;
    private List<Carro> carrosEstacionados = new ArrayList<>();

    public void estacionar(Carro carro) {
      if (carrosEstacionados() == LIMITE) {
        boolean estacionado = false;

        for(int i = 0; i < carrosEstacionados.size(); i++){
          if (carrosEstacionados.get(i).getMotorista().getIdade() < 55) {
            carrosEstacionados.set(i, carro);
            estacionado = true;
            break;
          }
        }

        if (!estacionado) {
          throw new EstacionamentoException("Motorista não encontrou vaga.");
        }


      } else {
        carrosEstacionados.add(carro);
      }
    }

    public int carrosEstacionados() {
        return carrosEstacionados.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carrosEstacionados.stream().anyMatch(c -> c.equals(carro));
    }
}
