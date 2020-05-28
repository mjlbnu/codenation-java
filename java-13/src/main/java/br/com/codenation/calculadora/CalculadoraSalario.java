package br.com.codenation.calculadora;

public class CalculadoraSalario {

	public long calcularSalarioLiquido(double salarioBase) {
    double salarioMinimo = 1039.00;

	  if (salarioBase < salarioMinimo) return Math.round(0.0);
    else
      return Math.round(calcularIrrf(calcularInss(salarioBase)));
	}

	private double calcularInss(double salarioBase) {
		if (salarioBase <= 1500) return salarioBase - (salarioBase * 0.08);
    else if (salarioBase > 1500 && (salarioBase <= 4000)) return salarioBase - (salarioBase * 0.09);
		else
      return  salarioBase - (salarioBase * 0.11);
	}

	private double calcularIrrf(double valor) {
	  if (valor <= 3000) return valor;
    else if (valor >= 3000 && (valor <= 6000)) return valor - (valor * 0.075);
    else
      return valor - (valor * 0.15);
  }

}
