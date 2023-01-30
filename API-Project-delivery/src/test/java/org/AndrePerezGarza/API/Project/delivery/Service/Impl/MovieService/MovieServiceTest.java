package org.AndrePerezGarza.API.Project.delivery.Service.Impl.MovieService;

import org.AndrePerezGarza.API.Project.delivery.DTO.MovieDTO;
import org.AndrePerezGarza.API.Project.delivery.Entity.Movie;
import org.AndrePerezGarza.API.Project.delivery.Mapper.MovieMapper;
import org.AndrePerezGarza.API.Project.delivery.Mapper.MovieMapperImpl;
import org.AndrePerezGarza.API.Project.delivery.Repository.MovieRepository;
import org.AndrePerezGarza.API.Project.delivery.Service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Spy
    MovieService movieServiceSpy;

    @Mock
    MovieRepository movieRepositoryMock;
    MovieMapperImpl movieMapperMock;

    @InjectMocks
    MovieServiceImpl movieService;




    // Will Test that the MovieService method findById calls the Movie Repository finById method
    @Test
    @DisplayName("finById Test")
    void findById() {
        Optional<Movie> movie = Optional.of(new Movie());
        given(movieRepositoryMock.findById(anyLong())).willReturn(movie);
        assertEquals(movie, movieService.findById(anyLong()));
    }


    // Will Test that the MovieService method delete calls the Movie Repository delete method and the finById method
    @Test
    @DisplayName("Update Test")
    void update() throws Exception {
        long id = 1;
        MovieDTO movieDTO = new MovieDTO();
        Optional<Movie> movie = Optional.of(new Movie());
        given(movieRepositoryMock.findById(anyLong())).willReturn(movie);
        movieService.update(id,movieDTO);
        verify(movieRepositoryMock,times(1)).findById(anyLong());
        verify(movieRepositoryMock,times(1)).save(any(Movie.class));

    }

    // Will Test that the MovieService method delete calls the Movie Repository delete method
    @Test
    @DisplayName("delete Test")
    void delete() throws Exception {
        long id = 1;
        Optional<Movie> movie = Optional.of(new Movie());
        given(movieRepositoryMock.findById(anyLong())).willReturn(movie);
        movieService.delete(id);
        verify(movieRepositoryMock,times(1)).deleteById(any());
    }

    /* Will Test that the MovieService getMovieInfo calls the MovieDB API and
       successfully return the requested info as a String based on a Movie name */

    @Test
    @DisplayName("getLink Test")
    void getLink() {

        assertEquals("https://v2.vidsrc.me/embed/624860/", movieService.getLink("Matrix"));
    }

    /* Will Test that if the user passes an invalid Movie name like a blank space the
     MovieService getLink method will return an error String to the user*/
    @Test
    @DisplayName("getLinkError Test")
    void getLinkError() {

        assertEquals("error in the request", movieService.getLink(" "));
    }

    /* Will Test that the MovieService getMovieInfo calls the MovieDB API and
       successfully return the requested info as a String based on a Movie name */
    @Test
    @DisplayName("getMovieInfo Test")
    void getMovieInfo() {

        assertEquals("The Matrix Resurrections", movieService.getMovieInfo("Matrix", "original_title"));
    }

    /* Will Test that if the user passes an invalid Movie name and info, like a blank space the
     MovieService getLink method will return an error String to the user*/

    @Test
    @DisplayName("getMovieInfoError Test")
    void getMovieInfoError() {

        assertEquals("error", movieService.getMovieInfo(" ", " "));
    }



    /* Will Test that if the user passes an invalid Movie name , like a blank space or null the
     MovieService findMovie method will return a RuntimeException*/

    @Test
    @DisplayName("getMovieInfoException Test")
    void findMovieException() {


        assertThrows(RuntimeException.class, () -> {
            movieService.findMovie(" ");
        });

    }

    // Will Test that the MovieService method saveFromName calls the Movie Repository save method
    @Test
    @DisplayName("saveFromName Test")
    void saveFromName() {

        movieService.saveFromName("Matrix");
        verify(movieRepositoryMock,times(1)).save(any(Movie.class));
    }
}