package com.jdev.webservice.moviesws.map;

import com.jdev.webservice.moviesws.generate.GetAllMoviesRequest;
import com.jdev.webservice.moviesws.generate.GetAllMoviesResponse;
import com.jdev.webservice.moviesws.generate.GetMovieByIdResponse;
import com.jdev.webservice.moviesws.generate.MovieType;
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
}
