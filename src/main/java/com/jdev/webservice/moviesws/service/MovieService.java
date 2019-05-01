package com.jdev.webservice.moviesws.service;

import com.jdev.webservice.moviesws.generate.MovieType;
import com.jdev.webservice.moviesws.generate.ServiceStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MovieService {

    private static List<MovieType> movies = new ArrayList<>();

    static {
        MovieType movie1 = new MovieType();
        movie1.setMovieId(101);
        movie1.setTitle("Mission Impossible");
        movie1.setCategory("Action");

        movies.add(movie1);

        MovieType movie2 = new MovieType();
        movie2.setMovieId(102);
        movie2.setTitle("Argo");
        movie2.setCategory("Drama");
        movies.add(movie2);

        MovieType movie3 = new MovieType();
        movie3.setMovieId(103);
        movie3.setTitle("Hang over");
        movie3.setCategory("Comedy");
        movies.add(movie3);

    }

    // Get Movie By Id - 1
    public MovieType findById(long id) {
        for (MovieType movie : movies) {
            if (movie.getMovieId() == id)
                return movie;
        }
        return null;
    }

    // Get All movies
    public List<MovieType> findAll() {
        return movies;
    }

    public ServiceStatus deleteById(int id) {
        Iterator iterator = movies.iterator();
        ServiceStatus status = new ServiceStatus();
        MovieType movie;
        while (iterator.hasNext()) {
            movie = (MovieType) iterator.next();
            if (movie.getMovieId() == id) {
                iterator.remove();

                status.setStatusCode("OK");
                status.setMessage("SUCCESS");
                return status;
            }
        }
        status.setStatusCode("ERROR");
        status.setMessage("FAILURE");
        return status;
    }

}
