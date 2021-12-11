package com.example.movieservice.Service.impl;

import com.example.movieservice.Model.entities.Movie;
import com.example.movieservice.Model.repositories.MovieRepository;
import com.example.movieservice.Service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final ObjectMapper objectMapper;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public String getAllAvailableMovies() {
        String moviesListJson = "";

        try {
            moviesListJson = objectMapper.writeValueAsString(movieRepository.findAllAvailableMovies());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return moviesListJson;
    }

    @Override
    public String getMovie(String movieName) {
        Optional<Movie> optionalMovie = movieRepository.findByName(movieName);

        if (optionalMovie.isEmpty()) {
            return "Movie does not exist";
        }

        Movie movie = optionalMovie.orElse(null);

        if (!movie.isAvailable()) {
            return "Movie is not available";
        }

        movie.setAvailable(false);
        movieRepository.save(movie);

        String movieJson = "";
        try {
            movieJson = objectMapper.writeValueAsString(movie);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return movieJson;
    }
}














