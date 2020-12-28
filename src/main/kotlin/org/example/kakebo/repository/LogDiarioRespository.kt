package org.example.kakebo.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.example.kakebo.entity.LogDiario
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Path("log")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class LogDiarioRespository : PanacheRepository<LogDiario> {

    @Inject
    lateinit var catRepo: CategoriaRepository

    @GET
    fun getLogDiario(): List<LogDiario>? = listAll()

    @GET
    @Path("{id}")
    fun getLogDiarioById(@PathParam("id") id: Long): LogDiario? = findById(id)

    @POST
    @Transactional
    fun createLogDiario(logDiario: LogDiario) {
        val log = LogDiario()
        log.fecha = logDiario.fecha
        log.monto = logDiario.monto
        log.idCategoria = logDiario.idCategoria
        if (log.cat != null) {
            log.cat = catRepo.findById(log.cat?.id)
        } else {
            log.cat = catRepo.findById(logDiario.idCategoria)
        }
        persistAndFlush(log)
    }

    @DELETE
    @Path("{id}")
    @Transactional
    fun deleteLogDiario(@PathParam("id") id: Long) = deleteById(id)

    @PUT
    @Path("{id}")
    @Transactional
    fun updateLogDiario(@PathParam("id") id: Long, log: LogDiario) =
        update("fecha=?1, monto=?2, idCategoria=?3 where id = ?4", log.fecha, log.monto, log.cat?.id, id)

}