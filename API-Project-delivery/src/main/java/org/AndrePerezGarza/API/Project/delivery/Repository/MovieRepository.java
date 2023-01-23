package org.AndrePerezGarza.API.Project.delivery.Repository;

import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
// This interface is the repository manage by JPA
@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

// Signature of the method use to find entries by id
    Optional<Movie> findById (long id);


}
