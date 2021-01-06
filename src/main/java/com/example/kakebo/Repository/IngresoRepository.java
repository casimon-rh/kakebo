package com.example.kakebo.Repository;

import com.example.kakebo.entity.Ingreso;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/ingreso")
@Produces("application/json")
@Consumes("application/json")
public class IngresoRepository {

  @Inject
  private KakeboJPAFactory kjf;

  @GET
  public Response getAll() {
    EntityManager em = kjf.getEntityManager();
    CriteriaQuery<Ingreso> cq = em.getCriteriaBuilder().createQuery(Ingreso.class);
    return Response.ok(em.createQuery(cq.select(cq.from(Ingreso.class))).getResultList()).build();
  }
  @GET
  @Path("{id}")
  public Response getOne(@PathParam("id") long id){
    EntityManager em = kjf.getEntityManager();
    return Response.ok(em.find(Ingreso.class, id)).build();
  }

  @POST
  @Transactional
  public Response create(Ingreso ing) {
    EntityManager em = kjf.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(ing);
    tx.commit();
    return Response.ok(ing).build();
  }

  @DELETE
  @Transactional
  @Path("{id}")
  public Response delete(@PathParam("id") long id) {
    EntityManager em = kjf.getEntityManager();
    Ingreso ing = em.find(Ingreso.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.remove(ing);
    tx.commit();
    return Response.ok().build();
  }

  @PUT
  @Transactional
  @Path("{id}")
  public Response update(@PathParam("id") long id, Ingreso ing) {
    EntityManager em = kjf.getEntityManager();
    Ingreso in = em.find(Ingreso.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    in.setFin(ing.getFin());
    in.setInicio(ing.getInicio());
    in.setMonto(ing.getMonto());
    tx.commit();
    return Response.ok(in).build();
  }
}
