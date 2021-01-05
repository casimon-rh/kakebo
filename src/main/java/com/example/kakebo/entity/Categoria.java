package com.example.kakebo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Categoria {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  @JsonProperty("id")
  private Long id;

  @Column(name = "name")
  @JsonProperty("name")
  private String name;

  @OneToMany(mappedBy = "categoria")
  private Set<LogDiario> logs;
}
