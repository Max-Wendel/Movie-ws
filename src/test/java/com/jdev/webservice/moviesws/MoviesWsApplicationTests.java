package com.jdev.webservice.moviesws;

import com.jdev.webservice.moviesws.generate.MovieType;
import com.jdev.webservice.moviesws.model.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesWsApplicationTests {

	@Autowired
	ModelMapper mapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testMovieMapToMovieType(){
		Movie movie = new Movie();

		movie.setMovieId(1);
		movie.setTitle("Teste");
		movie.setCategory("Teste");

		MovieType movieType = mapper.map(movie,MovieType.class);

		Assert.assertEquals(movie.getMovieId(), movieType.getMovieId());
		Assert.assertEquals(movie.getTitle(), movieType.getTitle());
		Assert.assertEquals(movie.getCategory(), movieType.getCategory());
	}

	@Test
	public void testMovieTypeMapToMovie(){
		MovieType movieType = new MovieType();

		movieType.setMovieId(1);
		movieType.setTitle("Teste");
		movieType.setCategory("Teste");

		Movie movie = mapper.map(movieType,Movie.class);

		Assert.assertEquals(movieType.getMovieId(), movie.getMovieId());
		Assert.assertEquals(movieType.getTitle(), movie.getTitle());
		Assert.assertEquals(movieType.getCategory(), movieType.getCategory());
	}

}
