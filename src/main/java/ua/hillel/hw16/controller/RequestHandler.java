package ua.hillel.hw16.controller;

import jakarta.servlet.http.HttpServletRequest;

import java.io.PrintWriter;
import java.util.Enumeration;

public class RequestHandler {

    static  String br = "<br>";

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
}
