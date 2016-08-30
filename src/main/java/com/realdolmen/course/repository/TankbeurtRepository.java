package com.realdolmen.course.repository;



import com.realdolmen.course.domain.Tankbeurt;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TankbeurtRepository {

    @PersistenceContext
    EntityManager em;

    public Tankbeurt save(Tankbeurt tankbeurt) {
        em.persist(tankbeurt);
        return tankbeurt;
    }

    public Tankbeurt findById(Long id) {
        return em.find(Tankbeurt.class, id);
    }

    public List<Tankbeurt> findAll() {
        return em.createQuery("select t from Tankbeurt t", Tankbeurt.class).getResultList();
    }

    public void remove(long tankbeurtId) {
        em.remove(em.getReference(Tankbeurt.class, tankbeurtId));
    }

    public Tankbeurt findLast(String nummerplaat) {
        List<Tankbeurt> tankbeurten = em.createQuery("SELECT t FROM Tankbeurt t WHERE t.nummerplaat = :nummerplaat ORDER BY t.datum desc", Tankbeurt.class)
                .setParameter("nummerplaat", nummerplaat)
                .setMaxResults(1)
                .getResultList();

        if(tankbeurten.isEmpty()){
            return null;
        }
        else {
            return tankbeurten.get(0);
        }
    }
}
