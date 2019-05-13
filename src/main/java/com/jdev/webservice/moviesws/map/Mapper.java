package com.jdev.webservice.moviesws.map;

import com.jdev.webservice.moviesws.generate.GetAllMoviesResponse;
import com.jdev.webservice.moviesws.generate.GetMovieByIdResponse;
import com.jdev.webservice.moviesws.generate.MovieType;
import com.jdev.webservice.moviesws.generate.DeleteMovieResponse;
import com.jdev.webservice.moviesws.generate.AddMovieResponse;
import com.jdev.webservice.moviesws.generate.UpdateMovieRequest;
import com.jdev.webservice.moviesws.generate.UpdateMovieResponse;
import com.jdev.webservice.moviesws.generate.ServiceStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper{

    public GetMovieByIdResponse mapGetMovieByIdResponse(ServiceStatus status, MovieType movie) {
        GetMovieByIdResponse response = new GetMovieByIdResponse();
        response.setServiceStatus(status);
        response.setMovieType(movie);
        return response;
    }

    public GetAllMoviesResponse mapGetAllMoviesResponse(List<MovieType> movies){
        GetAllMoviesResponse response = new GetAllMoviesResponse();
        response.setMovieType(movies);
        return response;
    }

    public DeleteMovieResponse mapDeleteMovieResponse(ServiceStatus status){
        DeleteMovieResponse response = new DeleteMovieResponse();
        response.setServiceStatus(status);
        return response;
    }

    public AddMovieResponse mapAddMovieResponse(ServiceStatus status, MovieType movie){
        AddMovieResponse response = new AddMovieResponse();
        response.setServiceStatus(status);
        response.setMovieType(movie);
        return response;
    }

    public UpdateMovieResponse mapUpdateMovieResponse(ServiceStatus serviceStatus, UpdateMovieRequest request){
        UpdateMovieResponse response = new UpdateMovieResponse();
        response.setServiceStatus(serviceStatus);
        response.setMovieType(map(request));
        return response;
    }

    private MovieType map(UpdateMovieRequest request){
        MovieType movieType = new MovieType();
        movieType.setCategory(request.getCategory());
        movieType.setTitle(request.getTitle());
        return  movieType;
    }
}
