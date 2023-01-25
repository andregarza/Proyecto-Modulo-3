package org.AndrePerezGarza.API.Project.delivery.Controller;


import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.AndrePerezGarza.API.Project.delivery.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

//This class is used to map all the endpoints for the Movie class
@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    // Movie object is created  fpr the variable injection from the MovieService interface
    private MovieService movie;

    // The Movie service object is created to use the methods to implement the business logic
    @Autowired
    public MovieController(MovieService movie) {
        this.movie = movie;
    }

    // returns all the entries in the DB
    @GetMapping
    public List<MovieDTO> findAll(){
        log.info("Returning all values");
        return movie.findAll();
    }

    // returns an entry searched by the id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Movie> findId(@PathVariable long id) {
        log.info("Searching id: {}", id );
        return movie.findById(id);
    }


    // Creates a new entry by using the body of the Post request
    @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> save(@Valid @RequestBody MovieDTO data){
        log.info("Posting body {} to repository", data);
        movie.save(data);
        return ResponseEntity.created(URI.create("")).build();
    }

    // Updates an entry searched by the id and uses the body of the request
    @PutMapping("/{id}")
   // @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody MovieDTO data) throws Exception{
        log.info("Updating by id");
       movie.update(id, data);
    }

    // Deletes an entry searched by the id
    @DeleteMapping ("/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws Exception{
        log.info("Deleting id: {}",id);
        movie.delete(id);
    }

    // Gets the online repository link searched by the name on the link
    @GetMapping("/getLink/{name}")
    public String getLink(@PathVariable("name") String name){
        log.info("Returning {} movie Link by name", name);
        return movie.getLink(name);
    }

    // Gets the Name of the movie from the Movie DB API searched by the name on the link
    @GetMapping("/get/{name}")
    public List<String> getMovie(@PathVariable("name") String name){
        log.info("Getting {} info",name);
        return movie.findMovie(name);
    }

    // Creates a new entry with the info from the Movie DB API searched by the name on the link
    @PostMapping("{name}")
    public ResponseEntity<Void>  saveMovie(@PathVariable("name") String name){
        log.info("Saving {} into repository", name);
        movie.saveFromName(name);
        return ResponseEntity.created(URI.create("")).build();
    }





}
