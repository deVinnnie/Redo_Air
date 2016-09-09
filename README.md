ReDo Air
========

[![Build Status](https://travis-ci.org/deVinnnie/Redo_Air.svg?branch=master)](https://travis-ci.org/deVinnnie/Redo_Air)

Adapted from [jee7-starter](https://github.com/kvanrobbroeck/jee7-starter).


Running with Integration Tests
------------------------------

    mvn clean package -Dintegration
    
Running with Integration Tests and Selenium Tests
-------------------------------------------------

Set the webdriver.gecko.driver property to the correct path.
(See [https://www.seleniumeasy.com/selenium-tutorials/launching-firefox-browser-with-geckodriver-selenium-3](https://www.seleniumeasy.com/selenium-tutorials/launching-firefox-browser-with-geckodriver-selenium-3))

    mvn clean package -Dwebdriver.gecko.driver=C:\Users\..\bin\geckodriver.exe -Dintegration

Activating security realm on Wildfly
------------------------------------

Copy the contents of `/src/main/webapp/WEB-INF/wildfly-standalone-snippet.xml` and insert it into the Wildfly standalone.xml file.

Other useful information
========================

(Taken from original Readme)

Platform
--------
Verified for use on Wildfly 10.0.0.Final. Apply the "wildfly-10.0.0.Final-diff.zip" file over a clean wildfly 10.0.0 installation to set things up correctly.

Handy features
--------------
    * Unit tests with either H2 or mysql (-DdatabaseEngine=h2)
    * DBUnit data loading in data.xml
    * Unit tests for remote EJB and JMS interaction enabled only when passing -Dintegration
    * Support for Travis CI

Prerequisites
-------------
    * JDK 8
    * Maven 3
    * MySQL server (schema "realdolmen", username="root", password="")

Resources provided by wildfly configuration
-------------------------------------------
    * JMS Queue (non-durable): java:jboss/exported/rd/queues/RealDolmenQueue
    * JTA Datasource java:/rd/queues/RealDolmenDataSource (jdbc:mysql://localhost:3306/realdolmen user: root password: (blank)
    * Administrator user for management and application user: administrator/Azerty123! (used for admin console, and JMS session authentication)