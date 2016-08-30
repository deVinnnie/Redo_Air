package com.realdolmen.course.domain;

import com.realdolmen.course.utilities.persistence.JpaPersistenceTest;
import org.junit.Test;

public class PlaneTest extends JpaPersistenceTest{

    @Test
    public void testCreatePlane(){
        Plane plane = new Plane("Airbus A400S");
        assertNull(plane.getId());
        entityManager().persist(plane);
        Integer planeId = plane.getId();
        assertNotNull(planeId);

        entityManager().flush();
        entityManager().clear();

        Plane persistedPlane = entityManager().find(Plane.class, planeId);
        assertEquals("Airbus A400S", persistedPlane.getType());
    }

    @Test
    public void testDeletePlane(){
        Plane plane = entityManager().find(Plane.class, 60);

        //entityManager().remove();


    }

}
