package com.realdolmen.air.beans;

import org.slf4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

/**
 * Convenience Backing Bean which combines some handy features
 * related to redirects and error pages.
 */
@Named
@RequestScoped
public class RedirectionBean {

    @Inject
    private Logger logger;

    /**
     * Redirect to the home page of the site.
     * Used in /index.xhtml, so that requests on the root url go to the correct page.
     *
     * @throws IOException if an input/output error occurs
     */
    public void doRedirect() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("./redo-public/search-flight.jsf");
    }

    /**
     * Redirect to the specified url.
     *
     * Fails gracefully when an IOException occurs.
     * (A log will printed)
     */
    public void doRedirect(String url) throws IOException {
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect(url);
        }
        catch(IOException ex){
            logger.error("Error occurred while trying to redirect to 404 page", ex);
        }
    }

    /**
     * Send a 404 Page Not Found response.
     *
     */
    public void throw404() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            context.getExternalContext().responseSendError(404, "The resource you requested does not exist");
            context.responseComplete();
        }
        catch(IOException ex){
            logger.error("Error occurred while trying to redirect to 404 page", ex);
        }
    }
}
