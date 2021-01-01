package com.example.kakebo.entity;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class LogDiario {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Long id;
  
  @Column(name="fecha")
  private Date fecha;

  @Column(name="monto")
  private double monto;

  @ManyToOne
  @JoinColumn(name ="idCategoria", referencedColumnName = "id")
  private Categoria categoria;
  
}
