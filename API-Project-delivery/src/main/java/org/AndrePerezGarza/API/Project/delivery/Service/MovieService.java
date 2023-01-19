package org.AndrePerezGarza.API.Project.delivery.Service;


import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MovieService {

     List<MovieDTO> findAll();

     Optional<Movie> findById(long id);

     MovieDTO save(MovieDTO data);

     void update(long id, MovieDTO data) throws Exception;

     void delete(long id) throws Exception;

     String getLink(String name);

     String getName(String name);

     String getYear(String name);

     List<String> findMovie(String name);

     void saveFromName(String name);


}
