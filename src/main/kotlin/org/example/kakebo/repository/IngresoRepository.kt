package org.example.kakebo.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.example.kakebo.entity.Ingreso
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Path("ingreso")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class IngresoRepository: PanacheRepository<Ingreso> {

    @GET
    fun getIngreso(): List<Ingreso>? = listAll()

    @GET
    @Path("{id}")
    fun getIngresoById(@PathParam("id") id:Long): Ingreso? = findById(id)

    @POST
    @Transactional
    fun createIngreso(ingreso:Ingreso) {
        val ing = Ingreso()
        ing.fin = ingreso.fin
        ing.inicio = ingreso.inicio
        ing.monto = ingreso.monto
        persistAndFlush(ing)
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun deleteIngreso(@PathParam("id") id:Long) = deleteById(id)

    @PUT
    @Path("{id}")
    @Transactional
    fun updateIngreso(@PathParam("id") id:Long, ingreso:Ingreso) =
        update("fin=?1, inicio=?2, monto=?3 where id = ?4",ingreso.fin, ingreso.inicio, ingreso.monto, id )

}