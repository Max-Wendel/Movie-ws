package com.jdev.webservice.moviesws.exception;

import com.jdev.webservice.moviesws.generate.ServiceStatus;

public class ServiceFaultException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private ServiceStatus serviceStatus;

    public ServiceFaultException(String message, ServiceStatus serviceStatus) {
        super(message);
        this.serviceStatus = serviceStatus;
    }

    public ServiceFaultException(String message, Throwable e, ServiceStatus serviceStatus) {
        super(message, e);
        this.serviceStatus = serviceStatus;
    }

    public ServiceStatus getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(ServiceStatus serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

}