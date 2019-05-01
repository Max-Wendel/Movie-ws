package com.jdev.webservice.moviesws.service;

import com.jdev.webservice.moviesws.exception.MovieNotFoundException;
import com.jdev.webservice.moviesws.generate.AddMovieRequest;
import com.jdev.webservice.moviesws.generate.MovieType;
import com.jdev.webservice.moviesws.generate.ServiceStatus;
import com.jdev.webservice.moviesws.generate.UpdateMovieRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class MovieService {

    private final ServiceStatus OK = new ServiceStatus("OK","SUCCESS");
    private final ServiceStatus ERROR = new ServiceStatus("ERROR","FAILURE");

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

    public MovieType findById(long id) {
        for (MovieType movie : movies) {
            if (movie.getMovieId() == id)
                return movie;
        }
        return null;
    }

    public MovieType findByTitle(String title){
        int pos = findPositionTitle(title);
        if (pos == -1){
            return null;
        }else {
            return movies.get(pos);
        }
    }

    private int findPositionTitle(String title){
        int pos = -1;

        for (MovieType movie:movies){
            pos++;
            if (movie.getTitle().equals(title)){
                return pos;
            }
        }
        return -1;
    }

    public List<MovieType> getAll() {
        return movies;
    }

    public ServiceStatus deleteById(long id) {
        Iterator iterator = movies.iterator();
        MovieType movie;
        while (iterator.hasNext()) {
            movie = (MovieType) iterator.next();
            if (movie.getMovieId() == id) {
                iterator.remove();

                return OK;
            }
        }
        return ERROR;
    }

    public ServiceStatus saveMovie(AddMovieRequest request){
        MovieType newMovie = new MovieType();
        newMovie.setTitle(request.getTitle());
        newMovie.setCategory(request.getCategory());
        movies.add(newMovie);
        return OK;
    }

    public ServiceStatus updateMovie(UpdateMovieRequest request){
        int pos = findPositionTitle(request.getTitle());

        if (pos == -1){
            return ERROR;
        }else {
            movies.get(pos).setTitle(request.getTitle());
            movies.get(pos).setCategory(request.getCategory());
        }
        return OK;
    }

}
