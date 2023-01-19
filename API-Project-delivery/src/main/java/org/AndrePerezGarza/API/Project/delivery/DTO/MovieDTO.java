package org.AndrePerezGarza.API.Project.delivery.DTO;


import lombok.Data;

import java.util.Date;

@Data
public class MovieDTO {

    private long id;
    private String name;
    private long year;
    private Date watchedAt;

}
