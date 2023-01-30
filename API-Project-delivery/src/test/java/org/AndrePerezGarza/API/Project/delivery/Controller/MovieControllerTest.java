package org.AndrePerezGarza.API.Project.delivery.Controller;

import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;

import org.AndrePerezGarza.API.Project.delivery.Service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieControllerTest {

    @Mock
    MovieService movieServiceMock;


    @InjectMocks
    MovieController movieController;


    // Will test that controller method findAll calls the service findAll and returns a list of MovieDTO
    @Test
    @DisplayName("Find all Test")
    void findAll() {
        List<MovieDTO> movies = new ArrayList<>();
        given(movieServiceMock.findAll()).willReturn(movies);
        assertEquals(movies, movieController.findAll());

    }

    //Will test that if the Service findAll method throws an Exception the controller findAll method also throws an exception
    @Test
    @DisplayName("findAll Exception Test")
    void findAllException(){
        doThrow(new RuntimeException()).when(movieServiceMock).findAll();

        assertThrows(RuntimeException.class, () -> {
            movieController.findAll();
        });

    }


    // Will test that controller findId calls the service method findById and returns an optional Movie object searched by id
    @Test
    @DisplayName("Find by id Test")
    void findId() {
        Optional<Movie> movie = Optional.of(new Movie());
        given(movieServiceMock.findById(anyLong())).willReturn(movie);
        assertEquals(movie, movieController.findId(anyLong()));

    }

    // Will test that the controller save method call 1 time the save method from the service class
    @Test
    @DisplayName("Save Test")
    void save() throws Exception {

        long id = 2;
        MovieDTO movieDTO = new MovieDTO();
        movieController.update(id,movieDTO);
        verify(movieServiceMock,times(1)).update(2,movieDTO);

    }

    // Will Test that when executed the controller save Method will return a Response Entity
    @Test
    @DisplayName("Save response Entity Test")
    void saveResponseEntity() {

        assertEquals(ResponseEntity.created(URI.create("")).build(), movieController.save(any(MovieDTO.class)));
    }

    //Will test that if the Service save method throws an Exception the controller save method also throws an exception
    @Test
    @DisplayName("save Exception Test")
    void saveException()  {
        doThrow(new RuntimeException()).when(movieServiceMock).save(any(MovieDTO.class));

        assertThrows(RuntimeException.class, () -> {
            movieController.save(any(MovieDTO.class) );
        });


    }

    // Will test that the controller Update Method calls 1 time the update method from the service class
    @Test
    @DisplayName("Update  Test")
    void update() throws Exception {

        long id = 1;
        MovieDTO movieDTO = new MovieDTO();
       movieController.update(id,movieDTO);
       verify(movieServiceMock,times(1)).update(1,movieDTO);


    }

    //Will test that if the Service update method throws an Exception the controller update method also throws an exception
    @Test
    @DisplayName("Update Exception Test")
    void updateException() throws Exception {
        doThrow(new RuntimeException()).when(movieServiceMock).update(anyLong(),any(MovieDTO.class));

        assertThrows(RuntimeException.class, () -> {
            movieController.update(anyLong(),any(MovieDTO.class) );
        });


    }

    // Will test that the controller delete Method calls 1 time the update method from the service class
    @Test
    @DisplayName("Delete Test")
    void delete() throws Exception {
        long id = 1;
        movieController.delete(id);
        verify(movieServiceMock,times(1)).delete(1);
    }

    //Will test that if the delete method from Service throws an Exception the controller delete method also throws an exception
    @Test
    @DisplayName("Delete Exception Test")
    void deleteException() throws Exception {
        doThrow(new RuntimeException()).when(movieServiceMock).delete(anyLong());

        assertThrows(RuntimeException.class, () -> {
            movieController.delete(anyLong());
        });


    }


    // Will test that the controller delete Method calls 1 time the update method from the service class
    @Test
    @DisplayName("Get Link Test")
    void getLink() {

        given(movieServiceMock.getLink(anyString())).willReturn("");
        assertEquals("", movieController.getLink(anyString()));


    }

    @Test
    @DisplayName("Get Link Exception Test")
    void getLinkException() {

        doThrow(new RuntimeException()).when(movieServiceMock).getLink(anyString());

        assertThrows(RuntimeException.class, () -> {
            movieController.getLink(anyString());
        });

    }



    // Tests that controller getMovie will call the controller find movie method and return its content
    @Test
    @DisplayName("get Movie Test")
    void getMovie() throws Exception {
        List<String> movie = new ArrayList<>();
        given(movieServiceMock.findMovie(anyString())).willReturn(movie);
        assertEquals(movie, movieController.getMovie(""));

    }

    //Will test that if the getMovie method from Service throws an Exception the controller getMovie method also throws an exception
    @Test
    @DisplayName("get Movie Exception Test")
    void getMovieException() throws Exception {
        doThrow(new RuntimeException()).when(movieServiceMock).findMovie(anyString());

        assertThrows(RuntimeException.class, () -> {
            movieController.getMovie(anyString());
        });
    }


    // Will test that the controller saveMovie method call 1 time the saveFromName method from the service class
    @Test
    @DisplayName("SaveMovie Test")
    void saveMovie() {

        movieController.saveMovie(anyString());
        verify(movieServiceMock,times(1)).saveFromName(anyString());
    }

    // Will Test that when executed the controller saveMovie Method will return a Response Entity
    @Test
    @DisplayName("SaveMovie Response Entity Test")
    void saveMovieResponseEntity(){
        assertEquals(ResponseEntity.created(URI.create("")).build(),  movieController.saveMovie(anyString()));

    }

    //Will test that if the saveMovie method from Service throws an Exception the controller saveFromName method also throws an exception
    @Test
    @DisplayName("SaveMovie Exception Test")
    void saveMovieException(){
        doThrow(new RuntimeException()).when(movieServiceMock).saveFromName(anyString());

        assertThrows(RuntimeException.class, () -> {
            movieController.saveMovie(anyString());
        });

    }

}