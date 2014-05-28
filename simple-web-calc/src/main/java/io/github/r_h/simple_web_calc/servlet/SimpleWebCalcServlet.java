package io.github.r_h.simple_web_calc.servlet;

import io.github.r_h.simple_web_calc.logic.expression.ExpressionsParser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class SimpleWebCalcServlet
 */
@WebServlet(urlPatterns = { "/" })
public class SimpleWebCalcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = Logger
			.getLogger(SimpleWebCalcServlet.class);

	private static final String EXPRESSION_PARAMETER_KEY = "expressions";
	
	private static final String INITIAL_DEMO_EXPRESSIONS = "5 + 6\nsechs durch drei\nvier mal einhundertvierundachtzigtausendzweihundertzweiundvierzig";

	private static final String STATIC_HTML = ""
			+ "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n"
			+ "<html>\n"
			+ "	<head><title>SimpleWebCalc, a simple web calculator.</title>\n"
			+ "   <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">"
			+ "   </head>"
			+ "	<body>\n"
			+ "	<h1>SimpleWebCalc</h1><br />\n"
			+ "	<h2>Please enter a simple expression either numerical or in german words e.g. '3 + 4' or 'vierhundert mal 2'.</h2><br />\n"
			+ "	<h3>Each expression can have 2 operands (integer numbers starting from -9.999.999 to 9.999.999 in german words numerical) and an operator (+, -, *, / or in german words e.g. 'mal', 'durch').</h3><br />\n"
			+ "	<h3>Operands and operators have to be sperated by space characters.</h3><br />\n"
			+ "	<h4>Each line can contain one expression, you can enter multiple lines.</h4><br />\n"
			+ "   <font color=\"#CC0000\" size=\"2\">%ERRORS%</font><br />"
			+ "	<b>Result is :%RESULT%</b><br />"
			+ "	<form method=\"post\" accept-charset=\"UTF-8\">\n"
			+ "	<textarea name=\"expressions\" rows=\"10\" cols=\"100\">%EXPRESSIONS%</textarea><br />\n"
			+ "	<input type=\"submit\" name=\"submit\" value=\"Submit\" /></form>\n"
			+ "	</body>\n" + "</html>\n";

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
	 * @param text
	 *            a text
	 * @param token
	 *            a token
	 * @param replacement
	 *            a replacement
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
