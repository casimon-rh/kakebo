package com.example.kakebo.Repository;

import com.example.kakebo.entity.Categoria;
import com.example.kakebo.entity.LogDiario;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/log")
@Produces("application/json")
@Consumes("application/json")
public class LogDiarioRepository {

  @Inject
  private KakeboJPAFactory kjf;

  @GET
  public Response getAll() {
    EntityManager em = kjf.getEntityManager();
    CriteriaQuery<LogDiario> cq = em.getCriteriaBuilder().createQuery(LogDiario.class);
    return Response.ok(em.createQuery(cq.select(cq.from(LogDiario.class))).getResultList()).build();
  }
  @GET
  @Path("{id}")
  public Response getOne(@PathParam("id") long id){
    EntityManager em = kjf.getEntityManager();
    return Response.ok(em.find(LogDiario.class, id)).build();
  }

  @POST
  @Transactional
  public Response create(LogDiario log) {
    EntityManager em = kjf.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(log);
    tx.commit();
    return Response.ok(log).build();
  }

  @DELETE
  @Transactional
  @Path("{id}")
  public Response delete(@PathParam("id") long id) {
    EntityManager em = kjf.getEntityManager();
    LogDiario log = em.find(LogDiario.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.remove(log);
    tx.commit();
    return Response.ok().build();
  }

  @PUT
  @Transactional
  @Path("{id}")
  public Response update(@PathParam("id") long id, LogDiario log) {
    EntityManager em = kjf.getEntityManager();
    LogDiario lo = em.find(LogDiario.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
//    if (log.getIdCategoria() > 0) {
//      lo.setIdCategoria(log.getIdCategoria());
//      lo.setCategoria(em.find(Categoria.class, log.getIdCategoria()));
//    }
    lo.setFecha(log.getFecha());
    lo.setMonto(log.getMonto());
    tx.commit();
    return Response.ok(lo).build();
  }
}
