# number-words-web-calc Build Status: TBD.. Gitlab CI to rescue !

A calculator that can process German number words as a webapp.

# About

This is a web-based calculator which can evaluate simple
expressions like `4 * 5`, `675 - 3` and also expressions given as German number words
e.g. `dreihundertzweiundneunzig mal sieben` and even 'mixed' expressions
like `zweiundneunzig mal 7`.

![number-words-web-calc](https://github.com/r-h/number-words-web-calc/raw/master/nwwc_screenshot.png)

I wrote this some years ago as a respond to a test for a job application.
The orginal requirements can be found [here](REQUIREMENTS.md).


# Prerequisites
You will need :

a JDK >= 7, like [Oracle JDK](http://www.oracle.com/technetwork/java/javase/downloads/index.html) or [OpenJDK](http://openjdk.java.net/install/index.html),

[Maven >= 3](http://maven.apache.org/download.cgi),

a working internet connection, so that maven can download missing deps.

An IDE is recommended, but not required
e.g. [Eclipse](http://www.eclipse.org/) with [Maven-Plugin](https://www.eclipse.org/m2e/), [Run-Jetty-Run](https://code.google.com/p/run-jetty-run/)

# Building and Running
After cloning this repo, in the project folder do a

`    mvn clean package`

to compile and package the project.

The resulting WAR under the target folder should run in virtually every
servlet container that supports servlet spec 3.1

To run the app in place, do a

`    mvn jetty:run`

you then should be able to connect with your web-browser
to 'http://localhost:8080/'

Alternatively you can run the project from your favorite IDE
like [Eclipse](http://www.eclipse.org/) with e.g. [Run-Jetty-Run](https://code.google.com/p/run-jetty-run/)

# How it works

The program consists of a servlet responsible for the input of the user data in an HTTP-Request and the output in a HTML-Page.

There are also several classes which handle the business logic of the evaluations of the expressions.

The program takes the user input which was entered in the web-page in an text-area via HTTP-Request parameter "expressions" in the servlet.
The servlet delegates the parsing of the expression to the `ExpressionsParser`.

This delegates the splitting of the expressions to the `ExpressionsTokenizer`.
This one splits the expressions on line-breaks and feeds the parts to the `ExpressionParser`, after some clean ups like removing empty strings and control characters.
The `ExpressionParser` splits the single expression into its parts, which are recognized by its separating space characters. After removing empty parts,
the `ExpressionParser` tries to identify the 3 valid parts of an expression, which are: two numbers and an operator.

For recognizing the numbers, the `NumberParser` is used. If no numeric number is detected, the `NumberParser` delegates the parsing of German number words to the `NumberWordParser`.
This one splits the German number word into its parts using the `NumberWordTokenizer`. The `NumberWordTokenizer` creates a list of `ITokens` which can be a `Number` or an `Operator`.
After doing this, the `NumberWordParser` calculates the numerical value of the German number word. So if the `ExpressionParser` could identify two valid numbers in the range of -9.999.999 and 9.999.999
and is a valid operator ('+', '-', '*', '/' or german words 'plus', 'minus', 'mal', 'durch') present, the expression is finally to be calculated.
The `ExpressionsParser` iterates over the list of expressions and tries to calculate them all. The results and the error messages are finally written to the resulting HTML-page in the servlet.

# Known issues

The syntax checking of the number-word parser is very relaxed, it excepts German fantasy words like 'nullhundertzwanzig' and delimiter symbols in numbers at any position.

Unary 'plus' is not implemented

Error messages are quite basic.

