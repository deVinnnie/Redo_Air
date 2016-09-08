package com.realdolmen.air.beans;

import com.realdolmen.air.domain.Flight;
import com.realdolmen.air.domain.TravelClass;
import com.realdolmen.air.service.FlightServiceBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@Named
@RequestScoped
public class FlightBean {

    @NotNull
    @Min(0)
    private Long flightId;

    private Flight flight;

    private List<TravelClass> travelClassSet;

    @Inject
    private FlightServiceBean flightServiceBean;

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public List<TravelClass> getTravelClassSet() {
        return travelClassSet;
    }

    public void setTravelClassSet(List<TravelClass> travelClassSet) {
        this.travelClassSet = travelClassSet;
    }

    public Long getFlightId() {
        return flightId;
    }

    public void setFlightId(Long flightId) {
        this.flightId = flightId;
    }

    /*public void save(){
        System.out.println("Flight: " + flight.getId());

        for(TravelClass t : flight.getTravelClasses()){
            System.out.println(t.getName() + " - " + t.getOverriddenPrice());
        }

        this.flightServiceBean.update(flight);
    }*/

    public void onParametersLoaded() throws IOException {
        this.flight = flightServiceBean.findFlightById(flightId);

        if(flight == null){
            FacesContext context= FacesContext.getCurrentInstance();
            context.getExternalContext().responseSendError(404, "The flight you requested does not exist");
            context.responseComplete();
        }
        else{
            this.travelClassSet = flightServiceBean.findTravelClasses(flightId);
        }
    }
}
