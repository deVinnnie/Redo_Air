package com.realdolmen.air.converter;

import com.realdolmen.air.domain.AirlineCompany;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean(name = "airlineCompanyConverter")
@FacesConverter
public class AirlineCompanyConverter implements Converter {
    @PersistenceContext
    private transient EntityManager entityManager;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return entityManager.createNamedQuery(AirlineCompany.FIND_BY_NAME, AirlineCompany.class)
                .setParameter("name", s)
                .getSingleResult();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        System.out.println(o);
        //return ((AirlineCompany) o).getName();
        return null;
    }
}
