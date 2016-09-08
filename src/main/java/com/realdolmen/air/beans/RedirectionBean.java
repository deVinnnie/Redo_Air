package com.realdolmen.air.beans;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;

/**
 * Convenience Backing Bean which combines some handy features
 * related to redirects and error pages.
 */
@Named
@RequestScoped
public class RedirectionBean {

    /**
     * Redirect to the home page of the site.
     * Used in /index.xhtml, so that requests on the root url go to the correct page.
     *
     * @throws IOException
     */
    public void doRedirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./redo-public/search-flight.jsf");
    }

    /**
     * Send a 404 Page Not Found response.
     *
     * @throws IOException
     */
    public void throw404() throws IOException {
        FacesContext context= FacesContext.getCurrentInstance();
        context.getExternalContext().responseSendError(404, "The resource you requested does not exist");
        context.responseComplete();
    }
}
