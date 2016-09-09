package com.realdolmen.air.beans;

import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.service.TravelClassServiceBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@ManagedBean
@ViewScoped
public class TravelClassBean {

    @Min(0)
    @NotNull
    private Long travelClassId;

    private TravelClass travelClass;

    @Inject
    TravelClassServiceBean travelClassServiceBean;

    @Inject
    private RedirectionBean redirectionBean;

    /**
     * Method called after request parameters are loaded in JSF page.
     * Defined in .xhtml file with the f:viewAction tag.
     *
     * The PostConstruct annotation doesn't work because the parameters
     * are not yet copied to the instance variables.
     * This would result in faulty initialisation.
     *
     * @throws IOException
     */
    public void onParametersLoaded() throws IOException {
        this.travelClass = travelClassServiceBean.find(travelClassId);

        if(this.travelClass == null){
            redirectionBean.throw404();
        }
    }

    public TravelClass getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(TravelClass travelClass) {
        this.travelClass = travelClass;
    }

    public Long getTravelClassId() {
        return travelClassId;
    }

    public void setTravelClassId(Long travelClassId) {
        this.travelClassId = travelClassId;
    }

    public String save(){
        this.travelClass = this.travelClassServiceBean.update(this.travelClass);
        Flash flash = FacesContext.getCurrentInstance().
                getExternalContext().getFlash();
        flash.put("success", "Success!");

        FacesContext.getCurrentInstance().
                getExternalContext().getFlash().setKeepMessages(true);

        return String.format("flight-detail.xhtml?id=%dfaces-redirect=true",
                travelClass.getFlight().getId()
        );
    }
}
