package com.jdev.webservice.moviesws.map;

import com.jdev.webservice.moviesws.generate.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper{

    public GetMovieByIdResponse map(MovieType movie) {
        GetMovieByIdResponse response = new GetMovieByIdResponse();
        response.setMovieType(movie);
        return response;
    }

    public GetAllMoviesResponse map(List<MovieType> movies){
        GetAllMoviesResponse response = new GetAllMoviesResponse();
        response.setMovieType(movies);
        return response;
    }

    public DeleteMovieResponse mapD(ServiceStatus status){
        DeleteMovieResponse response = new DeleteMovieResponse();
        response.setServiceStatus(status);
        return response;
    }

    public AddMovieResponse map(ServiceStatus status, AddMovieRequest request){
        AddMovieResponse response = new AddMovieResponse();
        response.setServiceStatus(status);
        response.setMovieType(map(request));
        return response;
    }

    public UpdateMovieResponse mapU(ServiceStatus serviceStatus){
        UpdateMovieResponse response = new UpdateMovieResponse();
        response.setServiceStatus(serviceStatus);
        return response;
    }

    private MovieType map(AddMovieRequest request){
        MovieType movieType = new MovieType();
        movieType.setCategory(request.getCategory());
        movieType.setTitle(request.getTitle());
        return  movieType;
    }
}
