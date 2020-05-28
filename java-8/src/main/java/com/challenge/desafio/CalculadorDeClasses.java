package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;

public class CalculadorDeClasses implements Calculavel {

  @Override
  public BigDecimal somar(Object object) {
    return somarFields(object, (Class) Somar.class);
  }

  @Override
  public BigDecimal subtrair(Object object) {
    return somarFields(object, (Class) Subtrair.class);
  }

  @Override
  public BigDecimal totalizar(Object object) {
    return somar(object).subtract(subtrair(object));
  }

  private BigDecimal somarFields(Object object, Class<Annotation> annotationClass) {
    BigDecimal soma = BigDecimal.ZERO;

    for (Field field : object.getClass().getDeclaredFields()) {
      if (field.isAnnotationPresent(annotationClass) && field.getAnnotation(annotationClass) != null
        && field.getType().equals(BigDecimal.class)) {
        try {
          field.setAccessible(true);
          soma = soma.add((BigDecimal) field.get(object));
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
    return soma;
  }
}
