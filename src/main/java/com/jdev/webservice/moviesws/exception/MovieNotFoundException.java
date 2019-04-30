package com.jdev.webservice.moviesws.exception;

import org.springframework.ws.soap.server.endpoint.annotation.FaultCode;
import org.springframework.ws.soap.server.endpoint.annotation.SoapFault;

@SoapFault(faultCode = FaultCode.CUSTOM,
        customFaultCode = "{" + MovieNotFoundException.NAMESPACE_URI + "}custom_fault",
        faultStringOrReason = "@faultString")
public class MovieNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;
    public static final String NAMESPACE_URI = "http://javaspringclub.com/exception";

    public MovieNotFoundException(String message) {
        super(message);
    }
}