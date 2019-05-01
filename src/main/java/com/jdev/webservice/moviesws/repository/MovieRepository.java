package com.jdev.webservice.moviesws.repository;

import com.jdev.webservice.moviesws.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    Optional<Movie> findMovieByTitle(String title);
}
