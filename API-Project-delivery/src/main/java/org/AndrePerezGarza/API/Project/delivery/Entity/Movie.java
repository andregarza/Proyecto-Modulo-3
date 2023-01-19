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

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Movies")
public class Movie {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "Movie", nullable = false)
    private String name;

    @Column(name = "Year", nullable = false)
    private long year;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "watched_at", updatable = false, nullable = false)
    private Date watchedAt;



}
