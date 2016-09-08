package com.realdolmen.air.web.controller;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/**
 * Backing Bean to change the Language of the site.
 */
@Named
@SessionScoped
public class LanguageBean implements Serializable {
    private static final long serialVersionUID = 2756934361134603857L;

    private Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

    private List<Locale> locales = new ArrayList<>();

    @PostConstruct
    public void setUp(){
        // Get ALL supported locales (Supported + Default)
        Locale defaultLocale = FacesContext.getCurrentInstance().getApplication().getDefaultLocale();
        locales.add(defaultLocale);

        Iterator<Locale> supportedLocales = FacesContext.getCurrentInstance().getApplication().getSupportedLocales();

        while (supportedLocales.hasNext()) {
            locales.add(supportedLocales.next());
        }
    }


    /**
     * Sets the current {@code Locale} for each user session
     *
     * @param language - ISO-639 language code
     * @return A JSF redirect to the original page.
     */
    public String changeLanguage(String language) {
        locale = new Locale(language);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
        String viewId = FacesContext.getCurrentInstance().getViewRoot().getViewId();
        return viewId + "?faces-redirect=true";
    }

    public Locale getLocale() {
        return locale;
    }

    public String getLanguage() {
        return locale.getLanguage();
    }

    public List<Locale> getLocales(){
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }
}
