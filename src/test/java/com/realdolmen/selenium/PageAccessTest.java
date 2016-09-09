package com.realdolmen.selenium;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertNotNull;

public class PageAccessTest extends SeleniumTest{

    private String base_url = "http://localhost:8080/quickstart";

    @Test(expected = NoSuchElementException.class)
    public void homePageShouldNotReturnErrorPage() throws InterruptedException {
        driver().get(base_url + "/redo-public/search-flight.jsf");
        driver().findElement(By.id("error-stack-trace"));
    }

    @Test
    public void xhtmlPageShouldNotReturnErrorPage() throws InterruptedException {
        driver().get(base_url + "/redo-public/search-flight.xhtml");
        WebElement element = driver().findElement(By.id("error-stack-trace"));
        assertNotNull(element);
    }

    @Test
    public void adminPagesShouldNotBeAccessableToPublic() throws InterruptedException {
        driver().get(base_url + "/redo-admin/index.jsf");
        WebElement element = driver().findElement(By.id("login-form"));
        assertNotNull(element);
    }
}
