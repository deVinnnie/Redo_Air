//package com.realdolmen.air.converter;
//
//import com.realdolmen.air.domain.AirlineCompany;
//import com.realdolmen.air.repository.AirlineCompanyRepository;
//import com.realdolmen.air.service.AirlineCompanyServiceBean;
//
//import javax.ejb.EJB;
//import javax.ejb.Stateless;
//import javax.faces.application.FacesMessage;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.ConverterException;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//import java.util.List;
//
//
//@FacesConverter("airlineCompanyConverter")
//public class AirlineCompanyConverter implements Converter{
//    @Inject
//    private AirlineCompanyRepository airlineCompanyRepository;
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uiComponent, String s) {
//        if(s != null && s.trim().length()>0){
//            try{
//                return airlineCompanyRepository.findById(Long.parseLong(s));
//            }catch (NumberFormatException e){
//                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
//            }
//        }
//        else {
//            return null;
//        }
//    }
//
//    @Override
//    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
//        if(object != null) {
//            return String.valueOf(((AirlineCompany) object).getId());
//        }
//        else {
//            return null;
//        }
//    }
//}
