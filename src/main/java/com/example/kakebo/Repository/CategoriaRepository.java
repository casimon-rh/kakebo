package com.example.kakebo.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.example.kakebo.entity.Categoria;

@ApplicationScoped
@Path("/categoria")
@Produces("application/json")
@Consumes("application/json")
public class CategoriaRepository {

  @Inject
  private KakeboJPAFactory kjf;

  @GET
  public Response getAll() {
    EntityManager em = kjf.getEntityManager();
    CriteriaQuery<Categoria> cq = em.getCriteriaBuilder().createQuery(Categoria.class);
    return Response.ok(em.createQuery(cq.select(cq.from(Categoria.class))).getResultList()).build();
  }
  @GET
  @Path("{id}")
  public Response getOne(@PathParam("id") long id){
    EntityManager em = kjf.getEntityManager();
//    CriteriaBuilder cb = em.getCriteriaBuilder();
//    CriteriaQuery<Categoria> cq = cb.createQuery(Categoria.class);
//    Root<Categoria> root = cq.from(Categoria.class);
//    ParameterExpression<Long> idParam = cb.parameter(Long.class);
//    TypedQuery<Categoria> tq = em.createQuery(cq.where(cb.equal(root.get("id"), idParam)).select(root));
//    tq.setParameter(idParam, id);
//    return Response.ok(tq.getSingleResult()).build();
    return Response.ok(em.find(Categoria.class, id)).build();
  }

  @POST
  @Transactional
  public Response create(Categoria cat) {
    EntityManager em = kjf.getEntityManager();
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.persist(cat);
    tx.commit();
    return Response.ok().build();
  }

  @DELETE
  @Transactional
  @Path("{id}")
  public Response delete(@PathParam("id") long id) {
    EntityManager em = kjf.getEntityManager();
    Categoria cat = em.find(Categoria.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    em.remove(cat);
    tx.commit();
    return Response.ok().build();
  }

  @PUT
  @Transactional
  @Path("{id}")
  public Response update(@PathParam("id") long id, Categoria cate) {
    EntityManager em = kjf.getEntityManager();
    Categoria cat = em.find(Categoria.class, id);
    EntityTransaction tx = em.getTransaction();
    tx.begin();
    cat.setName(cate.getName());
    tx.commit();
    return Response.ok().build();
  }



}
