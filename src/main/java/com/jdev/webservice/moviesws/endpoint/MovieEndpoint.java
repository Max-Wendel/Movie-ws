package com.jdev.webservice.moviesws.endpoint;

import com.jdev.webservice.moviesws.exception.MovieNotFoundException;
import com.jdev.webservice.moviesws.exception.ServiceFaultException;
import com.jdev.webservice.moviesws.generate.*;
import com.jdev.webservice.moviesws.map.Mapper;
import com.jdev.webservice.moviesws.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class MovieEndpoint {
    public static final String NAMESPACE_URI = "http://www.abstract.com/movies-ws";

    @Autowired
    MovieService service;

    @Autowired
    Mapper mapper;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMovieByIdRequest")
    @ResponsePayload
    public GetMovieByIdResponse getMovieById(@RequestPayload GetMovieByIdRequest request) throws MovieNotFoundException {
        MovieType movie = service.findById(request.getMovieId());

        if (movie == null)
            throw new MovieNotFoundException("Invalid Movie Id " + request.getMovieId());

        return mapper.map(movie);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMoviesRequest")
    @ResponsePayload
    public GetAllMoviesResponse getAll(@RequestPayload GetAllMoviesRequest request){
        return mapper.map(service.findAll());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
    @ResponsePayload
    public DeleteMovieResponse deleteMovieById(@RequestPayload DeleteMovieRequest request) {
        String errorMessage = "ERROR";
        ServiceStatus serviceStatus = new ServiceStatus();
        serviceStatus.setStatusCode("NOT_FOUND");
        serviceStatus.setMessage("Movie with id: " + request.getMovieId() + " not found. Cannot delete Movie");

        throw new ServiceFaultException(errorMessage, serviceStatus);
    }
}