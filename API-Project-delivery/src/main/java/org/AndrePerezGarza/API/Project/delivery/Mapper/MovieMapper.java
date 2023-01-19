package org.AndrePerezGarza.API.Project.delivery.Mapper;

import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MovieMapper {



    MovieDTO toDTO(Movie data);

    Movie toEntity(MovieDTO data);


}
