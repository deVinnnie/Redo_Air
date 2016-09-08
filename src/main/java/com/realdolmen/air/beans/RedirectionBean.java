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

    public void throw404() throws IOException {
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().responseSendError(404, "The resource you requested does not exist");
        context.responseComplete();
    }
}
