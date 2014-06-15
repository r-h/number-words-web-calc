package io.github.r_h.simple_web_calc.servlet;

import io.github.r_h.simple_web_calc.logic.expression.ExpressionsParser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SimpleWebCalcServlet
 */
public class NumberWordsWebCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger
			.getLogger(NumberWordsWebCalcServlet.class);

	private static final String EXPRESSION_PARAMETER_KEY = "expressions";
	
	private static final String INITIAL_DEMO_EXPRESSIONS = "5 + 6\nsechs durch drei\nvier mal einhundertvierundachtzigtausendzweihundertzweiundvierzig";

	private static final String STATIC_HTML = ""
			+ "<!doctype html>\n"
			+ "<html>\n"
			+ "	<head>"
			+ "   <meta charset=\"utf-8\">"
			+ "   <title>NumberWordsWebCalc, a calculator that can process German number words.</title>\n"
			+ "   <meta content=\"IE=edge,chrome=1\" http-equiv=\"X-UA-Compatible\">"
			+ "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
			+ "   <link href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\" rel=\"stylesheet\">"
			+ " </head>"
			+ "	<body>\n"
			+ "	<div class=\"container\" role=\"main\">\n"
			+ "  <div class=\"jumbotron\">"
			+ "	  <h1>NumberWordsCalc</h1>"
			+ "	  <h3>Please enter a simple expression either numerical or in german words e.g. '3 + 4' or 'vierhundert mal 2'.</h3>\n"
			+ "	  <h4>Each expression can have 2 operands (integer numbers starting from -9.999.999 to 9.999.999 in german words numerical) and an operator (+, -, *, / or in german words e.g. 'mal', 'durch').</h4>\n"
			+ "	  <h5>Operands and operators have to be sperated by space characters.</h5>\n"
			+ "	  <h6>Each line can contain one expression, you can enter multiple lines.</h6>\n"
			+ "   <p class=\"bg-danger\">%ERRORS%</p>"
			+ "	  <b>Result is :%RESULT%</b><br />"
			+ "	  <form method=\"post\" accept-charset=\"UTF-8\" role=\"form\">\n"
			+ "      <div class=\"form-group\">"
			+ "	       <textarea name=\"expressions\" class=\"form-control\">%EXPRESSIONS%</textarea><br />\n"
			+ "      </div>"
			+ "      <div class=\"form-group\">"
			+ "	       <button type=\"submit\" class=\"btn btn-primary btn-lg\" role=\"button\">Submit</button>"
			+ "      </div>"
			+ "   </form>\n"
			+ "  </div>\n"
			+ "	</div>\n"
			+ "	</body>\n" 
			+ "</html>\n";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("In Servlet GET");
		String resultString = null;
		String errors = "";
		String resultHtml = STATIC_HTML;
		String expressions = request.getParameter(EXPRESSION_PARAMETER_KEY);
		if (expressions == null) {
			expressions = INITIAL_DEMO_EXPRESSIONS;
		} else {
			LOG.debug("Got expressions, try to evaluate...");
			try {
				resultString = String
						.valueOf(new ExpressionsParser(expressions).parse());
			} catch (Exception e) {
				errors = e.getMessage();
			}
		}
		resultHtml = replaceTokenWithString(resultHtml, "%RESULT%", resultString);

		resultHtml = replaceTokenWithString(resultHtml, "%ERRORS%", errors);
		
		resultHtml = replaceTokenWithString(resultHtml, "%EXPRESSIONS%", expressions);

		response.getWriter().append(resultHtml);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOG.debug("In Servlet POST");
		doGet(request, response);
	}

	/**
	 * Replaces the token in the text with replacement, if the replacement is
	 * different from null. Otherwise just removes the token from text
	 * 
	 * @param text a text
	 * @param token a token
	 * @param replacement a replacement
	 * @return the result
	 */
	private String replaceTokenWithString(String text, String token,
			String replacement) {
		String result;
		if (replacement != null) {
			result = text.replace(token, replacement);
		} else {
			result = text.replace(token, "");
		}
		return result;
	}
}
