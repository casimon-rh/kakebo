package com.example.kakebo.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.example.kakebo.entity.Categoria;

@ApplicationScoped
@Path("/categoria")
@Produces("text/plain")
@Consumes("text/plain")
public class CategoriaRepository {

  @Inject
  private KakeboJPAFactory kjf;

  @GET
  public Response getAll() {
    EntityManager em = kjf.getEntityManager();
    CriteriaQuery<Categoria> cq = em.getCriteriaBuilder().createQuery(Categoria.class);
    return Response.ok(em.createQuery(cq.select(cq.from(Categoria.class))).getResultList()).build();
  }

  @POST
  @Transactional
  public Response create(Categoria cat) {
    EntityManager em = kjf.getEntityManager();
    em.persist(cat);
    em.flush();
    return Response.ok().build();
  }

}