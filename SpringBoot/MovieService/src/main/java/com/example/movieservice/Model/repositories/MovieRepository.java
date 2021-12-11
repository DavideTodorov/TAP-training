package com.example.movieservice.Model.repositories;

import com.example.movieservice.Model.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MovieRepository extends JpaRepository<Movie, UUID> {

    @Query("select m from Movie m where m.isAvailable = true")
    List<Movie> findAllAvailableMovies();

    Optional<Movie> findByName(String name);
}
