package com.example.kakebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingreso {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  @JsonProperty("id")
  private Long id;

  @Column(name = "inicio")
  @JsonProperty("inicio")
  private Date inicio;

  @Column(name = "fin")
  @JsonProperty("fin")
  private Date fin;
  
  @Column(name = "monto")
  @JsonProperty("monto")
  private double monto;

  public Date getInicio() {
    return inicio;
  }

  public void setInicio(Date inicio) {
    this.inicio = inicio;
  }

  public Date getFin() {
    return fin;
  }

  public void setFin(Date fin) {
    this.fin = fin;
  }

  public double getMonto() {
    return monto;
  }

  public void setMonto(double monto) {
    this.monto = monto;
  }
}
