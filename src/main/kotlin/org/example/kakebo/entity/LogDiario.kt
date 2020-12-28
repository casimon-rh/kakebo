package org.example.kakebo.entity

import io.quarkus.hibernate.orm.panache.PanacheEntity
import java.util.*
import javax.persistence.*

@Entity
class LogDiario: PanacheEntity() {

  var fecha:Date?=null
  var monto:Double=0.0
  @Column( insertable = false, updatable = false)
  var idCategoria:Long=0

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "idCategoria")
  var cat:Categoria?=null
}