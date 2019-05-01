package com.jdev.webservice.moviesws.endpoint;

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
    public GetMovieByIdResponse getMovieById(@RequestPayload GetMovieByIdRequest request) {
        return mapper.mapGetMovieByIdResponse(service.isPresent(request.getMovieId()),service.findById(request.getMovieId()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMovieRequest")
    @ResponsePayload
    public AddMovieResponse addMovie(@RequestPayload AddMovieRequest request){
        return mapper.mapAddMovieResponse(service.saveMovie(request),service.findByTitle(request.getTitle()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllMoviesRequest")
    @ResponsePayload
    public GetAllMoviesResponse getAll(@RequestPayload GetAllMoviesRequest request){
        return mapper.mapGetAllMoviesResponse(service.getAll());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
    @ResponsePayload
    public DeleteMovieResponse deleteMovieById(@RequestPayload DeleteMovieRequest request){
        return mapper.mapDeleteMovieResponse(service.deleteById(request.getMovieId()));
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMovieRequest")
    @ResponsePayload
    public UpdateMovieResponse updateMovie(@RequestPayload UpdateMovieRequest request) {
        return mapper.mapUpdateMovieResponse(service.updateMovie(request),request);
    }
}
