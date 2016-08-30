JEE7 Starter
============

[![Build Status](https://travis-ci.org/kvanrobbroeck/jee7-starter.svg?branch=master)](https://travis-ci.org/kvanrobbroeck/jee7-starter)

This project serves as a demo/template for JEE6 and JEE7 development. It is used as a starting point for a number of RealDolmen courses.

Version 2.0 is now heavily simplified, to make use of the trends in JEE7 to allow more simplified project packaging such
as web-profile only.

RealDolmen courses
------------------
    * Developing Java Enterprise Applications with JEE 6
    * Building Enterprise Applications with JEE 7
    * Enterprise JavaBeans (EJB) 3.1
    * Java Enterprise Component Development with JPA 2 and EJB 3.1
    * Java Persistence API (JPA) 2
    * Java Server Faces 2

Demonstrated features
---------------------
    * JEE7
    * EJB
    * CDI
    * JSF
    * JPA
    * JMS
    * Remote JNDI using unit tests (run them with -Dintegration or they will be skipped)
    * JAX-RS web service
    * JAX-WS web service
    * Servlets & JSP

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