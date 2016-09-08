package com.realdolmen.air.beans;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@ManagedBean
public class LoginBean implements Serializable {

    private boolean loginFailure = false;

    public LoginBean(){
    }

    public boolean isLoginFailure() {
        return loginFailure;
    }

    public void setLoginFailure(boolean loginFailure) {
        this.loginFailure = loginFailure;
    }
}
