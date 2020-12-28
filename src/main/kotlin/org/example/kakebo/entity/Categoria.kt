package org.example.kakebo.entity

import io.quarkus.hibernate.orm.panache.PanacheEntity
import javax.persistence.Entity
import javax.persistence.OneToMany

@Entity
class Categoria: PanacheEntity {
  var name:String=""

  constructor(){}
  constructor(nam: String) {
    this.name = nam
  }
}