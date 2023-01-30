package org.AndrePerezGarza.API.Project.delivery.DTO;


import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Value;

import java.util.Date;

// This class is used to create a Movie DTO object that the controller will handle
@Data
public class MovieDTO {

    private long id;

    @NotEmpty(message = "The name of the movie can´t be empty")
    private String name;

    @Positive(message = "The name of the movie can´t be empty and needs to be positive")
    private long year;


    @Max(value = 250, message = "The overview can´t have more than 250 characters")
    @Min(value = 10, message = "The overview can´t be less than 10 characters")
    private String overview;

    private Date watchedAt;

}
