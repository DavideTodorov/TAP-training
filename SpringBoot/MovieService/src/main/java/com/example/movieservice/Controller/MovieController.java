package com.example.movieservice.Controller;

import com.example.movieservice.Service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/available")
    public String getAllAvailableMovies(){
        return movieService.getAllAvailableMovies();
    }

    @GetMapping("movie/{movieName}")
    public String rentMovie(@PathVariable String movieName){
        return movieService.getMovie(movieName);
    }
}
