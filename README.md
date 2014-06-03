# simple-web-calc

A simple calculator as an webapp written in Java

# About

This is a simple web-based calculator which can parse and evaluate simple
expressions like `4 * 5`, `675 -3` and also expressions given as german words
e.g. `dreihundertzweiundneunzig mal sieben` and even 'mixed' expressions
like `dreihundertzweiundneunzig mal 7`.

I wrote this some years ago as a respond to a test for an job application.
The orginal requirements can be found [here](REQUIREMENTS.md).


# Prerequisites
You will need :

an JDK >= 7, like [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/install/index.html),

[Maven >= 3](http://maven.apache.org/download.cgi),

 a working internet connection, so that maven can download missing deps.
 
An IDE is recommended, but not required
e.g. [Eclipse](http://www.eclipse.org/) with [Maven-Plugin](https://www.eclipse.org/m2e/), [Run-Jetty-Run](https://code.google.com/p/run-jetty-run/)

# Building and Running
In the project folder do a

`    mvn install`

to compile and package the project.

The resulting WAR under the target folder should run in virtually every
servlet container that supports servlet spec 3.0

To run the app in place, do a 

`    mvn jetty:run`

you then should be able to connect with your web-browser
to 'http://localhost:8080/'

Alternatively you can run the project from your favorite IDE
like [Eclipse](http://www.eclipse.org/) with e.g. [Run-Jetty-Run](https://code.google.com/p/run-jetty-run/)
