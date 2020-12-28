package org.example.kakebo.entity

import io.quarkus.hibernate.orm.panache.PanacheEntity
import java.util.Date
import javax.persistence.Entity

@Entity
class Ingreso: PanacheEntity() {
    var inicio:Date?=null
    var fin:Date?=null
    var monto:Double=0.0
}