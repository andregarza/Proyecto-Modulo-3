package org.AndrePerezGarza.API.Project.delivery.Controller;


import lombok.extern.slf4j.Slf4j;
import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.AndrePerezGarza.API.Project.delivery.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/movies")
public class MovieController {

    private MovieService movie;

    @Autowired
    public MovieController(MovieService movie) {
        this.movie = movie;
    }

    @GetMapping
    public List<MovieDTO> findAll(){
        log.info("Returning all values");
        return movie.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Movie> findId(@PathVariable long id) {
        log.info("Searching id: {}", id );
        return movie.findById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO save(@RequestBody MovieDTO data){
        log.info("Posting body {} to repository", data);
        return movie.save(data);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") long id, @RequestBody MovieDTO data) throws Exception{
        log.info("Updating by id");
       movie.update(id, data);
    }

    @DeleteMapping ("/{id}")
    //@ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) throws Exception{
        log.info("Deleting id: {}",id);
        movie.delete(id);
    }

    @GetMapping("/getLink{name}")
    public String getLink(@PathVariable("name") String name){
        log.info("Returning {} movie Link by name", name);
        return movie.getLink(name);
    }

    @GetMapping("/get{name}")
    public List<String> getMovie(@PathVariable("name") String name){
        log.info("Getting {} info",name);
        return movie.findMovie(name);
    }

    @PostMapping("/post{name}")
    @ResponseStatus(HttpStatus.OK)
    public void  saveMovie(@PathVariable("name") String name){
        log.info("Saving {} into repository", name);
         movie.saveFromName(name);
    }





}
