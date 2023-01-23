package org.AndrePerezGarza.API.Project.delivery.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.tuple.CreationTimestampGeneration;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;


// Model class where the movie variables are enlisted and Lombok is used to reduce code


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movies")
public class Movie {

// id is randomly generated
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Movie", nullable = false)
    private String name;

    @Column(name = "Year", nullable = false)
    private long year;
// Creation stamp is created everytime a new Movie Object is generated
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "watched_at", updatable = false, nullable = false)
    private Date watchedAt;



}
