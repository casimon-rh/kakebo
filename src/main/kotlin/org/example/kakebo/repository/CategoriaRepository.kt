package org.example.kakebo.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.example.kakebo.entity.Categoria
import javax.enterprise.context.ApplicationScoped
import javax.inject.Singleton
import javax.transaction.Transactional
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@ApplicationScoped
@Path("categoria")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class CategoriaRepository: PanacheRepository<Categoria> {

  @GET
  fun getCategorias(): List<Categoria>? = listAll()

  @GET
  @Path("{id}")
  fun getCategoriaById(@PathParam("id") id:Long): Categoria = findById(id)

  @POST
  @Transactional
  fun createCategoria(cat:String) = persistAndFlush(Categoria(cat))

  @DELETE
  @Path("{id}")
  @Transactional
  fun deleteCategoria(@PathParam("id") id:Long) = deleteById(id)

  @PUT
  @Path("{id}")
  @Transactional
  fun updateCategoria(@PathParam("id") id:Long, cat: String) = update("name=?1 where id = ?2", cat, id)

}