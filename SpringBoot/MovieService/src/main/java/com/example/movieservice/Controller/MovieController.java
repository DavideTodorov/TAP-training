package com.example.movieservice.Controller;

import com.example.movieservice.Service.MovieService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
