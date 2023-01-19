package org.AndrePerezGarza.API.Project.delivery.Repository;

import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findById (long id);


}
