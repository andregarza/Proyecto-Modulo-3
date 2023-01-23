package org.AndrePerezGarza.API.Project.delivery.DTO;


import lombok.Data;

import java.util.Date;

// This class is used to create a Movie DTO object that the controller will handle
@Data
public class MovieDTO {

    private long id;
    private String name;
    private long year;
    private Date watchedAt;

}
