package ua.hillel.hw16.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class RequestHandler {

    static String br = "<br>";
    private static StringBuffer locked = new StringBuffer();

    public static int getRequestInfo(PrintWriter printWriter, HttpServletRequest req, int counter) {
        printWriter.println(br + "Request number: " + ++counter);
        printWriter.println(br + "Request: " + req.getRequestURI());
        printWriter.println(br + "Method: " + req.getMethod());
        printWriter.println(br + "Path: " + req.getRequestURI());
        printWriter.println(br + "Protocol: " + req.getProtocol());
        printWriter.println(br + "Query: " + req.getQueryString());
        printWriter.println(br + "RemoteAddr: " + req.getRemoteAddr());
        Enumeration headerNames = req.getHeaderNames();
        printWriter.println(br + "Headers: " + headerNames);
        while (headerNames.hasMoreElements()) {
            String headerName = (String) headerNames.nextElement();
            String headerValue = req.getHeader(headerName);
            printWriter.println(br + "\t" + headerName + ": " + headerValue);
        }
        return counter;
    }


    public static void generate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try (PrintWriter printWriter = response.getWriter()) {
            String name, value;
            printWriter.print("<HTML><HEAD>");
            printWriter.print("<TITLE>Result</TITLE>");
            printWriter.print("</HEAD><BODY>");
            printWriter.print("<TABLE BORDER=3>");
            Enumeration names = request.getParameterNames();
            while (names.hasMoreElements()) {
                name = (String) names.nextElement();
                value = request.getParameterValues(name)[0];
                printWriter.print("<TR>");
                printWriter.print("<TD>" + name + "</TD>");
                printWriter.print("<TD>" + value + "</TD>");
                printWriter.print("</TR>");
            }
            printWriter.print("</TABLE></BODY></HTML>");
        }
    }

    public static String sync() {
        final String STR = "SYNCHRONIZATION";
        try {
            for (int i = 0; i < STR.length(); i++) {
                locked.append(STR.charAt(i));
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result = locked.toString();
        locked.delete(0, STR.length() - 1);
        return result;
    }
}
