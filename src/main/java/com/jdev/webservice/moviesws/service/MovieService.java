package com.jdev.webservice.moviesws.service;

import com.jdev.webservice.moviesws.generate.AddMovieRequest;
import com.jdev.webservice.moviesws.generate.MovieType;
import com.jdev.webservice.moviesws.generate.ServiceStatus;
import com.jdev.webservice.moviesws.generate.UpdateMovieRequest;
import com.jdev.webservice.moviesws.model.Movie;
import com.jdev.webservice.moviesws.repository.MovieRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final ServiceStatus okStatus = new ServiceStatus("okStatus", "SUCCESS");
    private final ServiceStatus notFoundStatus = new ServiceStatus("ERROR", "MOVIE_NOT_FOUND");
    private final ServiceStatus alreadyExistStatus = new ServiceStatus("ERROR", "MOVIE_ALREADY_EXIST");

    @Autowired
    private MovieRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    private static List<MovieType> movies = new ArrayList<>();

    public MovieType findById(long id) {
        Optional<Movie> movie = repository.findById(id);
        return movie.map(value -> modelMapper.map(value, MovieType.class)).orElse(null);
    }

    public MovieType findByTitle(String title) {
        Optional<Movie> movie = repository.findMovieByTitle(title);
        return movie.map(value -> modelMapper.map(value, MovieType.class)).orElse(null);
    }

    public ServiceStatus isPresent(long id){
        Optional<Movie> movie = repository.findById(id);
        if (movie.isPresent()){
            return okStatus;
        }else {
            return notFoundStatus;
        }
    }

    public List<MovieType> getAll() {
        List<MovieType> result = new ArrayList<>();
        List<Movie> movies = repository.findAll();

        for (Movie movie:movies){
            result.add(modelMapper.map(movie, MovieType.class));
        }

        return result;
    }

    public ServiceStatus deleteById(long id) {
        MovieType movie = findById(id);
        if (movie != null){
            repository.deleteById(id);
            return okStatus;
        }
        return notFoundStatus;
    }

    public ServiceStatus saveMovie(AddMovieRequest request){
        Optional<Movie> movie = repository.findMovieByTitle(request.getTitle());

        if (movie.isPresent()){
            return alreadyExistStatus;
        }

        MovieType newMovie = new MovieType();
        newMovie.setTitle(request.getTitle());
        newMovie.setCategory(request.getCategory());

        repository.save(map(newMovie));

        return okStatus;
    }

    public ServiceStatus updateMovie(UpdateMovieRequest request){
        Optional<Movie> movie = repository.findMovieByTitle(request.getTitle());

        if (!movie.isPresent()){
            return notFoundStatus;
        }

        MovieType newMovie = new MovieType();
        newMovie.setTitle(request.getTitle());
        newMovie.setCategory(request.getCategory());

        Movie movieToUpdate = map(newMovie);
        movieToUpdate.setMovieId(movie.get().getMovieId());

        repository.save(movieToUpdate);

        return okStatus;
    }

    private Movie map(MovieType movieType){
        return modelMapper.map(movieType, Movie.class);
    }

}
