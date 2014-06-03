# simple-web-calc
  ===============

A simple calculator as an webapp written in Java

# About
I wrote this some years ago as a respond to a test for an job application.


# Prerequisites
You will need :
an JDK >= 7, like Oracle JDK or OpenJDK,
Maven >= 3,
 a working internet connection, so that maven can download missing deps.
An IDE is recommended, but not required
e.g. Eclipse with Maven-Plugin, Run-Jetty-Run

# Building and Running
In the project folder do a
    mvn install
to compile and package the project.
The resulting WAR under the target folder should run in virtually every
servlet container that supports servlet spec 3.0

To run the app in place, do a 
    mvn jetty:run
you then should be able to connect with your web-browser
to 'http://localhost:8080/'

Alternatively you can run the project from your favorite IDE
like Eclipse with e.g. Run-Jetty-Run
