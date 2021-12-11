package com.example.movieservice.Controller;

import com.example.movieservice.Service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/all")
    public String getAllAvailableMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("movie/{movieName}")
    public String rentMovie(@PathVariable String movieName){
        return movieService.getMovie(movieName);
    }
}
