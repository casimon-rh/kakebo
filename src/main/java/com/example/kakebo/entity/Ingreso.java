package com.example.kakebo.entity;

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
  private Long id;

  @Column(name = "inicio")
  private Date inicio;

  @Column(name = "fin")
  private Date fin;
  
  @Column(name = "monto")
  private double monto;
  
}
