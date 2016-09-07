package com.realdolmen.air.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class RedirectionBean {

    public void doRedirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./redo-public/search-flight.jsf");
    }
}
