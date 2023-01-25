package org.AndrePerezGarza.API.Project.delivery.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

// This class is used to create a Movie DTO object that the controller will handle
@Data
public class MovieDTO {

    private long id;

    @NotEmpty(message = "The name of the movie can´t be empty")
    private String name;

    @Positive(message = "The name of the movie can´t be empty and needs to be positive")
    private long year;


    @Max(250)
    private String overview;

    private Date watchedAt;

}
